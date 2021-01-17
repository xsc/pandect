(ns pandect.signature-test
  (:require [clojure.test :refer [deftest are is]]
            [pandect.core :as core])
  (:import [java.security KeyPairGenerator]))

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
           (doseq [[_ input] inputs]
             (let [in0 (input test-string)
                   in1 (input test-string)
                   result (sign-algorithm in0 private-key)]
               (is (string? result))
               (is (seq result))
               (is (verify-algorithm in1 result public-key))))
           true)

         core/md2-rsa       core/md2-rsa-verify
         core/md5-rsa       core/md5-rsa-verify
         core/sha1-rsa      core/sha1-rsa-verify
         core/sha256-rsa    core/sha256-rsa-verify
         core/sha384-rsa    core/sha384-rsa-verify
         core/sha512-rsa    core/sha512-rsa-verify)))

(let [[private-key public-key] (generate-keys "DSA" 768)
      inputs {:string  identity
              :bytes  #(.getBytes ^String % "UTF-8")
              :stream #(java.io.ByteArrayInputStream. (.getBytes ^String % "UTF-8"))}]
  (deftest t-dsa-algorithms
    (are [sign-algorithm verify-algorithm]
         (do
           (doseq [[_ input] inputs]
             (let [in0 (input test-string)
                   in1 (input test-string)
                   result (sign-algorithm in0 private-key)]
               (is (string? result))
               (is (seq result))
               (is (verify-algorithm in1 result public-key))))
           true)

         core/sha1-dsa core/sha1-dsa-verify)))
