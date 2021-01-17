(ns ^:no-doc pandect.impl.message-digest.hash
  (:require [pandect.gen
             [core :as gen]
             [hash-generator :as hash]]
            [pandect.utils.convert :as convert])
  (:import [java.security MessageDigest]))

;; ## Code Generator

(deftype MessageDigestHashGen [algorithm]
  gen/CodeGen
  (algorithm-string [_]
    algorithm)

  hash/HashGen
  (bytes->hash [_ form]
    `(let [md# (MessageDigest/getInstance ~algorithm)]
       (.digest md# ~form)))
  (stream->hash [_ form buffer-size]
    `(let [md# (MessageDigest/getInstance ~algorithm)
           c# (int ~buffer-size)
           buf# (byte-array c#)
           s# ~form]
       (loop []
         (let [r# (.read s# buf# 0 c#)]
           (when-not (= r# -1)
             (.update md# buf# 0 r#)
             (recur))))
       (.digest md#)))
  (hash->string [_ form]
    `(convert/bytes->hex ~form))
  (hash->bytes [_ form]
    form))

;; ## Constructor

(defn make
  [algorithm]
  (some-> algorithm ->MessageDigestHashGen))
