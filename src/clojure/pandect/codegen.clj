(ns ^:no-doc pandect.codegen
  (:require [clojure.pprint :refer [pprint]]
            [clojure.java.io :refer [file writer]]
            [pandect.impl message-digest checksum bouncy-castle]
            [pandect.gen
             [core :refer [generate get-code-generators as-sym]]
             [hash-generator :refer [hash-generator]]
             [hmac-generator :refer [hmac-generator]]
             [signature-generator :refer [signature-generator]]]))

(def ^:private algorithms-names
  (-> '{gost      "GOST3411"
        whirlpool "Whirlpool"
        adler32   "ADLER-32"
        crc32     "CRC-32"
        tiger     "Tiger"
        siphash   "Siphash-2-4"
        siphash48 "Siphash-4-8"}

      ;; KECCAK
      (into
        (for [length [224 256 384 512]]
          [(as-sym 'keccak- length) (str "Keccak-" length)]))

      ;; BLAKE2B
      (into
        (for [length [160 256 384 512]]
          [(as-sym 'blake2b- length) (str "BLAKE2B-" length)]))

      ;; MDx
      (into
        (for [v [2 4 5]]
          [(as-sym 'md v) (str "MD" v)]))

      ;; RipeMD
      (into
        (for [length [128 160 256 320]]
          [(as-sym 'ripemd length) (str "RipeMD" length)]))

      ;; SHA
      (into
        (for [length [1 224 256 384 512]]
          [(as-sym 'sha length) (str "SHA-" length)]))

      ;; SHA-3
      (into
        (for [length [224 256 384 512]]
          [(as-sym 'sha3- length) (str "SHA3-" length)]))))

(def ^:private generators
  [hash-generator
   hmac-generator
   signature-generator])

(defn algorithm-namespace
  [algorithm-symbol]
  (symbol (str 'pandect.algo. algorithm-symbol)))

(defn- write-source!
  [file sym algorithm]
  (let [out (writer file)
        {:keys [requires docstring code-gens]} (get-code-generators algorithm)
        defs (for [generator generators
                   code-gen  code-gens
                   :let [code (generate generator code-gen sym '*buffer-size*)]
                   :when code]
               code)]
    (doseq [form (list*
                   (list
                     'ns (algorithm-namespace sym)
                     (format "%s algorithm implementation%s"
                             algorithm
                             (str (some->> docstring (str "\n\n"))))
                     (list* :require
                            '[pandect.buffer :refer [*buffer-size*]]
                            '[pandect.utils.convert]
                            (distinct requires)))
                   defs)]
      (pprint form out))))

(defn- generate-algo-namespaces!
  [base]
  (->> (sort-by val algorithms-names)
       (pmap
         (fn [[sym algorithm]]
           (let [filename (.replace (format "%s.clj" sym) \- \_)]
             (locking *out*
               (println "[codegen] *" algorithm))
             (doto (file base "pandect" "algo" filename)
               (-> .getParentFile .mkdirs)
               (write-source! sym algorithm)))))
       (dorun)))

(defn algorithms
  []
  (keys algorithms-names))

(defn -main
  [& [base]]
  (let [base (or base "target/generated")]
    (println "[codegen] writing algorithms to:" base)
    (generate-algo-namespaces! base)
    (println "[codegen] done.")
    (shutdown-agents)))
