(ns ^{:doc "Digest Creation for Pandect"
      :author "Yannick Scherer"}
  pandect.core
  (:use pandect.gen.core)
  (:require [pandect.gen message-digest checksum]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

;; ## Available Algorithms

(def ^:private algorithms
  {'md5     "MD5"      'md2    "MD2"
   'sha1    "SHA-1"    'sha256 "SHA-256"
   'sha384  "SHA-384"  'sha512 "SHA-512"
   'adler32 "ADLER-32" 'crc32  "CRC-32"})

(defmacro ^:private generate-hash-functions!
  []
  `(do
     ~@(for [[sym algorithm] algorithms]
         (when-let [cg (code-generator algorithm)]
           (generate-hash cg sym)))
     nil))

(generate-hash-functions!)
