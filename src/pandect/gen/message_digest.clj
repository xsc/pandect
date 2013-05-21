(ns ^ {:doc "Message Digest Code Generation"
       :author "Yannick Scherer"}
  pandect.gen.message-digest
  (:use pandect.gen.core)
  (:require [pandect.utils.convert :as c :only [bytes->hex]])
  (:import [java.security MessageDigest]
           [java.io InputStream FileInputStream File]))

;; ## Code Generator

(deftype MessageDigestCodeGen [algorithm]
  CodeGen
  (algorithm-string [_] algorithm)
  (bytes->hash [_ form]
    `(let [md# (MessageDigest/getInstance ~algorithm)]
       (.digest md# ~form)))
  (stream->hash [_ form]
    `(let [md# (MessageDigest/getInstance ~algorithm)
           buf# (byte-array 2048)
           s# ~form]
       (loop []
         (let [r# (.read s# buf# 0 2048)]
           (when-not (= r# -1)
             (.update md# buf# 0 r#)
             (recur))))
       (.digest md#)))
  (hash->string [_ form]
    `(c/bytes->hex ~form))
  (hash->bytes [_ form]
    form))

;; ## Register all Algorithms

(defmacro ^:private register-code-generators!
  "Register all MessageDigest code generators by implementing
   pandect.gen.core/code-generator."
  []
  `(do
     ~@(for [algorithm ["MD2" "MD5" "SHA-1" "SHA-256" "SHA-384" "SHA-512"]]
         `(defmethod code-generator ~algorithm
            [_#]
            (MessageDigestCodeGen. ~algorithm)))))

(register-code-generators!)
