(ns ^{:doc "General Digest Abstraction"
      :author "Yannick Scherer"}
  pandect.hashable
  (:import [java.security MessageDigest DigestInputStream]
           [java.io InputStream FileInputStream File]))

(defprotocol Hashable
  "Protocol for Entities a Digest can be derived from."
  (digest ^"[B" [this algorithm]
    "Compute Digest from the given Entity using the given Algorithm."))

(defprotocol Digest
  "Protocol for Digests."
  (reset-digest! [this]
    "Reset Digest IV.")
  (update-digest! [this data offset length]
    "Add data to Digest buffer.")
  (read-digest! ^"[B" [this]
    "Get result from Digest.")
  (byte-digest ^"[B" [this data]
    "Directly compute Digest for the given Byte Array."))

(defmulti create-digest
  "Create entity implementing Protocol `Digest` based on an algorithm string."
  (fn [algorithm] algorithm)
  :default nil)

;; ## Generic Implementation

(extend-protocol Hashable

  (class (byte-array 0))
  (digest [this algorithm]
    (let [md (create-digest algorithm)]
      (byte-digest md this)))

  String
  (digest [this algorithm]
    (let [^"[B" data (.getBytes this)]
      (digest data algorithm)))

  InputStream
  (digest [this algorithm]
    (let [md (create-digest algorithm)
          ^"[B" buffer (byte-array 2048)] 
      #_(reset-digest! md)
      (loop []
        (let [read-length (.read this buffer 0 2048)]
          (when-not (= read-length -1)
            (update-digest! md buffer 0 read-length)
            (recur))))
      (read-digest! md)))

  File
  (digest [this algorithm]
    (with-open [in (FileInputStream. this)]
      (digest in algorithm)))
  
  nil
  (digest [this algorithm]
    nil))
