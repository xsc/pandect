(ns pandect.clj-digest-bench
  (:require [pandect.bench :refer [defbench]]))

(defbench clj-digest
  {:all
   {:md5      digest/md5
    :sha1     digest/sha-1
    :sha256   digest/sha-256
    :sha384   digest/sha-384
    :sha512   digest/sha-512
    :sha3-256 digest/sha3-256
    :sha3-384 digest/sha3-384
    :sha3-512 digest/sha3-512}})
