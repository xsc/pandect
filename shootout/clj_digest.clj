(ns clj-digest
  (:require digest)
  (:use base
        [clojure.java.io :only [as-file]]))

(defbench
  "--md2"    [digest/md2     (comp digest/md2 as-file)]
  "--md5"    [digest/md5     (comp digest/md5 as-file)]
  "--sha1"   [digest/sha-1   (comp digest/sha-1 as-file)]
  "--sha256" [digest/sha-256 (comp digest/sha-256 as-file)]
  "--sha384" [digest/sha-384 (comp digest/sha-384 as-file)]
  "--sha512" [digest/sha-512 (comp digest/sha-512 as-file)])
