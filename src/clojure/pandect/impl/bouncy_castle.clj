(ns ^:no-doc pandect.impl.bouncy-castle
  (:require [pandect.gen.core :as gen]
            [pandect.impl.message-digest
             [hmac :as hmac]
             [hash :as hash]]
            [pandect.utils.convert :as c]))

;; ## Message Digest/HMAC Algorithms

(def ^:private MD_ALGORITHMS
  (vector
    ["GOST3411"   "Hmac-GOST3411"]
    ["MD4"        "Hmac-MD4"]
    ["RipeMD128"  "Hmac-RipeMD128"]
    ["RipeMD160"  "Hmac-RipeMD160"]
    ["RipeMD256"  "Hmac-RipeMD256"]
    ["RipeMD320"  "Hmac-RipeMD320"]
    ["SHA-224"    "Hmac-SHA224"]
    ["SHA3-224"   "Hmac-SHA3-224"]
    ["SHA3-256"   "Hmac-SHA3-256"]
    ["SHA3-384"   "Hmac-SHA3-384"]
    ["SHA3-512"   "Hmac-SHA3-512"]
    ["Tiger"      "Hmac-Tiger"]
    ["Whirlpool"  "Hmac-Whirlpool"]))

(doseq [[hash-algorithm
         hmac-algorithm] MD_ALGORITHMS]
  (gen/register-algorithm!
    {:name     hash-algorithm
     :requires '(pandect.utils.bouncy-castle-provider)}
    (hash/make hash-algorithm)
    (hmac/make hmac-algorithm)))

;; ## HMAC-only Algorithms

(def ^:private HMAC_ALGORITHMS
  (vector
    ["Siphash-2-4"]
    ["Siphash-4-8"]))

(doseq [[hmac-algorithm] HMAC_ALGORITHMS]
  (gen/register-algorithm!
    {:name     hmac-algorithm
     :requires '(pandect.utils.bouncy-castle-provider)}
    (hmac/make-exclusive hmac-algorithm)))
