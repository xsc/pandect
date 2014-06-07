(ns ^ {:doc "Message Digest Code Generation"
       :author "Yannick Scherer"}
  pandect.gen.message-digest
  (:use pandect.gen.core)
  (:require [pandect.utils.convert :as c :only [bytes->hex]])
  (:import [java.security MessageDigest]
           [javax.crypto Mac]
           [javax.crypto.spec SecretKeySpec]
           [java.io InputStream FileInputStream File]))

;; ## Code Generator

(deftype MessageDigestCodeGen [hash-algorithm hmac-algorithm]
  CodeGen
  (algorithm-string [_]
    (if (and hash-algorithm hmac-algorithm)
      (str hash-algorithm "/" hmac-algorithm)
      (or hash-algorithm hmac-algorithm)))

  HMACGen
  (bytes->hmac [_ msg-form key-form]
    (let [msg (vary-meta (gensym "msg") assoc :tag "[B")]
      `(let [mac# (Mac/getInstance ~hmac-algorithm)
             ~msg ~msg-form
             k# (SecretKeySpec. ~key-form ~hmac-algorithm)]
         (-> (doto mac#
               (.init k#)
               (.update ~msg))
           (.doFinal)))))
  (stream->hmac [_ stream-form key-form]
    (let [s (vary-meta (gensym "s") assoc :tag `InputStream)]
      `(let [mac# (Mac/getInstance ~hmac-algorithm)
             k# (SecretKeySpec. ~key-form ~hmac-algorithm)
             c# (int *buffer-size*)
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
    form)

  HashGen
  (bytes->hash [_ form]
    `(let [md# (MessageDigest/getInstance ~hash-algorithm)]
       (.digest md# ~form)))
  (stream->hash [_ form]
    `(let [md# (MessageDigest/getInstance ~hash-algorithm)
           c# (int *buffer-size*)
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

(defmacro ^:private register-code-generators!
  "Register all MessageDigest code generators by implementing
   pandect.gen.core/code-generator."
  []
  `(do
     ~@(for [[hash-algorithm hmac-algorithm] MD_ALGORITHMS]
         (let [algorithm (or hash-algorithm hmac-algorithm)]
           `(defmethod code-generator ~algorithm
              [_#]
              (MessageDigestCodeGen. ~hash-algorithm ~hmac-algorithm))))))

(register-code-generators!)
