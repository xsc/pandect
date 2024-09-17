(ns ^:no-doc pandect.impl.bouncy-castle
  (:require [pandect.gen.core :as gen]
            [pandect.impl.message-digest
             [hmac :as hmac]
             [hash :as hash]]))

;; ## Message Digest/HMAC Algorithms

(def ^:private MD_ALGORITHMS
  (concat
    ;; Single Instances
    (vector
      ["GOST3411"   "Hmac-GOST3411"]
      ["MD4"        "Hmac-MD4"]
      ["PARALLELHASH128-256"]
      ["PARALLELHASH256-512"]
      ["SHA-224"    "Hmac-SHA224"]
      ["SHAKE128-256"]
      ["SHAKE256-512"]
      ["SM3"]
      ["Tiger"      "Hmac-Tiger"]
      ["TUPLEHASH128-256"]
      ["TUPLEHASH256-512"]
      ["Whirlpool"  "Hmac-Whirlpool"]
      )

    ;; BLAKE2B
    (for [length [160 256 384 512]]
      [(str "BLAKE2B-" length)])

    ;; BLAKE2S
    (for [length [128 160 224 256]]
      [(str "BLAKE2S-" length)])

    ;; BLAKE3
    (for [length [256]]
      [(str "BLAKE3-" length)])

    ;; DSTU7564
    (for [length [256 384 512]]
      [(str "DSTU7564-" length)])

    ;; HARAKA-256
    (for [length [256 512]]
      [(str "HARAKA-" length)])

    ;; KECCAK
    (for [length [224 256 288 384 512]]
      [(str "Keccak-" length) (str "Hmac-Keccak" length)])

    ;; RipeMD
    (for [rmd-length [128 160 256 320]]
      [(str "RipeMD" rmd-length) (str "Hmac-RipeMD" rmd-length)])

    ;; SHA-3
    (for [sha3-length [224 256 384 512]]
      [(str "SHA3-" sha3-length)])

    ;; SHA-512
    (for [length [224 256]]
      [(str "SHA-512/" length)])

    ;; SKEIN-256
    (for [length [128 160 224 256]]
      [(str "SKEIN-256-" length)])

    ;; SKEIN-512
    (for [length [128 160 224 256 384 512]]
      [(str "SKEIN-512-" length)])

    ;; SKEIN-1024
    (for [length [384 512 1024]]
      [(str "SKEIN-1024-" length)])

    ))

(doseq [[hash-algorithm
         hmac-algorithm] MD_ALGORITHMS]
  (gen/register-algorithm!
    {:name      hash-algorithm
     :requires  '(pandect.utils.bouncy-castle-provider)
     :docstring "(requires `org.bouncycastle/bcprov-jdk18on` to be on the classpath)"}
    (hash/make hash-algorithm)
    (hmac/make hmac-algorithm)))

;; ## HMAC-only Algorithms

(def ^:private HMAC_ALGORITHMS
  (vector
    ["Siphash-2-4"]
    ["Siphash-4-8"]))

(doseq [[hmac-algorithm] HMAC_ALGORITHMS]
  (gen/register-algorithm!
    {:name      hmac-algorithm
     :requires  '(pandect.utils.bouncy-castle-provider)
     :docstring "(requires `org.bouncycastle/bcprov-jdk18on` to be on the classpath)"}
    (hmac/make-exclusive hmac-algorithm)))
