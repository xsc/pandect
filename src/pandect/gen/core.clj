(ns ^:no-doc pandect.gen.core
  (:import [java.io File FileInputStream]))

;; ## Concept
;;
;; Instead of having types that implement protocols that perform
;; the actual digest/checksum calculations (pandect <= 0.2.1), we
;; will have a protocol for code generators to be used to create
;; the digest/checksum functions in pandect.core at compile
;; time.

;; ## Code Generator Protocols

(defprotocol CodeGen
  "Protocol for Algorithm Code Generators."
  (algorithm-string [this]
    "Get String representing the Algorithm this Code Generator is built
     for."))

(defprotocol Generator
  "Protocol for the actual code generation based on algorithm code generators."
  (can-generate? [this code-gen]
    "Can this generator process the given algorithm generator?")
  (generate-protocol [this code-gen id buffer-size]
    "Generate and implement protocol for the types that should be processable.
     The given symbol should be used as the hash/HMAC function name.")
  (generate-functions [this code-gen id f]
    "Generate functions relying on the protocol function given in `id`. `f` is the
     symbol to be used as the base function name; `buffer-size` is the form used to
     lookup the buffer size for stream processing."))

;; ## Code Generator List

(defonce ^:private code-generators {})

(defn register-algorithm!
  "Register new algorithm code generator."
  ([code-gen]
   (register-algorithm! {:name (algorithm-string code-gen)} code-gen))
  ([{:keys [name requires docstring]} & code-gens]
   (alter-var-root
     #'code-generators
     (fn [gens]
       (-> gens
           (update-in [name :docstring] #(or % docstring))
           (update-in [name :requires] concat requires)
           (update-in [name :code-gens]
                      concat (filter identity code-gens)))))))

(defn get-code-generators
  "Lookup code generators by algorithm."
  [algorithm-string]
  (let [v (code-generators algorithm-string ::none)]
    (if (= v ::none)
      (println "WARN: No such Code Generator:" algorithm-string)
      v)))

;; ## Helpers

(defn wrap-file-stream
  "Replace the given symbol's value with an input stream."
  ([generator-code sym]
   (->> (vary-meta sym assoc :tag `File)
        (wrap-file-stream generator-code sym)))
  ([generator-code sym fsym]
   (let [wrap-fn (condp = (:tag (meta fsym))
                   `File `clojure.java.io/as-file
                   `String `str)]
     `(with-open [~sym (FileInputStream. (~wrap-fn ~fsym))]
        ~generator-code))))

(defn as-sym
  [sym & suffixes]
  (symbol (apply str (name sym) suffixes)))

(defn symbol+
  [sym suffix]
  (cond (= suffix :*) (symbol (str (name sym) "*"))
        suffix (symbol (str (name sym) "-" (name suffix)))
        :else sym))

;; ## Generation

(defn generate
  "Generate algorithm functions based on the given generators."
  [generator code-gen f buffer-size]
  (when (can-generate? generator code-gen)
    (let [id (gensym (str "compute-" (name f)))]
      `(do
         ~(generate-protocol generator code-gen id buffer-size)
         ~(generate-functions generator code-gen id f)))))
