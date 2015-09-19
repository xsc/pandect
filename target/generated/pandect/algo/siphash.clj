(ns
 pandect.algo.siphash
 "SipHash-2-4 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol
   G__1069
   (compute-siphash1066 [data1067 key1068]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1069 #'compute-siphash1066]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1069
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-siphash1066
    [data1067 key1068]
    (clojure.core/let
     [buf__869__auto__
      data1067
      k__870__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key1068)
      mac__871__auto__
      (new org.bouncycastle.crypto.macs.SipHash)
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
   (compute-siphash1066
    [data1067 key1068]
    (clojure.core/let
     [data1067 (.getBytes data1067 "UTF-8")]
     (clojure.core/let
      [buf__869__auto__
       data1067
       k__870__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key1068)
       mac__871__auto__
       (new org.bouncycastle.crypto.macs.SipHash)
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
   G__1069
   java.io.InputStream
   (compute-siphash1066
    [data1067 key1068]
    (clojure.core/let
     [s__874__auto__
      data1067
      c__875__auto__
      (clojure.core/int *buffer-size*)
      mac__876__auto__
      (new org.bouncycastle.crypto.macs.SipHash)
      k__877__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key1068)
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
   (compute-siphash1066
    [data1067 key1068]
    (clojure.core/with-open
     [data1067 (clojure.java.io/input-stream data1067)]
     (clojure.core/let
      [s__874__auto__
       data1067
       c__875__auto__
       (clojure.core/int *buffer-size*)
       mac__876__auto__
       (new org.bouncycastle.crypto.macs.SipHash)
       k__877__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key1068)
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
  'G__1069)
 (do
  (clojure.core/defn
   siphash*
   "[HMAC] SipHash-2-4 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-siphash1066
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   siphash-file*
   "[HMAC] SipHash-2-4 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-siphash1066
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   siphash-bytes
   "[HMAC] SipHash-2-4 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-siphash1066
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   siphash-file-bytes
   "[HMAC] SipHash-2-4 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-siphash1066
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   siphash
   "[HMAC] SipHash-2-4 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-siphash1066
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   siphash-file
   "[HMAC] SipHash-2-4 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-siphash1066
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
