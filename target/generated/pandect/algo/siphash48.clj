(ns
 pandect.algo.siphash48
 "SipHash-4-8 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol
   G__1029
   (compute-siphash481026 [data1027 key1028]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1029 #'compute-siphash481026]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1029
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-siphash481026
    [data1027 key1028]
    (clojure.core/let
     [buf__869__auto__
      data1027
      k__870__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key1028)
      mac__871__auto__
      (new org.bouncycastle.crypto.macs.SipHash 4 8)
      result__872__auto__
      (clojure.core/byte-array (.getMacSize mac__871__auto__))]
     (clojure.core/doto
      mac__871__auto__
      (.init k__870__auto__)
      (.update
       buf__869__auto__
       0
       (clojure.core/alength (clojure.core/bytes buf__869__auto__)))
      (.doFinal result__872__auto__ 0))
     result__872__auto__))
   java.lang.String
   (compute-siphash481026
    [data1027 key1028]
    (clojure.core/let
     [data1027 (.getBytes data1027 "UTF-8")]
     (clojure.core/let
      [buf__869__auto__
       data1027
       k__870__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key1028)
       mac__871__auto__
       (new org.bouncycastle.crypto.macs.SipHash 4 8)
       result__872__auto__
       (clojure.core/byte-array (.getMacSize mac__871__auto__))]
      (clojure.core/doto
       mac__871__auto__
       (.init k__870__auto__)
       (.update
        buf__869__auto__
        0
        (clojure.core/alength (clojure.core/bytes buf__869__auto__)))
       (.doFinal result__872__auto__ 0))
      result__872__auto__))))
  (clojure.core/extend-protocol
   G__1029
   java.io.InputStream
   (compute-siphash481026
    [data1027 key1028]
    (clojure.core/let
     [s__874__auto__
      data1027
      c__875__auto__
      (clojure.core/int *buffer-size*)
      mac__876__auto__
      (new org.bouncycastle.crypto.macs.SipHash 4 8)
      k__877__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key1028)
      buf__878__auto__
      (clojure.core/byte-array c__875__auto__)]
     (.init mac__876__auto__ k__877__auto__)
     (clojure.core/loop
      []
      (clojure.core/let
       [r__879__auto__
        (.read s__874__auto__ buf__878__auto__ 0 c__875__auto__)]
       (clojure.core/when
        (clojure.core/not= r__879__auto__ -1)
        (.update mac__876__auto__ buf__878__auto__ 0 r__879__auto__)
        (recur))))
     (clojure.core/let
      [result__880__auto__
       (clojure.core/byte-array (.getMacSize mac__876__auto__))]
      (.doFinal mac__876__auto__ result__880__auto__ 0)
      result__880__auto__)))
   java.io.File
   (compute-siphash481026
    [data1027 key1028]
    (clojure.core/with-open
     [data1027 (clojure.java.io/input-stream data1027)]
     (clojure.core/let
      [s__874__auto__
       data1027
       c__875__auto__
       (clojure.core/int *buffer-size*)
       mac__876__auto__
       (new org.bouncycastle.crypto.macs.SipHash 4 8)
       k__877__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key1028)
       buf__878__auto__
       (clojure.core/byte-array c__875__auto__)]
      (.init mac__876__auto__ k__877__auto__)
      (clojure.core/loop
       []
       (clojure.core/let
        [r__879__auto__
         (.read s__874__auto__ buf__878__auto__ 0 c__875__auto__)]
        (clojure.core/when
         (clojure.core/not= r__879__auto__ -1)
         (.update mac__876__auto__ buf__878__auto__ 0 r__879__auto__)
         (recur))))
      (clojure.core/let
       [result__880__auto__
        (clojure.core/byte-array (.getMacSize mac__876__auto__))]
       (.doFinal mac__876__auto__ result__880__auto__ 0)
       result__880__auto__)))))
  'G__1029)
 (do
  (clojure.core/defn
   siphash48*
   "[HMAC] SipHash-4-8 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-siphash481026
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   siphash48-file*
   "[HMAC] SipHash-4-8 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-siphash481026
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   siphash48-bytes
   "[HMAC] SipHash-4-8 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-siphash481026
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   siphash48-file-bytes
   "[HMAC] SipHash-4-8 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-siphash481026
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   siphash48
   "[HMAC] SipHash-4-8 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-siphash481026
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   siphash48-file
   "[HMAC] SipHash-4-8 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-siphash481026
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
