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
    (.digest ^MessageDigest this))
  (byte-digest [this data]
    (.digest ^MessageDigest this ^"[B" data)))

;; ## Register Algorithms

(defmacro ^:private init-message-digest
  "Create `create-digest` multimethods for the algorithms supported
   by MessageDigest."
  []
  `(do
     ~@(for [algorithm ["MD2" "MD5" "SHA-1" "SHA-256" "SHA-384" "SHA-512"]]
         `(defmethod create-digest ~algorithm
            [_#]
            (MessageDigest/getInstance ~algorithm)))))

(init-message-digest)
