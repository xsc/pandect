(ns ^:no-doc pandect.gen.hmac-generator
  (:require [pandect.gen
             [core :as gen]
             [protocol-utils :as protocol]
             [function-utils :as function]]
            [pandect.utils.convert :refer [convert-to-byte-array]]))

;; ## Generation

(defprotocol HMACGen
  (base-symbol [this sym]
    "Create base symbol for function generation.")
  (bytes->hmac [this msg-form key-form]
    "Generate code to convert the byte array produced by the given `msg-form`
     to a value representing the hash-based message authentication code using the given
     `key-form` (a byte array).")
  (stream->hmac [this stream-form key-form buffer-size]
    "Generate code to convert the input stream produced by the given `stream-form`
     to a value representing the hash-based message authentication code using the given
     `key-form` (a byte array).")
  (hmac->string [this form]
    "Generate code to convert the HMAC value produced by the given form to
     a hex string.")
  (hmac->bytes [this form]
    "Generate code to convert the HMAC value produced by the given form to
     a byte array."))

;; ## Generator

(def hmac-generator
  "Generates function representing a HMAC algorithm based on an implementation
   of 'HMACGen'."
  (reify gen/Generator
    (can-generate? [_ code-gen]
      (satisfies? HMACGen code-gen))
    (generate-protocol [_ code-gen protocol-fn buffer-size]
      (let [this (gensym "data")
            k (gensym "key")]
        (protocol/generate
          [this]
          {:name   protocol-fn
           :args   [k]
           :bytes  (bytes->hmac code-gen this k)
           :stream (stream->hmac code-gen this k buffer-size)})))
    (generate-functions [_ code-gen protocol-fn f]
      (function/generate
        'x
        {:name        (base-symbol code-gen f)
         :fn          protocol-fn
         :args        '[secret]
         :docstring   (str "[HMAC] " (gen/algorithm-string code-gen) " (%s)%n%n"
                           "'secret' can be given as a byte array, string, "
                           "java.io.File, java.io.InputStream%nor any value "
                           "implementing `pandect.utils.convert/ByteConvertable`.")
         :transform   {'secret `(convert-to-byte-array ~'secret)}
         :wrap-string #(hmac->string code-gen %)
         :wrap-bytes  #(hmac->bytes code-gen %)}))))
