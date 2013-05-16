(ns 
  pandect.hashable
  (:import [java.security MessageDigest DigestInputStream]
           [java.io InputStream FileInputStream File]))

(defprotocol Hashable
  "Protocol for Entities a Digest can be derived from."
  (digest ^"[B" [this algorithm]
    "Compute Digest from the given Entity using the given Algorithm."))

(defprotocol Digest
  "Protocol for Digests."
  (reset-digest! [this])
  (update-digest! [this data offset length])
  (read-digest! [this]))

(defmulti create-digest
  (fn [algorithm] algorithm)
  :default nil)

;; ## Generic Implementation

(extend-protocol Hashable

  (class (byte-array 0))
  (digest [this algorithm]
    (let [md (create-digest algorithm)]
      (reset-digest! md)
      (update-digest! md this 0 (count this))
      (read-digest! md)))

  String
  (digest [this algorithm]
    (let [^"[B" data (.getBytes this)]
      (digest data algorithm)))

  InputStream
  (digest [this algorithm]
    (let [md (create-digest algorithm)
          ^"[B" buffer (byte-array 2048)] 
      (reset-digest! md)
      (loop []
        (let [read-length (.read this buffer 0 2048)]
          (when-not (= read-length -1)
            (update-digest! md buffer 0 read-length))))
      (read-digest! md)))

  File
  (digest [this algorithm]
    (with-open [in (FileInputStream. this)]
      (digest in algorithm)))
  
  nil
  (digest [this algorithm]
    nil))
