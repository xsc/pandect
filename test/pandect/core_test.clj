(ns pandect.core-test
  (:require [pandect.gen.core :refer [symbol+]]
            [pandect.utils.convert :refer [bytes->hex]]
            [clojure.test :refer [deftest are is]]))

;; ## Utils Tests

(deftest t-bytes->hex
  (are [data result] (= result (->> data
                                    (map byte)
                                    (byte-array)
                                    (bytes->hex)))
       [0 0 0 0]     "00000000"
       [-1 7 16 64]  "ff071040"
       [-5 127 4 15] "fb7f040f"))

;; ## API Tests

;; ### Helpers

(defn- namespace-exists?
  [ns]
  (try
    (require ns)
    true
    (catch Throwable _ false)))

(defn- algorithm-function?
  [ns sym]
  (let [r (ns-resolve ns sym)]
    (when (var? r)
      (and (fn? @r)
           (-> r meta :doc string?
               (is (str ns "/" sym " is missing documentation.")))))))

(defn- suffix-exists?
  [ns sym suffix]
  (algorithm-function? ns (symbol+ sym suffix)))

(defmacro ^:private deftest-api
  [sym suffixes & algorithms]
  `(deftest ~sym
     (are [~'algorithm] (let [~'algo-ns (symbol (str "pandect.algo." ~'algorithm))
                              ~'exists? #(suffix-exists? ~'algo-ns %)]
                          (is (namespace-exists? ~'algo-ns))
                          (is (algorithm-function? ~'algo-ns ~'algorithm))
                          (doseq [~'suffix ~suffixes]
                            (is (suffix-exists? ~'algo-ns ~'algorithm ~'suffix)))
                          true)
          ~@(for [algorithm algorithms] `(quote ~algorithm)))))

;; ### Declare (for clj-kondo)

(declare adler32
         crc32
         blake2b-160
         blake2b-256
         blake2b-384
         blake2b-512
         sha3-224
         sha3-256
         sha3-384
         sha3-512
         md2
         gost
         keccak-224
         keccak-256
         keccak-384
         keccak-512
         md4
         md5
         ripemd128
         ripemd160
         ripemd256
         ripemd320
         sha1
         sha224
         sha256
         sha384
         sha512
         tiger
         whirlpool)

;; ### Tests

(deftest-api t-digest-only-functions
  [:* :file* :file :bytes :file-bytes]
  adler32
  crc32
  blake2b-160
  blake2b-256
  blake2b-384
  blake2b-512
  sha3-224
  sha3-256
  sha3-384
  sha3-512
  md2)

(deftest-api t-digest-and-hmac-functions
  [      :*     :file*      :file      :bytes      :file-bytes
   :hmac :hmac* :hmac-file* :hmac-file :hmac-bytes :hmac-file-bytes]
  gost
  keccak-224
  keccak-256
  keccak-384
  keccak-512
  md4
  md5
  ripemd128
  ripemd160
  ripemd256
  ripemd320
  sha1
  sha224
  sha256
  sha384
  sha512
  tiger
  whirlpool)

(deftest-api t-rsa-signature-functions
  [:rsa :rsa* :rsa-file* :rsa-file :rsa-bytes :rsa-file-bytes]
  md2
  md5
  sha1
  sha256
  sha384
  sha512)

(deftest-api t-dsa-signature-functions
  [:dsa :dsa* :dsa-file* :dsa-file :dsa-bytes :dsa-file-bytes]
  sha1)
