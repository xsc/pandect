(ns ^{:doc "MessageDigest Implementations"
      :author "Yannick Scherer"}
  pandect.message-digest
  (:use pandect.hashable)
  (:import [java.security MessageDigest DigestInputStream]
           [java.io InputStream FileInputStream File]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

;; ## Make MessageDigest available

(extend-type MessageDigest
  Digest
  (reset-digest! [this]
    (.reset ^MessageDigest this))
  (update-digest! [this data offset length]
    (.update ^MessageDigest this ^"[B" data offset length))
  (read-digest! [this]
    (.digest this)))

;; ## Register Algorithms

(defmacro ^:private init-message-digest
  []
  `(do
     ~@(for [algorithm ["MD2" "MD5" "SHA-1" "SHA-256" "SHA-384" "SHA-512"]]
         `(defmethod create-digest ~algorithm
            [_#]
            (MessageDigest/getInstance ~algorithm)))))

(init-message-digest)
