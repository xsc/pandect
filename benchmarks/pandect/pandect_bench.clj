(ns pandect.pandect-bench
  (:require [pandect.bench :refer [defbench]]))


(defbench pandect
  {:all
   {:blake2b-160 pandect.algo.blake2b-160/blake2b-160-bytes
    :blake2b-256 pandect.algo.blake2b-256/blake2b-256-bytes
    :blake2b-512 pandect.algo.blake2b-512/blake2b-512-bytes
    :md5         pandect.algo.md5/md5-bytes
    :sha1        pandect.algo.sha1/sha1-bytes
    :sha256      pandect.algo.sha256/sha256-bytes
    :sha384      pandect.algo.sha384/sha384-bytes
    :sha512      pandect.algo.sha512/sha512-bytes
    :sha3-256    pandect.algo.sha3-256/sha3-256-bytes
    :sha3-384    pandect.algo.sha3-384/sha3-384-bytes
    :sha3-512    pandect.algo.sha3-512/sha3-512-bytes}})
