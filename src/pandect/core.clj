(ns ^{:doc "Digest Creation for Pandect"
      :author "Yannick Scherer"}
  pandect.core
  (:require [pandect.buffer]
            [potemkin :refer [import-vars]]
            [pandect.codegen :refer [algorithms algorithm-namespace]]))

;; ## Re-export all algorithms

;; ### Helper

(defn- collect-algorithm-functions
  [ns algorithm-symbol]
  (require ns)
  (let [s (name algorithm-symbol)]
    (->> (ns-publics ns)
         (map (comp first))
         (filter #(.startsWith (name %) s)))))

;; ### Declare (necessary for clj-kondo to work)

(defn- create-declare
  []
  `(~'declare ~'with-buffer-size
              ~@(->> (for [algo (algorithms)
                           :let [ns (algorithm-namespace algo)]]
                       (collect-algorithm-functions ns algo))
                     (apply concat))))

(comment
  ;; Create the 'declare' form by evaluting the following:
  (create-declare))

(declare with-buffer-size ripemd256 ripemd256-hmac-file-bytes ripemd256-file ripemd256-hmac ripemd256-hmac-file ripemd256-hmac* ripemd256* ripemd256-hmac-bytes ripemd256-file-bytes ripemd256-hmac-file* ripemd256-bytes ripemd256-file* tiger tiger-file* tiger-hmac-bytes tiger-file tiger-hmac tiger-hmac-file-bytes tiger-file-bytes tiger-hmac* tiger-bytes tiger-hmac-file tiger* tiger-hmac-file* sha3-512 sha3-512-bytes sha3-512-file-bytes sha3-512* sha3-512-file* sha3-512-file keccak-384-file keccak-384-hmac* keccak-384 keccak-384-hmac-file keccak-384* keccak-384-bytes keccak-384-hmac-file-bytes keccak-384-file* keccak-384-file-bytes keccak-384-hmac-file* keccak-384-hmac keccak-384-hmac-bytes md5-file-bytes md5-hmac-bytes md5-hmac md5-rsa-verify-file md5-rsa* md5 md5-hmac-file* md5* md5-rsa-bytes md5-bytes md5-rsa-verify md5-rsa-file md5-file md5-hmac-file-bytes md5-rsa-file-bytes md5-rsa-file* md5-rsa md5-file* md5-hmac* md5-hmac-file ripemd128-hmac-file-bytes ripemd128 ripemd128-hmac ripemd128-file-bytes ripemd128-hmac-bytes ripemd128-hmac* ripemd128-hmac-file* ripemd128-file ripemd128-bytes ripemd128* ripemd128-file* ripemd128-hmac-file ripemd320-hmac-bytes ripemd320-hmac-file* ripemd320-hmac ripemd320 ripemd320-hmac* ripemd320-file ripemd320-hmac-file-bytes ripemd320-file* ripemd320-bytes ripemd320-hmac-file ripemd320* ripemd320-file-bytes siphash48-file siphash48-file-bytes siphash48-file* siphash48 siphash48-bytes siphash48* blake2b-384-file* blake2b-384-bytes blake2b-384* blake2b-384 blake2b-384-file blake2b-384-file-bytes sha3-224* sha3-224-bytes sha3-224-file* sha3-224-file-bytes sha3-224 sha3-224-file blake2b-512* blake2b-512-file* blake2b-512-file-bytes blake2b-512-bytes blake2b-512 blake2b-512-file sha3-384-file sha3-384 sha3-384* sha3-384-file* sha3-384-file-bytes sha3-384-bytes md2-rsa-file md2-bytes md2-rsa-verify md2-rsa* md2-rsa-file-bytes md2-rsa-bytes md2-file-bytes md2 md2-rsa-file* md2-rsa-verify-file md2* md2-file* md2-rsa md2-file whirlpool-hmac whirlpool-hmac* whirlpool-bytes whirlpool-hmac-bytes whirlpool-hmac-file* whirlpool whirlpool-file whirlpool* whirlpool-file* whirlpool-hmac-file whirlpool-hmac-file-bytes whirlpool-file-bytes blake2b-256* blake2b-256-bytes blake2b-256-file* blake2b-256-file blake2b-256-file-bytes blake2b-256 sha3-256-file-bytes sha3-256-file sha3-256-file* sha3-256 sha3-256-bytes sha3-256* siphash-file* siphash* siphash-file siphash siphash-bytes siphash-file-bytes keccak-224-file* keccak-224-hmac-file keccak-224-hmac* keccak-224-bytes keccak-224* keccak-224-hmac-file* keccak-224-file keccak-224-hmac keccak-224-file-bytes keccak-224 keccak-224-hmac-file-bytes keccak-224-hmac-bytes sha1-rsa sha1-rsa-file sha1-file sha1-rsa* sha1-dsa sha1-bytes sha1-hmac-file* sha1-dsa* sha1-hmac-bytes sha1-file* sha1-hmac-file-bytes sha1-hmac sha1-dsa-file* sha1-hmac* sha1-rsa-verify sha1* sha1 sha1-rsa-bytes sha1-dsa-bytes sha1-rsa-verify-file sha1-rsa-file-bytes sha1-rsa-file* sha1-dsa-file-bytes sha1-hmac-file sha1-dsa-verify sha1-dsa-verify-file sha1-file-bytes sha1-dsa-file sha224-hmac-file sha224-hmac sha224-bytes sha224-hmac* sha224-hmac-file* sha224-file sha224* sha224-file-bytes sha224-file* sha224-hmac-file-bytes sha224 sha224-hmac-bytes sha384-rsa sha384-rsa-verify-file sha384-rsa-verify sha384* sha384-hmac sha384-hmac-file sha384-hmac-file* sha384-rsa-file-bytes sha384-rsa* sha384-rsa-file* sha384-file-bytes sha384-file* sha384-rsa-bytes sha384-hmac-file-bytes sha384-file sha384 sha384-bytes sha384-hmac* sha384-rsa-file sha384-hmac-bytes keccak-256-bytes keccak-256-hmac* keccak-256-file* keccak-256* keccak-256-hmac keccak-256-hmac-file-bytes keccak-256-hmac-file keccak-256-file-bytes keccak-256-hmac-bytes keccak-256-hmac-file* keccak-256 keccak-256-file gost-file-bytes gost-hmac-file* gost-hmac* gost-hmac-bytes gost-bytes gost-hmac gost-hmac-file-bytes gost-file* gost* gost-hmac-file gost-file gost sha512-hmac-file-bytes sha512-rsa* sha512-bytes sha512-hmac sha512-rsa-file sha512-rsa-verify sha512-rsa-file* sha512-hmac* sha512-hmac-file sha512-hmac-file* sha512-file-bytes sha512-file* sha512-file sha512-rsa-file-bytes sha512 sha512-rsa-bytes sha512* sha512-hmac-bytes sha512-rsa sha512-rsa-verify-file crc32-file* crc32-bytes crc32-file-bytes crc32* crc32 crc32-file blake2b-160-bytes blake2b-160-file blake2b-160-file-bytes blake2b-160 blake2b-160* blake2b-160-file* ripemd160-hmac* ripemd160-file ripemd160-hmac-file* ripemd160-file-bytes ripemd160-file* ripemd160-hmac-file-bytes ripemd160-bytes ripemd160* ripemd160-hmac-file ripemd160-hmac-bytes ripemd160 ripemd160-hmac keccak-512-hmac-file-bytes keccak-512-hmac-file keccak-512-file-bytes keccak-512-file* keccak-512-hmac-bytes keccak-512-hmac* keccak-512-hmac-file* keccak-512-bytes keccak-512-hmac keccak-512* keccak-512-file keccak-512 sha256-hmac-bytes sha256-hmac-file sha256-file-bytes sha256-rsa* sha256-rsa sha256-hmac* sha256-hmac sha256* sha256-rsa-verify sha256-hmac-file-bytes sha256-rsa-file-bytes sha256-file* sha256-rsa-bytes sha256-bytes sha256-rsa-file* sha256-hmac-file* sha256-file sha256-rsa-file sha256 sha256-rsa-verify-file md4* md4-file md4-bytes md4-hmac* md4-hmac-file* md4-hmac md4-file-bytes md4-hmac-file-bytes md4-file* md4-hmac-file md4 md4-hmac-bytes adler32* adler32-bytes adler32-file* adler32-file adler32-file-bytes adler32)

;; ### Import Vars

(defmacro ^:private reexport-algos
  []
  `(do
     (import-vars pandect.buffer/with-buffer-size)
     ~@(for [algo (algorithms)
             :let [ns (algorithm-namespace algo)]]
         `(do
            (require '~ns)
            (import-vars
              [~ns ~@(collect-algorithm-functions ns algo)])))))

(reexport-algos)
