(ns ^:no-doc pandect.impl.message-digest
  (:require [pandect.gen.core :as gen]
            [pandect.impl.message-digest
             [hmac :as hmac]
             [hash :as hash]]))

(def ^:private MD_ALGORITHMS
  (vector
    ["MD2"]
    ["MD5"     "HmacMD5"]
    ["SHA-1"   "HmacSHA1"]
    ["SHA-256" "HmacSHA256"]
    ["SHA-384" "HmacSHA384"]
    ["SHA-512" "HmacSHA512"]))

(doseq [[hash-algorithm hmac-algorithm] MD_ALGORITHMS]
  (gen/register-algorithm!
    hash-algorithm
    (hash/make hash-algorithm)
    (hmac/make hmac-algorithm)))
