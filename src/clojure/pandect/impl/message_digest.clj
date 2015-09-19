(ns ^:no-doc pandect.impl.message-digest
  (:require [pandect.gen.core :as gen]
            [pandect.impl.message-digest
             [hmac :as hmac]
             [hash :as hash]
             [signature :as signature]]))

(def ^:private MD_ALGORITHMS
  (vector
    ["MD2"     nil          "MD2withRSA"]
    ["MD5"     "HmacMD5"    "MD5withRSA"]
    ["SHA-1"   "HmacSHA1"   "SHA1withRSA"   "SHA1withDSA"]
    ["SHA-256" "HmacSHA256" "SHA256withRSA"]
    ["SHA-384" "HmacSHA384" "SHA384withRSA"]
    ["SHA-512" "HmacSHA512" "SHA512withRSA"]))

(doseq [[hash-algorithm
         hmac-algorithm
         rsa-algorithm
         dsa-algorithm] MD_ALGORITHMS]
  (gen/register-algorithm!
    hash-algorithm
    (hash/make hash-algorithm)
    (hmac/make hmac-algorithm)
    (signature/make rsa-algorithm :rsa)
    (signature/make dsa-algorithm :dsa)))
