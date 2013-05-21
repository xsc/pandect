(ns ^ {:doc "CRC32 Implementation"
       :author "Yannick Scherer"}
  pandect.gen.crc32
  (:use pandect.gen.core)
  (:require [pandect.utils.convert :as c only [long->4-bytes]]))

(set! *unchecked-math* true)

;; ## Reference Implementation
;;
;; Based on the Reference Implementation given here:
;; http://www.w3.org/TR/PNG-CRCAppendix.html

;; ## Implementation

(def ^:private ^"[J" CRC_TABLE 
  "CRCs for all 8-bit messages."
  (let [C (long 0xEDB88320)] 
    (->> 
      (for [n (range 256)]
        (reduce
          (fn [c k]
            (if (not= (bit-and c 1) 0)
              (bit-xor C (bit-shift-right c 1))
              (bit-shift-right c 1)))
          n (range 8)))
      (long-array))))

(defn crc-update
  "Update a CRC-32 checksum with the contents of the given Buffer."
  (^long [^long crc ^"[B" buffer]
   (crc-update crc buffer (count buffer)))
  (^long [^long crc ^"[B" buffer ^long length]
   (loop [n 0
          c crc]
     (if (< n length)
       (recur
         (inc n)
         (bit-xor
           (bit-shift-right c 8)
           (aget CRC_TABLE 
                 (bit-and 0xFF (bit-xor c (aget buffer n))))))
       c))))

(defn crc32
  "Create the CRC-32 checksum of the given Buffer."
  ([^"[B" buffer] (crc32 buffer (count buffer)))
  ([^"[B" buffer ^long length]
  (bit-xor 
    (long 0xFFFFFFFF) 
    (crc-update (long 0xFFFFFFFF) buffer length))))

;; ## Code Generator

(deftype CRC32CodeGen []
  CodeGen
  (algorithm-string [_] "CRC-32")
  (bytes->hash [_ form]
    `(crc32 ~form))
  (stream->hash [_ form]
    `(let [s# ~form
           buf# (byte-array 2048)]
       (loop [crc# (long 0xFFFFFFFF)]
         (let [r# (.read s# buf# 0 2048)]
           (if (= r# -1)
             (bit-xor 0xFFFFFFFF crc#) 
             (recur (crc-update crc# buf# r#)))))))
  (hash->bytes [_ form]
    `(c/long->4-bytes ~form))
  (hash->string [_ form]
    `(Long/toString (long ~form) 16)))

(defmethod code-generator "CRC-32"
  [_]
  (CRC32CodeGen.))
