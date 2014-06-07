(ns ^ {:doc "Code Generation for Pandect"
       :author "Yannick Scherer"}
  pandect.gen.core
  (:import [java.io File FileInputStream InputStream]))

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
  (can-generate? [this code-gen])
  (generate-protocol [this code-gen id])
  (generate-functions [this code-gen id f]))

;; ## Multimethod

(defmulti code-generator
  "Get code generator for the given algorithm."
  identity
  :default nil)

(defmethod code-generator nil
  [x]
  (println "WARN: No such Code Generator: " x)
  nil)

;; ## Helpers

(defn wrap-file-stream
  "Replace the given symbol's value with an input stream."
  ([generator-code sym]
   (->> (vary-meta sym assoc :tag `File)
        (wrap-file-stream generator-code sym)))
  ([generator-code sym fsym]
   `(with-open [~sym (FileInputStream. ~fsym)]
      ~generator-code)))

(defn symbol+
  [sym suffix]
  (symbol (str (name sym) "-" (name suffix))))

(defn symbol*
  [sym]
  (symbol (str (name sym) "*")))

;; ## Generation

(defn generate
  "Generate algorithm functions based on the given generators."
  [generator code-gen f]
  (when (can-generate? generator code-gen)
    (let [id (gensym (str "compute-" (name f)))]
      `(do
         ~(generate-protocol generator code-gen id)
         ~(generate-functions generator code-gen id f)))))

;; ## Dynamic Variable for Buffer Size

(def ^:dynamic *buffer-size* 2048)
