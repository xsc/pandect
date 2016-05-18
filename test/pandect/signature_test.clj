(ns pandect.signature-test
  (:require [clojure.test :refer :all]
            [pandect.core :refer :all]
            [clojure.java.io :as io])
  (:import [java.security KeyPairGenerator KeyFactory]
           [java.io File]))

;; ## Helpers

(defn- generate-keys
  [algorithm bits]
  (let [gen (doto (KeyPairGenerator/getInstance algorithm)
              (.initialize (int bits)))
        pair (.genKeyPair gen)]
    (vector
      (.getPrivate pair)
      (.getPublic pair))))

;; ## Tests

(def test-string "The quick brown fox jumps over the lazy dog")

(let [[private-key public-key] (generate-keys "RSA" 768)
      inputs {:string  identity
              :bytes  #(.getBytes ^String % "UTF-8")
              :stream #(java.io.ByteArrayInputStream. (.getBytes ^String % "UTF-8"))}]
  (deftest t-rsa-algorithms
    (are [sign-algorithm verify-algorithm]
         (do
           (doseq [[k input] inputs]
             (let [in0 (input test-string)
                   in1 (input test-string)
                   result (sign-algorithm in0 private-key)]
               (is (string? result))
               (is (seq result))
               (is (verify-algorithm in1 result public-key))))
           true)

         md2-rsa       md2-rsa-verify
         md5-rsa       md5-rsa-verify
         sha1-rsa      sha1-rsa-verify
         sha256-rsa    sha256-rsa-verify
         sha384-rsa    sha384-rsa-verify
         sha512-rsa    sha512-rsa-verify)))

(let [[private-key public-key] (generate-keys "DSA" 768)
      inputs {:string  identity
              :bytes  #(.getBytes ^String % "UTF-8")
              :stream #(java.io.ByteArrayInputStream. (.getBytes ^String % "UTF-8"))}]
  (deftest t-dsa-algorithms
    (are [sign-algorithm verify-algorithm]
         (do
           (doseq [[k input] inputs]
             (let [in0 (input test-string)
                   in1 (input test-string)
                   result (sign-algorithm in0 private-key)]
               (is (string? result))
               (is (seq result))
               (is (verify-algorithm in1 result public-key))))
           true)

         sha1-dsa sha1-dsa-verify)))
