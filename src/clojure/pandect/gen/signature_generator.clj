(ns ^:no-doc pandect.gen.signature-generator
  (:require [pandect.gen
             [core :as gen]
             [protocol-utils :as protocol]
             [function-utils :as function]]
            [pandect.utils.convert :refer [convert-signature-to-byte-array]]))

;; ## Generation

(defprotocol SignatureGen
  (base-sym [_ sym])
  (bytes->signature [this form key-form])
  (stream->signature [this form key-form buffer-size])
  (signature->string [this form])
  (signature->bytes [this form])
  (bytes->verify [this form signature-form key-form])
  (stream->verify [this form signature-form key-form buffer-size]))

;; ## Generator

(def signature-generator
  "Generates function representing a Hash algorithm based on an implementation
   of `SignatureGen`."
  (reify gen/Generator
    (can-generate? [_ code-gen]
      (satisfies? SignatureGen code-gen))
    (generate-protocol [_ code-gen protocol-fn buffer-size]
      (let [this (gensym "data")
            sig (gensym "sig")
            k (gensym "key")]
        (protocol/generate
          (gensym)
          this
          ;; sign implementation
          {:name   protocol-fn
           :args   [k]
           :bytes  (bytes->signature code-gen this k)
           :stream (stream->signature code-gen this k buffer-size)}

          ;; verification implementation
          {:name   (gen/symbol+ protocol-fn :verify)
           :args   [sig k]
           :bytes  (bytes->verify code-gen this sig k)
           :stream (stream->verify  code-gen this sig k buffer-size)})))
    (generate-functions [_ code-gen protocol-fn f]
      (let [algorithm (gen/algorithm-string code-gen)
            sym (base-sym code-gen f)]
        (function/generate
          'x

          ;; sign function
          {:name       sym
           :fn         protocol-fn
           :args       '[private-key]
           :docstring  (str "[Signature] " algorithm " (%s)%n%n"
                            "Sign the given message using the given "
                            "java.security.PrivateKey.")
           :wrap-string #(signature->string code-gen %)
           :wrap-bytes  #(signature->bytes code-gen %)}

          ;; verify function
          {:name       (gen/symbol+ sym :verify)
           :fn         (gen/symbol+ protocol-fn :verify)
           :args       '[signature public-key]
           :transform  {'signature `(convert-signature-to-byte-array ~'signature)}
           :docstring  (str "[Signature] " algorithm "\n\n"
                            "Verify the given message signature using the given "
                            "java.security.PublicKey\n"
                            "or java.security.cert.Certificate.\n\n"
                            "The signature can be given as a byte array, "
                            "hex (!) string, java.io.File,\n"
                            "java.io.InputStream or any value implementing "
                            "`pandect.utils.convert/ByteConvertable`.")
           :suffixes   {nil :none}})))))
