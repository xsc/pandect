(ns pandect.buddy-bench
  (:require [pandect.bench :refer [defbench]]))

(defbench buddy
  {:all
   {:blake2b-128 buddy.core.hash/blake2b-128
    :blake2b-256 buddy.core.hash/blake2b-256
    :blake2b-512 buddy.core.hash/blake2b-512
    :md5         buddy.core.hash/md5
    :sha1        buddy.core.hash/sha1
    :sha256      buddy.core.hash/sha256
    :sha384      buddy.core.hash/sha384
    :sha512      buddy.core.hash/sha512
    :sha3-256    buddy.core.hash/sha3-256
    :sha3-384    buddy.core.hash/sha3-384
    :sha3-512    buddy.core.hash/sha3-512}})
