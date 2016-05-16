(ns pandect.hmac-test
  (:import java.io.File)
  (:require [clojure.test :refer :all]
            [pandect.core :refer :all]
            [clojure.java.io :as io]))

(def test-string "The quick brown fox jumps over the lazy dog")
(def test-key "key")

(let [inputs {:string identity
              :bytes  #(.getBytes % "UTF-8")
              :stream #(java.io.ByteArrayInputStream. (.getBytes % "UTF-8"))}]
  (deftest t-hmac-algorithms
    (are [algorithm result]
         (do
           (doseq [[kv value-input]  inputs
                   [ks secret-input] inputs]
             (is (= result (algorithm
                             (value-input test-string)
                             (secret-input test-key)))
                 (str "input was: " kv "/" ks)))
           true)

         gost-hmac       "e06ac9388fa2107fa7bb49d6b29c28a09a2c0cde316cd349a12bb4b0d3497370"
         md4-hmac        "8d3366c440a9c65124ab0b5f4ca27338"
         md5-hmac        "80070713463e7749b90c2dc24911e275"
         sha1-hmac       "de7c9b85b8b78aa6bc8a7a36f70a90701c9db4d9"
         sha224-hmac     "88ff8b54675d39b8f72322e65ff945c52d96379988ada25639747e69"
         sha256-hmac     "f7bc83f430538424b13298e6aa6fb143ef4d59a14946175997479dbc2d1a3cd8"
         sha384-hmac     "d7f4727e2c0b39ae0f1e40cc96f60242d5b7801841cea6fc592c5d3e1ae50700582a96cf35e1e554995fe4e03381c237"
         sha512-hmac     "b42af09057bac1e2d41708e48a902e09b5ff7f12ab428a4fe86653c73dd248fb82f948a549f7b791a5b41915ee4d1ec3935357e4e2317250d0372afa2ebeeb3a"
         keccak-224-hmac "763e70a1ec866fbc1c6e6c398cd6e2383e2ad3aecbb3d6150f1e56fd"
         keccak-256-hmac "74547bc8c8e1ef02aec834ca60ff24cc316d4c2244a360fe17448cb53410bed4"
         keccak-384-hmac "73acb07b5b1db5431758262b55e5923d362de4492229a7420302c80d4348ca1b11ecea06fb1c232f9b832aadca8cd289"
         keccak-512-hmac "22fb03b3391bc0adfc73c18e0919d9f142390e81d6cc2689716ac53ab75458a718059d58cfbb23c6a416c32b8afa84a9a7a9d852312a743bef0a55148e5a1b8a"
         ripemd128-hmac  "ea830b2f823e559e753aecfa22cf666c"
         ripemd160-hmac  "50278a77d4d7670561ab72e867383aef6ce50b3e"
         ripemd256-hmac  "39f102599868d204bbf6165139f79eaa856a75cf92d785492907e2fee4168097"
         ripemd320-hmac  "dfca8756189fc556323fb344001a927c161f83a9d8f402d092c537346ae977113c4d02cca757a7ad"
         tiger-hmac      "fba544227e1471d8d47dd9d68c5008d554c6de6072ca2e2a"
         whirlpool-hmac  "7f7192e3a155cb6a8171584ba146882f26821658112dfd2601272db013517a31e573637d146584596f86a884eb0decc9514dde000ecf2476dc5d436a92197527"))

  (deftest t-hmac-algorithms-with-128bit-keys
    (let [key-128bit (apply str (take 16 (cycle (range 10))))]
      (are [algorithm result]
           (do
             (doseq [[kv value-input]  inputs
                     [ks secret-input] inputs]
               (is (= result (algorithm
                               (value-input test-string)
                               (secret-input key-128bit)))
                   (str "input was: " kv "/" ks)))
             true)
           siphash     "654cd7fbec56953a"
           siphash48   "647ddd16789a2819"))))
