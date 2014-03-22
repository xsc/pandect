(ns pandect.gen.bouncy-castle
  (:require [pandect.gen.core :refer :all]
            [pandect.utils.convert :as c]))

;; ## Code Generator

(deftype BouncyCastleCodeGen [algorithm digest-class]
  CodeGen
  (algorithm-string [_] algorithm)
  (support-hash? [_] true)
  (support-hmac? [_] false)
  (bytes->hash [_ form]
    `(let [buf# ~form
           digest# (new ~(symbol (format "org.bouncycastle.crypto.digests.%s" digest-class)))
           result# (byte-array (.getDigestSize digest#))]
       (doto digest#
         (.update buf# 0 (alength (bytes buf#)))
         (.doFinal result# 0))
       result#))
  (stream->hash [_ form]
    `(let [s# ~form
           c# (int *buffer-size*)
           digest# (new ~(symbol (format "org.bouncycastle.crypto.digests.%s" digest-class)))
           buf# (byte-array c#)]
       (loop []
         (let [r# (.read s# buf# 0 c#)]
           (when (not= r# -1)
             (.update digest# buf# 0 r#)
             (recur))))
       (let [result# (byte-array (.getDigestSize digest#))]
         (.doFinal digest# result# 0)
         result#)))
  (hash->string [_ form]
    `(c/bytes->hex ~form))
  (hash->bytes [_ form]
    form))

;; ## Register Bouncy Castle Algorithms

(def ^:private BC_ALGORITHMS
  '{GOST3411Digest  "GOST 34.11-94"
    MD4Digest       "MD4"
    RIPEMD128Digest "RIPEMD-128"
    RIPEMD160Digest "RIPEMD-160"
    RIPEMD256Digest "RIPEMD-256"
    RIPEMD320Digest "RIPEMD-320"
    SHA224Digest    "SHA-224"
    SHA3Digest      "SHA-3"
    TigerDigest     "Tiger"
    WhirlpoolDigest "Whirlpool"})

(defmacro ^:private register-code-generators!
  "Register all MessageDigest code generators by implementing
   pandect.gen.core/code-generator."
  []
  `(do
     ~@(for [[digest-class algorithm] BC_ALGORITHMS]
         `(defmethod code-generator ~algorithm
            [_#]
            (BouncyCastleCodeGen. ~algorithm '~digest-class)))))

(register-code-generators!)
