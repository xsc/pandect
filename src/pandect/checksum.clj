(ns ^{:doc "Checksum Implementations"
      :author "Yannick Scherer"}
  pandect.checksum
  (:use pandect.hashable)
  (:import [java.util.zip Adler32 CRC32]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

;; ## Long to 4 Bytes

(defmacro ^:private long->4-bytes
  "Convert an unsigned 32-bit integer (given as long) to a 4-byte array."
  [v]
  `(let [v# (long ~v)]
     (doto (byte-array 4)
      (aset 0 (byte (bit-and (bit-shift-right v# 24) 0xFF)))
      (aset 1 (byte (bit-and (bit-shift-right v# 16) 0xFF)))
      (aset 2 (byte (bit-and (bit-shift-right v# 8) 0xFF)))
      (aset 3 (byte (bit-and v# 0xFF))))))

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
    (long->4-bytes (.getValue this)))
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
    (long->4-bytes (.getValue this)))
  (byte-digest [this data]
    (doto this
      (reset-digest!)
      (update-digest! data 0 (count data))) 
    (read-digest! this)))
