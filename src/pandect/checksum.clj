(ns ^{:doc "Checksum Implementations"
      :author "Yannick Scherer"}
  pandect.checksum
  (:use pandect.hashable)
  (:import [java.util.zip Adler32 CRC32]
           [java.nio ByteBuffer]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

;; ## Adler32

(defmethod create-digest "ADLER-32"
  [_]
  (Adler32.))

(extend-type Adler32
  Digest
  (reset-digest! [this]
    (.reset this))
  (update-digest! [this data offset length]
    (.update this ^"[B" data offset length))
  (read-digest! [this]
    (let [v (int (.getValue this))]
      (-> (ByteBuffer/allocate 4)
        (.putInt v)
        (.array))))
  (byte-digest [this data]
    (doto this
      (reset-digest!)
      (update-digest! data 0 (count data))) 
    (read-digest! this)))

;; ## CRC32

(defmethod create-digest "CRC-32"
  [_]
  (CRC32.))

(extend-type CRC32
  Digest
  (reset-digest! [this]
    (.reset this))
  (update-digest! [this data offset length]
    (.update this ^"[B" data offset length))
  (read-digest! [this]
    (let [v (int (.getValue this))]
      (-> (ByteBuffer/allocate 4)
        (.putInt v)
        (.array))))
  (byte-digest [this data]
    (doto this
      (reset-digest!)
      (update-digest! data 0 (count data))) 
    (read-digest! this)))
