(ns ^{:doc "Core Tests for Pandect"
      :author "Yannick Scherer"}
  pandect.core-test
  (:require [midje.sweet :refer :all]
            [pandect.core :as pandect]
            [pandect.gen.core :refer [symbol+]]
            [pandect.utils.convert :refer [bytes->hex]]))

(tabular
  (fact "about bytes->hex"
    (let [^"[B" d (byte-array (map byte ?data))]
      (bytes->hex d) => ?result))
  ?data                ?result
  [0 0 0 0]            "00000000"
  [-1 7 16 64]         "ff071040"
  [-5 127 4 15]        "fb7f040f")

(tabular
  (fact "about the API"
        (let [fs (->> [nil :* :file* :file :bytes :file-bytes]
                      (map #(symbol+ ?sym %))
                      (map #(ns-resolve 'pandect.core %)))]
          fs => (has every? var?)
          (map var-get fs) => (has every? fn?)
          (map (comp :doc meta) fs) => (has every? string?)))
  ?sym
  ;; Checksum
  'adler32   'crc32

  ;; Hash
  'gost
  'md2       'md4       'md5
  'ripemd128 'ripemd160 'ripemd256 'ripemd320
  'sha1
  'sha224    'sha256    'sha384    'sha512
  'sha3-224  'sha3-256  'sha3-384  'sha3-512
  'tiger
  'whirlpool

  ;; HMAC
  'gost-hmac
  'md2-hmac       'md4-hmac       'md5-hmac
  'ripemd128-hmac 'ripemd160-hmac 'ripemd256-hmac 'ripemd320-hmac
  'sha1-hmac
  'sha224-hmac    'sha256-hmac    'sha384-hmac    'sha512-hmac
  'sha3-224-hmac  'sha3-256-hmac  'sha3-384-hmac  'sha3-512-hmac
  'siphash   'siphash48
  'tiger-hmac
  'whirlpool-hmac)
