(ns ^ {:doc "Message Digest Code Generation"
       :author "Yannick Scherer"}
  pandect.impl.message-digest
  (:require [pandect.gen
             [core :refer :all]
             [hash-generator :refer :all]
             [hmac-generator :refer :all]]
            [pandect.utils.convert :as c :only [bytes->hex]])
  (:import [java.security MessageDigest]
           [javax.crypto Mac]
           [javax.crypto.spec SecretKeySpec]
           [java.io InputStream FileInputStream File]))

;; ## Code Generator

(deftype MessageDigestHMACGen [algorithm]
  CodeGen
  (algorithm-string [_]
    algorithm)

  HMACGen
  (base-symbol [_ sym]
    (symbol+ sym :hmac))
  (bytes->hmac [_ msg-form key-form]
    (let [msg (vary-meta (gensym "msg") assoc :tag "[B")]
      `(let [mac# (Mac/getInstance ~algorithm)
             ~msg ~msg-form
             k# (SecretKeySpec. ~key-form ~algorithm)]
         (-> (doto mac#
               (.init k#)
               (.update ~msg))
           (.doFinal)))))
  (stream->hmac [_ stream-form key-form buffer-size]
    (let [s (vary-meta (gensym "s") assoc :tag `InputStream)]
      `(let [mac# (Mac/getInstance ~algorithm)
             k# (SecretKeySpec. ~key-form ~algorithm)
             c# (int ~buffer-size)
             buf# (byte-array c#)
             ~s ~stream-form]
         (.init mac# k#)
         (loop []
           (let [r# (.read ~s buf# 0 c#)]
             (when-not (= r# -1)
               (.update mac# buf# 0 r#)
               (recur))))
         (.doFinal mac#))))
  (hmac->string [_ form]
    `(c/bytes->hex ~form))
  (hmac->bytes [_ form]
    form))

(deftype MessageDigestHashGen [algorithm]
  CodeGen
  (algorithm-string [_]
    algorithm)

  HashGen
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
    `(c/bytes->hex ~form))
  (hash->bytes [_ form]
    form))

;; ## Register all Algorithms

(def ^:private MD_ALGORITHMS
  (vector
    ["MD2"]
    ["MD5"     "HmacMD5"]
    ["SHA-1"   "HmacSHA1"]
    ["SHA-256" "HmacSHA256"]
    ["SHA-384" "HmacSHA384"]
    ["SHA-512" "HmacSHA512"]))

(doseq [[hash-algorithm hmac-algorithm] MD_ALGORITHMS]
  (register-algorithm!
    hash-algorithm
    (MessageDigestHashGen. hash-algorithm)
    (when hmac-algorithm
      (MessageDigestHMACGen. hmac-algorithm))))
