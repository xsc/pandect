(ns pandect.gen.bouncy-castle
  (:require [pandect.gen.core :refer :all]
            [pandect.utils.convert :as c]))

;; ## Code Generator

(defn- create-digest-form
  [digest-class constructor-args]
  `(new ~(symbol (format "org.bouncycastle.crypto.digests.%s" digest-class))
        ~@constructor-args))

(deftype BouncyCastleCodeGen [algorithm digest-class constructor-args]
  CodeGen
  (algorithm-string [_] algorithm)
  (support-hash? [_] true)
  (support-hmac? [_] false)
  (bytes->hash [_ form]
    `(let [buf# ~form
           digest# ~(create-digest-form digest-class constructor-args)
           result# (byte-array (.getDigestSize digest#))]
       (doto digest#
         (.update buf# 0 (alength (bytes buf#)))
         (.doFinal result# 0))
       result#))
  (stream->hash [_ form]
    `(let [s# ~form
           c# (int *buffer-size*)
           digest# ~(create-digest-form digest-class constructor-args)
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
  '{"GOST 34.11-94"  GOST3411Digest
    "MD4"            MD4Digest
    "RIPEMD-128"     RIPEMD128Digest
    "RIPEMD-160"     RIPEMD160Digest
    "RIPEMD-256"     RIPEMD256Digest
    "RIPEMD-320"     RIPEMD320Digest
    "SHA-224"        SHA224Digest
    "SHA-3 (224)"    [SHA3Digest 224]
    "SHA-3 (256)"    [SHA3Digest 256]
    "SHA-3 (384)"    [SHA3Digest 384]
    "SHA-3 (512)"    [SHA3Digest 512]
    "Tiger (192,3)"  TigerDigest
    "Whirlpool"      WhirlpoolDigest})

(defmacro ^:private register-code-generators!
  "Register all MessageDigest code generators by implementing
   pandect.gen.core/code-generator."
  []
  `(do
     ~@(for [[algorithm v] BC_ALGORITHMS]
         (let [[digest-class & args] (if (vector? v) v [v])]
           `(defmethod code-generator ~algorithm
              [_#]
              (BouncyCastleCodeGen. ~algorithm '~digest-class [~@args]))))))

(register-code-generators!)
