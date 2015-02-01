(ns
 pandect.algo.crc32
 "CRC-32 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__729
   (compute-crc32727 [this__228__auto__]))
  (clojure.core/doseq
   [v__229__auto__ [#'G__729 #'compute-crc32727]]
   (clojure.core/alter-meta!
    v__229__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__729
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-crc32727
    [data728]
    (clojure.core/let
     [buf__467__auto__
      data728
      a__468__auto__
      (new java.util.zip.CRC32)]
     (.update
      a__468__auto__
      buf__467__auto__
      0
      (clojure.core/count buf__467__auto__))
     (.getValue a__468__auto__)))
   java.lang.String
   (compute-crc32727
    [data728]
    (clojure.core/let
     [buf__467__auto__
      (.getBytes data728 "UTF-8")
      a__468__auto__
      (new java.util.zip.CRC32)]
     (.update
      a__468__auto__
      buf__467__auto__
      0
      (clojure.core/count buf__467__auto__))
     (.getValue a__468__auto__)))
   java.io.InputStream
   (compute-crc32727
    [data728]
    (clojure.core/let
     [s__469__auto__
      data728
      c__470__auto__
      (clojure.core/int *buffer-size*)
      buf__471__auto__
      (clojure.core/byte-array c__470__auto__)
      a__472__auto__
      (new java.util.zip.CRC32)]
     (clojure.core/loop
      []
      (clojure.core/let
       [r__473__auto__
        (.read s__469__auto__ buf__471__auto__ 0 c__470__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__473__auto__ -1)
        (.update a__472__auto__ buf__471__auto__ 0 r__473__auto__)
        (recur))))
     (.getValue a__472__auto__)))
   java.io.File
   (compute-crc32727
    [data728]
    (clojure.core/with-open
     [data728
      (java.io.FileInputStream. (clojure.java.io/as-file data728))]
     (clojure.core/let
      [s__469__auto__
       data728
       c__470__auto__
       (clojure.core/int *buffer-size*)
       buf__471__auto__
       (clojure.core/byte-array c__470__auto__)
       a__472__auto__
       (new java.util.zip.CRC32)]
      (clojure.core/loop
       []
       (clojure.core/let
        [r__473__auto__
         (.read s__469__auto__ buf__471__auto__ 0 c__470__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__473__auto__ -1)
         (.update a__472__auto__ buf__471__auto__ 0 r__473__auto__)
         (recur))))
      (.getValue a__472__auto__))))))
 [(clojure.core/defn
   crc32*
   "[Hash] CRC-32 (raw value)"
   [x]
   (compute-crc32727 x))
  (clojure.core/defn
   crc32-file*
   "[Hash] CRC-32 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-crc32727 x)))
  (clojure.core/defn
   crc32-file-bytes
   "[Hash] CRC-32 (file path -> byte array)"
   [x]
   (pandect.utils.convert/long->4-bytes
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-crc32727 x))))
  (clojure.core/defn
   crc32-file
   "[Hash] CRC-32 (file path -> string)"
   [x]
   (pandect.utils.convert/long->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-crc32727 x))))
  (clojure.core/defn
   crc32-bytes
   "[Hash] CRC-32 (value -> byte array)"
   [x]
   (pandect.utils.convert/long->4-bytes (compute-crc32727 x)))
  (clojure.core/defn
   crc32
   "[Hash] CRC-32 (value -> string)"
   [x]
   (pandect.utils.convert/long->hex (compute-crc32727 x)))])
