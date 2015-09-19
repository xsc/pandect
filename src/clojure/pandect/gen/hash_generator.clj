(ns ^:no-doc pandect.gen.hash-generator
  (:require [pandect.gen
             [core :as gen]
             [protocol-utils :as protocol]
             [function-utils :as function]]))

;; ## Generation

(defprotocol HashGen
  (bytes->hash [this form]
    "Generate code to convert the byte array produced by the given form to
     a value representing the given hash.")
  (stream->hash [this form buffer-size]
    "Generate code to convert the input stream produced by the given form
     to a value representing the given hash.")
  (hash->string [this form]
    "Generate code to convert the hash value produced by the given form to
     a hex string.")
  (hash->bytes [this form]
    "Generate code to convert the hash value produced by the given form to
     a byte array."))

;; ## Generator

(def hash-generator
  "Generates function representing a Hash algorithm based on an implementation
  of `HashGen`."
  (reify gen/Generator
    (can-generate? [_ code-gen]
      (satisfies? HashGen code-gen))
    (generate-protocol [_ code-gen protocol-fn buffer-size]
      (let [this (gensym "data")]
        (protocol/generate
          [this]
          {:name   protocol-fn
           :bytes  (bytes->hash code-gen this)
           :stream (stream->hash code-gen this buffer-size)})))
    (generate-functions [_ code-gen protocol-fn f]
      (function/generate
        'x
        {:name        f
         :fn          protocol-fn
         :docstring   (str "[Hash] " (gen/algorithm-string code-gen) " (%s)")
         :wrap-string #(hash->string code-gen %)
         :wrap-bytes  #(hash->bytes code-gen %)}))))
