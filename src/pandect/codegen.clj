(ns ^:no-doc pandect.codegen
  (:require [clojure.pprint :refer [pprint]]
            [clojure.java.io :refer [file writer]]
            [clojure.string :as str]
            [pandect.impl message-digest checksum bouncy-castle]
            [pandect.gen
             [core :refer [generate get-code-generators as-sym]]
             [hash-generator :refer [hash-generator]]
             [hmac-generator :refer [hmac-generator]]
             [signature-generator :refer [signature-generator]]])
  (:import (java.security Security)))

(def ^:private algorithms-names
  (-> '{gost                "GOST3411"
        whirlpool           "Whirlpool"
        adler32             "ADLER-32"
        crc32               "CRC-32"
        tiger               "Tiger"
        parallelhash128-256 "PARALLELHASH128-256"
        parallelhash256-512 "PARALLELHASH256-512"
        siphash             "Siphash-2-4"
        siphash48           "Siphash-4-8"
        shake128-256        "SHAKE128-256"
        shake256-512        "SHAKE256-512"
        sm3                 "SM3"
        tuplehash128-256    "TUPLEHASH128-256"
        tuplehash256-512    "TUPLEHASH256-512"
        }

      ;; BLAKE2B
      (into
        (for [length [160 256 384 512]]
          [(as-sym 'blake2b- length) (str "BLAKE2B-" length)]))

      ;; BLAKE2S
      (into
        (for [length [128 160 224 256]]
          [(as-sym 'blake2s- length) (str "BLAKE2S-" length)]))

      ;; BLAKE3
      (into
        (for [length [256]]
          [(as-sym 'blake3- length) (str "BLAKE3-" length)]))

      ;; HARAKA
      (into
        (for [length [256 512]]
          [(as-sym 'haraka- length) (str "HARAKA-" length)]))

      ;; KECCAK
      (into
        (for [length [224 256 288 384 512]]
          [(as-sym 'keccak- length) (str "Keccak-" length)]))

      ;; KUPYNA
      (into
        (for [length [256 384 512]]
          [(as-sym 'kupyna- length) (str "DSTU7564-" length)]))
      (into
        (for [length [256 384 512]]
          [(as-sym 'dstu7464- length) (str "DSTU7564-" length)]))

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
          [(as-sym 'sha3- length) (str "SHA3-" length)]))

      ;; SHA-512
      (into
        (for [length [224 256]]
          [(as-sym 'sha512- length) (str "SHA-512/" length)]))

      ;; SKEIN-256
      (into
        (for [length [128 160 224 256]]
          [(as-sym 'skein256- length) (str "SKEIN-256-" length)]))

      ;; SKEIN-512
      (into
        (for [length [128 160 224 256 384 512]]
          [(as-sym 'skein512- length) (str "SKEIN-512-" length)]))

      ;; SKEIN-1024
      (into
        (for [length [384 512 1024]]
          [(as-sym 'skein1024- length) (str "SKEIN-1024-" length)]))))

(defn- missing-algorithms
  []
  (require 'pandect.utils.bouncy-castle-provider)
  (let [implemented (set (map str/lower-case (vals algorithms-names)))
        available (->> (Security/getAlgorithms "MessageDigest")
                       (remove #(re-matches #"^.*\d\.\d.+" %)))]
    (remove (comp implemented str/lower-case) available)))

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
               (println "[codegen] *" sym '-> algorithm))
             (doto (file base "pandect" "algo" filename)
               (-> .getParentFile .mkdirs)
               (write-source! sym algorithm)))))
       (dorun)))

(defn algorithms
  []
  (keys algorithms-names))

(defn -main
  [& [base]]
  (let [base (or base "src")]
    (println "[codegen] writing algorithms to:" base)
    (generate-algo-namespaces! base)
    (println "[codegen] done.")
    (when-let [algos (seq (missing-algorithms))]
      (println "[codegen] Unimplemented:" (str/join ", " algos)))
    (shutdown-agents)))
