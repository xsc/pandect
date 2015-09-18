(ns pandect.signature-test
  (:require [midje.sweet :refer :all]
            [pandect.core :refer :all]
            [clojure.java.io :as io])
  (:import [java.security KeyPairGenerator KeyFactory]
           [java.io File]))

;; ## Helpers

(defn- generate-keys
  [algorithm bits]
  (let [gen (doto (KeyPairGenerator/getInstance algorithm)
              (.initialize bits))
        pair (.genKeyPair gen)]
    (vector
      (.getPrivate pair)
      (.getPublic pair))))

(defn- tmp-file
  [contents]
  (let [f (doto (File/createTempFile "pandect" ".txt") (.deleteOnExit))]
    (spit f contents)
    f))

(defn- close!
  [& ins]
  (doseq [in ins]
    (when (instance? java.io.Closeable in)
      (.close in))))

;; ## Tests

(def test-string "The quick brown fox jumps over the lazy dog")

(let [[private-key public-key] (generate-keys "RSA" 768)]
  (tabular
    (tabular
      (fact "about RSA signing."
            (let [in0 ?input
                  in1 ?input
                  result (?sign in0 private-key)]
              result => string?
              result => seq
              (?verify in1 result public-key) => truthy
              (close! in0 in1)))
      ?sign         ?verify
      md2-rsa       md2-rsa-verify
      md5-rsa       md5-rsa-verify
      sha1-rsa      sha1-rsa-verify
      sha256-rsa    sha256-rsa-verify
      sha384-rsa    sha384-rsa-verify
      sha512-rsa    sha512-rsa-verify)
    ?input
    test-string
    (io/input-stream (.getBytes test-string "UTF-8"))
    (tmp-file test-string)))

(let [[private-key public-key] (generate-keys "DSA" 768)]
  (tabular
    (tabular
      (fact "about DSA signing."
            (let [in0 ?input
                  in1 ?input
                  result (?sign in0 private-key)]
              result => string?
              result => seq
              (?verify in1 result public-key) => truthy
              (close! in0 in1)))
      ?sign         ?verify
      sha1-dsa      sha1-dsa-verify)
    ?input
    test-string
    (io/input-stream (.getBytes test-string "UTF-8"))
    (tmp-file test-string)))
