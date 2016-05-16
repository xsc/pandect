(ns ^:no-doc pandect.impl.bouncy-castle
  (:require [pandect.gen.core :as gen]
            [pandect.impl.message-digest
             [hmac :as hmac]
             [hash :as hash]]
            [pandect.utils.convert :as c]))

;; ## Message Digest/HMAC Algorithms

(def ^:private MD_ALGORITHMS
  (concat
    ;; Single Instances
    (vector
      ["GOST3411"   "Hmac-GOST3411"]
      ["MD4"        "Hmac-MD4"]
      ["SHA-224"    "Hmac-SHA224"]
      ["Tiger"      "Hmac-Tiger"]
      ["Whirlpool"  "Hmac-Whirlpool"])

    ;; KECCAK
    (for [length [224 256 384 512]]
      [(str "Keccak-" length) (str "Hmac-Keccak" length)])

    ;; BLAKE2B
    (for [length [160 256 384 512]]
      [(str "BLAKE2B-" length)])

    ;; RipeMD
    (for [rmd-length [128 160 256 320]]
      [(str "RipeMD" rmd-length) (str "Hmac-RipeMD" rmd-length)])

    ;; SHA-3
    (for [sha3-length [224 256 384 512]]
      [(str "SHA3-" sha3-length)])))

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
