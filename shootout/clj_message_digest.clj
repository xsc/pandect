(ns clj-message-digest
  (:require clj-message-digest.core)
  (:use base
        [clojure.java.io :only [as-file]]))

(defbench
  "--md2"    [clj-message-digest.core/md2-hex     (comp clj-message-digest.core/md2-hex as-file)]
  "--md5"    [clj-message-digest.core/md5-hex     (comp clj-message-digest.core/md5-hex as-file)]
  "--sha1"   [clj-message-digest.core/sha-1-hex   (comp clj-message-digest.core/sha-1-hex as-file)]
  "--sha256" [clj-message-digest.core/sha-256-hex (comp clj-message-digest.core/sha-256-hex as-file)]
  "--sha384" [clj-message-digest.core/sha-384-hex (comp clj-message-digest.core/sha-384-hex as-file)]
  "--sha512" [clj-message-digest.core/sha-512-hex (comp clj-message-digest.core/sha-512-hex as-file)])
