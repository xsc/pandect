(ns ^:no-doc pandect.codegen
  (:require [clojure.pprint :refer [pprint]]
            [clojure.java.io :refer [file writer]]
            [pandect.impl message-digest checksum bouncy-castle]
            [pandect.gen
             [core :refer [generate get-code-generators]]
             [hash-generator :refer [hash-generator]]
             [hmac-generator :refer [hmac-generator]]
             [signature-generator :refer [signature-generator]]]))

(def ^:private algorithms-names
  '{md5       "MD5"           md2       "MD2"
    md4       "MD4"           gost      "GOST 34.11-94"
    sha1      "SHA-1"         sha224    "SHA-224"
    sha256    "SHA-256"       sha384    "SHA-384"
    sha512    "SHA-512"       sha3-256  "SHA-3 (256)"
    sha3-224  "SHA-3 (224)"   sha3-384  "SHA-3 (384)"
    sha3-512  "SHA-3 (512)"   whirlpool "Whirlpool"
    adler32   "ADLER-32"      crc32     "CRC-32"
    ripemd128 "RIPEMD-128"    ripemd160 "RIPEMD-160"
    ripemd256 "RIPEMD-256"    ripemd320 "RIPEMD-320"
    tiger     "Tiger (192,3)" siphash   "SipHash-2-4"
    siphash48 "SipHash-4-8"})

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
        analyzed (for [generator generators
                       :let [{:keys [requires code-gens]}
                             (get-code-generators algorithm)]
                       code-gen code-gens
                       :let [code (generate generator code-gen sym '*buffer-size*)]
                       :when code]
                   {:def code
                    :requires requires})
        requires (distinct (mapcat :requires analyzed))
        defs (map :def analyzed)]
    (doseq [form (list*
                   (list
                     'ns (algorithm-namespace sym)
                     (format "%s algorithm implementation" algorithm)
                     (list* :require
                            '[pandect.buffer :refer [*buffer-size*]]
                            '[pandect.utils.convert]
                            requires))
                   defs)]
      (pprint form out))))

(defn- generate-algo-namespaces!
  [base]
  (doseq [[sym algorithm] algorithms-names
          :let [filename (.replace (format "%s.clj" sym) \- \_)]]
    (doto (file base "pandect" "algo" filename)
      (-> .getParentFile .mkdirs)
      (write-source! sym algorithm))))

(defn algorithms
  []
  (keys algorithms-names))

(defn -main [& [base]]
  (generate-algo-namespaces!
    (or base "target/generated")))
