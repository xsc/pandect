(ns ^{:doc "Checksum Implementations"
      :author "Yannick Scherer"}
  pandect.checksum
  (:use pandect.gen.core)
  (:require [pandect.utils.convert :as c only [long->4-bytes]]) 
  (:import [java.util.zip Adler32]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

(deftype Adler32CodeGen []
  CodeGen
  (algorithm-string [_] "ADLER-32")
  (bytes->hash [_ form]
    `(let [buf# ~form
           a# (Adler32.)]
       (.update a# buf# 0 (count buf#))
       (.getValue a#)))
  (stream->hash [_ form]
    `(let [s# ~form
           buf# (byte-array 2048)
           a# (Adler32.)]
       (loop []
         (let [r# (.read s# buf# 0 2048)]
           (when-not (= r# -1)
             (.update a# buf# 0 r#)
             (recur))))
       (.getValue a#)))
  (hash->bytes [_ form]
    `(c/long->4-bytes ~form))
  (hash->string [_ form]
    `(Long/toString (long ~form) 16)))

(defmethod code-generator "ADLER-32"
  [_]
  (Adler32CodeGen.))
