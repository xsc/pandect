(ns ^:no-doc pandect.impl.message-digest.hmac
  (:require [pandect.gen
             [core :as gen]
             [hmac-generator :as hmac]]
            [pandect.utils.convert :as convert])
  (:import [javax.crypto Mac]
           [javax.crypto.spec SecretKeySpec]))

;; ## Code Generator

(deftype MessageDigestHMACGen [algorithm symbol-suffix]
  gen/CodeGen
  (algorithm-string [_]
    algorithm)

  hmac/HMACGen
  (base-symbol [_ sym]
    (if symbol-suffix
      (gen/symbol+ sym symbol-suffix)
      sym))
  (bytes->hmac [_ msg-form key-form]
    `(let [mac# (Mac/getInstance ~algorithm)
           msg# (bytes ~msg-form)
           k# (SecretKeySpec. ~key-form ~algorithm)]
       (-> (doto mac#
             (.init k#)
             (.update msg#))
           (.doFinal))))
  (stream->hmac [_ stream-form key-form buffer-size]
    `(let [mac# (Mac/getInstance ~algorithm)
           k# (SecretKeySpec. ~key-form ~algorithm)
           c# (int ~buffer-size)
           buf# (byte-array c#)
           s# ~stream-form]
       (.init mac# k#)
       (loop []
         (let [r# (.read s# buf# 0 c#)]
           (when-not (= r# -1)
             (.update mac# buf# 0 r#)
             (recur))))
       (.doFinal mac#)))
  (hmac->string [_ form]
    `(convert/bytes->hex ~form))
  (hmac->bytes [_ form]
    form))

;; ## Constructor

(defn make
  [algorithm]
  (some-> algorithm (->MessageDigestHMACGen :hmac)))

(defn make-exclusive
  [algorithm]
  (some-> algorithm (->MessageDigestHMACGen nil)))
