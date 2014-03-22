(ns ^{:doc "Checksum Implementations"
      :author "Yannick Scherer"}
  pandect.gen.checksum
  (:use pandect.gen.core)
  (:require [pandect.utils.convert :as c only [long->4-bytes]]) 
  (:import [java.util.zip Adler32 CRC32]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

;; ## CRC32

(deftype CRC32CodeGen []
  CodeGen
  (algorithm-string [_] "CRC-32")
  (support-hash? [_] true)
  (support-hmac? [_] false)
  (bytes->hash [_ form]
    `(let [buf# ~form
           a# (CRC32.)]
       (.update a# buf# 0 (count buf#))
       (.getValue a#)))
  (stream->hash [_ form]
    `(let [s# ~form
           buf# (byte-array *buffer-size*)
           a# (CRC32.)]
       (loop []
         (let [r# (.read s# buf# 0 *buffer-size*)]
           (when-not (= r# -1)
             (.update a# buf# 0 r#)
             (recur))))
       (.getValue a#)))
  (hash->bytes [_ form]
    `(c/long->4-bytes ~form))
  (hash->string [_ form]
    `(c/long->hex ~form)))

(defmethod code-generator "CRC-32"
  [_]
  (CRC32CodeGen.))

;; ## Adler32

(deftype Adler32CodeGen []
  CodeGen
  (algorithm-string [_] "ADLER-32")
  (support-hash? [_] true)
  (support-hmac? [_] false)
  (bytes->hash [_ form]
    `(let [buf# ~form
           a# (Adler32.)]
       (.update a# buf# 0 (count buf#))
       (.getValue a#)))
  (stream->hash [_ form]
    `(let [s# ~form
           buf# (byte-array *buffer-size*)
           a# (Adler32.)]
       (loop []
         (let [r# (.read s# buf# 0 *buffer-size*)]
           (when-not (= r# -1)
             (.update a# buf# 0 r#)
             (recur))))
       (.getValue a#)))
  (hash->bytes [_ form]
    `(c/long->4-bytes ~form))
  (hash->string [_ form]
    `(c/long->hex ~form)))

(defmethod code-generator "ADLER-32"
  [_]
  (Adler32CodeGen.))
