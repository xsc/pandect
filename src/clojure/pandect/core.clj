(ns ^{:doc "Digest Creation for Pandect"
      :author "Yannick Scherer"}
  pandect.core
  (:use pandect.gen.core)
  (:require [pandect.gen
             message-digest checksum
             bouncy-castle]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

;; ## Available Algorithms

(def ^:private algorithms
  '{md5       "MD5"        md2       "MD2"
    md4       "MD4"        gost3411  "GOST 34.11-94"
    sha1      "SHA-1"      sha224    "SHA-224"
    sha256    "SHA-256"    sha384    "SHA-384"
    sha512    "SHA-512"    sha3      "SHA-3"
    adler32   "ADLER-32"   crc32     "CRC-32"
    ripemd128 "RIPEMD-128" ripemd160 "RIPEMD-160"
    ripemd256 "RIPEMD-256" ripemd320 "RIPEMD-320"
    tiger     "Tiger"      whirlpool "Whirlpool"})

(defmacro ^:private generate-hash-functions!
  []
  `(do
     ~@(for [[sym algorithm] algorithms]
         (when-let [cg (code-generator algorithm)]
           (generate-hash cg sym)))
     nil))

(generate-hash-functions!)

;; ## Buffer Size

(defmacro with-buffer-size
  "Set buffer size for stream processing."
  [n & body]
  `(binding [*buffer-size* ~n]
     ~@body))
