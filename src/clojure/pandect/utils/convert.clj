(ns ^{:doc "Conversion Utilities"
      :author "Yannick Scherer"}
  pandect.utils.convert
  (:require [clojure.java.io :as io]))

(set! *unchecked-math* true)

;; ## Casts

(defn as-public-key
  ^java.security.PublicKey
  [k]
  k)

(defn as-certificate
  ^java.security.cert.Certificate
  [k]
  k)

;; ## Byte-Array to Hex-String

(def ^:private ^"[B" hex-chars
  (byte-array (.getBytes "0123456789abcdef" "UTF-8")))

(def ^:private hex-val
  (zipmap "0123456789abcdef" (range 16)))

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

(defn hex->bytes
  "Convert Hex String to Byte Array"
  ^"[B"
  [^String data]
  (let [data (.toLowerCase data)
        len (.length data)
        result (byte-array (quot len 2))]
    (loop [i 0]
      (if (< i len)
        (let [a (.charAt data i)
              b (.charAt data (inc i))
              v (+ (* (hex-val a) 16) (hex-val b))]
          (aset result (quot i 2) (byte v))
          (recur (+ i 2)))))
    result))

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

;; ## Bytes

(defprotocol ByteArrayConvertable
  "Protocol for Entities that can be converted to byte arrays."
  (convert-to-byte-array [this]))

(extend-protocol ByteArrayConvertable
  (class (byte-array 0))
  (convert-to-byte-array [this] this)
  String
  (convert-to-byte-array [this] (.getBytes this "UTF-8"))
  java.io.File
  (convert-to-byte-array [this]
    (slurp-bytes this))
  java.io.InputStream
  (convert-to-byte-array [this]
    (slurp-bytes this)))

(defn convert-signature-to-byte-array
  ^"[B"
  [value]
  (if (string? value)
    (hex->bytes value)
    (convert-to-byte-array value)))
