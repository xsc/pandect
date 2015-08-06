(ns ^{:doc "Conversion Utilities"
      :author "Yannick Scherer"}
  pandect.utils.convert
  (:require [clojure.java.io :as io]))

(set! *unchecked-math* true)

;; ## Byte-Array to Hex-String

(def ^:private ^"[B" hex-chars
  (byte-array (.getBytes "0123456789abcdef" "UTF-8")))

(defn bytes->hex
  "Convert Byte Array to Hex String"
  ^String
  [^"[B" data]
  (let [len (alength data)
        ^"[B" buffer (byte-array (* 2 len))]
    (loop [i 0]
      (when (< i len)
        (let [b (aget data i)]
          (aset buffer (* 2 i) (aget hex-chars (bit-shift-right (bit-and b 0xF0) 4)))
          (aset buffer (inc (* 2 i)) (aget hex-chars (bit-and b 0x0F))))
        (recur (inc i))))
    (String. buffer "UTF-8")))

;; ## Unsigned Int (as Long) to Byte Array

(defmacro long->4-bytes
  "Convert an unsigned 32-bit integer (given as long) to a 4-byte array."
  [v]
  `(let [v# (long ~v)]
     (doto (byte-array 4)
      (aset 0 (byte (bit-and (bit-shift-right v# 24) 0xFF)))
      (aset 1 (byte (bit-and (bit-shift-right v# 16) 0xFF)))
      (aset 2 (byte (bit-and (bit-shift-right v# 8) 0xFF)))
      (aset 3 (byte (bit-and v# 0xFF))))))

;; ## Unsigned Int (as Long) to String

(defn long->hex
  "Convert an unsigned 32-bit integer (given as long) to a 8-char hexstring."
  ^String
  [b]
  (let [buf (byte-array 8)]
    (loop [i 7
           n (long b)]
      (when (>= i 0)
        (aset buf i (aget hex-chars (bit-and 0xF n)))
        (recur (dec i) (bit-shift-right n 4))))
    (String. buf "UTF-8")))

;; ## Byte Array from Source

(defn slurp-bytes
  "Get bytes from the given source."
  [source]
  (with-open [in (io/input-stream source)]
    (with-open [out (java.io.ByteArrayOutputStream.)]
      (io/copy in out)
      (.toByteArray out))))
