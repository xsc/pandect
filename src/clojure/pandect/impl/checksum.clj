(ns ^:no-doc pandect.impl.checksum
  (:require [pandect.gen.core :refer :all]
            [pandect.gen.hash-generator :refer :all]
            [pandect.gen.hmac-generator :refer :all]
            [pandect.utils.convert :as c :only [long->4-bytes]])
  (:import [java.util.zip Adler32 CRC32]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

;; ## Checksum Class

(deftype ChecksumCodeGen [algorithm checksum-class]
  CodeGen
  (algorithm-string [_]
    algorithm)

  HashGen
  (bytes->hash [_ form]
    `(let [buf# (bytes ~form)
           a# (new ~checksum-class)]
       (.update a# buf# 0 (alength buf#))
       (.getValue a#)))
  (stream->hash [_ form buffer-size]
    `(let [s# ~form
           c# (int ~buffer-size)
           buf# (byte-array c#)
           a# (new ~checksum-class)]
       (loop []
         (let [r# (.read s# buf# 0 c#)]
           (when-not (= r# -1)
             (.update a# buf# 0 r#)
             (recur))))
       (.getValue a#)))
  (hash->bytes [_ form]
    `(c/long->4-bytes ~form))
  (hash->string [_ form]
    `(c/long->hex ~form)))

;; ## Checksums

(def ^:private CS_ALGORITHMS
  {"CRC-32"   `CRC32
   "ADLER-32" `Adler32})

(doseq [[algorithm checksum-class] CS_ALGORITHMS]
  (register-algorithm!
    (ChecksumCodeGen. algorithm checksum-class)))
