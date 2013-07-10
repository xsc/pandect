(ns ^{:doc "HMAC Tests for Pandect"
      :author "Yannick Scherer"}
  pandect.hmac-test
  (:import java.io.File)
  (:use midje.sweet
        pandect.core
        [clojure.java.io :only [input-stream delete-file]]))

(def test-string "The quick brown fox jumps over the lazy dog")
(def test-key "key")

(tabular
  (fact "about hashing a string"
    (?digest test-string test-key) => ?result)
  ?digest       ?result
  md5-hmac      "80070713463e7749b90c2dc24911e275"
  sha1-hmac     "de7c9b85b8b78aa6bc8a7a36f70a90701c9db4d9"
  sha256-hmac   "f7bc83f430538424b13298e6aa6fb143ef4d59a14946175997479dbc2d1a3cd8"
  sha384-hmac   "d7f4727e2c0b39ae0f1e40cc96f60242d5b7801841cea6fc592c5d3e1ae50700582a96cf35e1e554995fe4e03381c237"
  sha512-hmac   "b42af09057bac1e2d41708e48a902e09b5ff7f12ab428a4fe86653c73dd248fb82f948a549f7b791a5b41915ee4d1ec3935357e4e2317250d0372afa2ebeeb3a")

(tabular
  (fact "about hashing 'Hello World!' as an input stream"
    (?digest (input-stream (.getBytes test-string)) test-key) => ?result)
  ?digest       ?result
  md5-hmac      "80070713463e7749b90c2dc24911e275"
  sha1-hmac     "de7c9b85b8b78aa6bc8a7a36f70a90701c9db4d9"
  sha256-hmac   "f7bc83f430538424b13298e6aa6fb143ef4d59a14946175997479dbc2d1a3cd8"
  sha384-hmac   "d7f4727e2c0b39ae0f1e40cc96f60242d5b7801841cea6fc592c5d3e1ae50700582a96cf35e1e554995fe4e03381c237"
  sha512-hmac   "b42af09057bac1e2d41708e48a902e09b5ff7f12ab428a4fe86653c73dd248fb82f948a549f7b791a5b41915ee4d1ec3935357e4e2317250d0372afa2ebeeb3a")

(let [path (.getAbsolutePath (doto (File/createTempFile "pandect" ".txt") (.deleteOnExit)))]
  (tabular
    (fact "about hashing a file"
      (spit path test-string)
      (?digest path test-key) => ?result)
    ?digest            ?result
    md5-hmac-file      "80070713463e7749b90c2dc24911e275"
    sha1-hmac-file     "de7c9b85b8b78aa6bc8a7a36f70a90701c9db4d9"
    sha256-hmac-file   "f7bc83f430538424b13298e6aa6fb143ef4d59a14946175997479dbc2d1a3cd8"
    sha384-hmac-file   "d7f4727e2c0b39ae0f1e40cc96f60242d5b7801841cea6fc592c5d3e1ae50700582a96cf35e1e554995fe4e03381c237"
    sha512-hmac-file   "b42af09057bac1e2d41708e48a902e09b5ff7f12ab428a4fe86653c73dd248fb82f948a549f7b791a5b41915ee4d1ec3935357e4e2317250d0372afa2ebeeb3a"))
