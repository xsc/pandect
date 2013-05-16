(ns ^{:doc "Digest Implementations"
      :author "Yannick Scherer"}
  pandect.hashable
  (:import [java.security MessageDigest DigestInputStream]
           [java.io InputStream FileInputStream File]))

(set! *warn-on-reflection* true)

;; ## Digest Base

(defn- create-message-digest
  "Create MessageDigest instance using the given Algorithm."
  ^MessageDigest
  [^String algorithm]
  (MessageDigest/getInstance algorithm))

;; ## Digest Creation

(defprotocol Hashable
  "Protocol for Entities a Digest can be derived from."
  (digest [this algorithm]
    "Compute Digest from the given Entity using the given Algorithm."))

(extend-protocol Hashable

  (class (byte-array 0))
  (digest [this algorithm]
    (let [md (create-message-digest algorithm)]
      (.digest md this)))

  String
  (digest [this algorithm]
    (let [^"[B" data (.getBytes this)]
      (digest data algorithm)))

  InputStream
  (digest [this algorithm]
    (let [md (create-message-digest algorithm)]
      (with-open [^InputStream ds (DigestInputStream. this md)]
        (let [^"[B" buffer (byte-array 2048)]
          (while (not (= (.read ds buffer 0 2048) -1)) nil)))
      (.digest md)))

  File
  (digest [this algorithm]
    (with-open [in (FileInputStream. this)]
      (digest in algorithm)))
  
  nil
  (digest [this algorithm]
    nil))
