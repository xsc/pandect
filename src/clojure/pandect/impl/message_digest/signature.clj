(ns ^:no-doc pandect.impl.message-digest.signature
  (:require [pandect.gen
             [core :as gen]
             [signature-generator :as sig]]
            [pandect.utils.convert :as convert])
  (:import [java.security Signature PublicKey]))

;; ## Code Generator

(deftype MessageDigestSignatureGen [algorithm suffix]
  gen/CodeGen
  (algorithm-string [_]
    algorithm)

  sig/SignatureGen
  (base-sym [_ sym]
    (gen/symbol+ sym suffix))
  (bytes->signature [_ form key-form]
    `(let [signer# (doto (Signature/getInstance ~algorithm)
                     (.initSign ~key-form)
                     (.update (bytes ~form)))]
       (.sign signer#)))
  (bytes->verify [_ form signature-form key-form]
    `(let [signer# (Signature/getInstance ~algorithm)
           key# (convert/as-public-key ~key-form)]
       (.initVerify signer# key#)
       (.update signer# (bytes ~form))
       (.verify signer# (bytes ~signature-form))))
  (stream->signature [_ form key-form buffer-size]
    `(let [signer# (doto (Signature/getInstance ~algorithm)
                     (.initSign ~key-form))
           c# (int ~buffer-size)
           buf# (byte-array c#)
           s# ~form]
       (loop []
         (let [r# (.read s# buf# 0 c#)]
           (when-not (= r# -1)
             (.update signer# buf# 0 r#)
             (recur))))
       (.sign signer#)))
  (stream->verify [_ form signature-form key-form buffer-size]
    `(let [signer# (Signature/getInstance ~algorithm)
           c# (int ~buffer-size)
           buf# (byte-array c#)
           s# ~form
           key# (convert/as-public-key ~key-form)]
       (.initVerify signer# key#)
       (loop []
         (let [r# (.read s# buf# 0 c#)]
           (when-not (= r# -1)
             (.update signer# buf# 0 r#)
             (recur))))
       (.verify signer# (bytes ~signature-form))))
  (signature->string [_ form]
    `(convert/bytes->hex ~form))
  (signature->bytes [_ form]
    form))

;; ## Constructor

(defn make
  [algorithm suffix]
  (some-> algorithm (->MessageDigestSignatureGen suffix)))
