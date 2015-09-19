(ns
 pandect.algo.gost
 "GOST 34.11-94 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1108 (compute-gost1106 [data1107]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1108 #'compute-gost1106]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1108
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-gost1106
    [data1107]
    (clojure.core/let
     [buf__882__auto__
      data1107
      digest__883__auto__
      (new org.bouncycastle.crypto.digests.GOST3411Digest)
      result__884__auto__
      (clojure.core/byte-array (.getDigestSize digest__883__auto__))]
     (clojure.core/doto
      digest__883__auto__
      (.update
       buf__882__auto__
       0
       (clojure.core/alength (clojure.core/bytes buf__882__auto__)))
      (.doFinal result__884__auto__ 0))
     result__884__auto__))
   java.lang.String
   (compute-gost1106
    [data1107]
    (clojure.core/let
     [data1107 (.getBytes data1107 "UTF-8")]
     (clojure.core/let
      [buf__882__auto__
       data1107
       digest__883__auto__
       (new org.bouncycastle.crypto.digests.GOST3411Digest)
       result__884__auto__
       (clojure.core/byte-array (.getDigestSize digest__883__auto__))]
      (clojure.core/doto
       digest__883__auto__
       (.update
        buf__882__auto__
        0
        (clojure.core/alength (clojure.core/bytes buf__882__auto__)))
       (.doFinal result__884__auto__ 0))
      result__884__auto__))))
  (clojure.core/extend-protocol
   G__1108
   java.io.InputStream
   (compute-gost1106
    [data1107]
    (clojure.core/let
     [s__886__auto__
      data1107
      c__887__auto__
      (clojure.core/int *buffer-size*)
      digest__888__auto__
      (new org.bouncycastle.crypto.digests.GOST3411Digest)
      buf__889__auto__
      (clojure.core/byte-array c__887__auto__)]
     (clojure.core/loop
      []
      (clojure.core/let
       [r__890__auto__
        (.read s__886__auto__ buf__889__auto__ 0 c__887__auto__)]
       (clojure.core/when
        (clojure.core/not= r__890__auto__ -1)
        (.update digest__888__auto__ buf__889__auto__ 0 r__890__auto__)
        (recur))))
     (clojure.core/let
      [result__891__auto__
       (clojure.core/byte-array (.getDigestSize digest__888__auto__))]
      (.doFinal digest__888__auto__ result__891__auto__ 0)
      result__891__auto__)))
   java.io.File
   (compute-gost1106
    [data1107]
    (clojure.core/with-open
     [data1107 (clojure.java.io/input-stream data1107)]
     (clojure.core/let
      [s__886__auto__
       data1107
       c__887__auto__
       (clojure.core/int *buffer-size*)
       digest__888__auto__
       (new org.bouncycastle.crypto.digests.GOST3411Digest)
       buf__889__auto__
       (clojure.core/byte-array c__887__auto__)]
      (clojure.core/loop
       []
       (clojure.core/let
        [r__890__auto__
         (.read s__886__auto__ buf__889__auto__ 0 c__887__auto__)]
        (clojure.core/when
         (clojure.core/not= r__890__auto__ -1)
         (.update
          digest__888__auto__
          buf__889__auto__
          0
          r__890__auto__)
         (recur))))
      (clojure.core/let
       [result__891__auto__
        (clojure.core/byte-array (.getDigestSize digest__888__auto__))]
       (.doFinal digest__888__auto__ result__891__auto__ 0)
       result__891__auto__)))))
  'G__1108)
 (do
  (clojure.core/defn
   gost*
   "[Hash] GOST 34.11-94 (raw value)"
   [x]
   (compute-gost1106 x))
  (clojure.core/defn
   gost-file*
   "[Hash] GOST 34.11-94 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-gost1106 x)))
  (clojure.core/defn
   gost-bytes
   "[Hash] GOST 34.11-94 (value -> byte array)"
   [x]
   (compute-gost1106 x))
  (clojure.core/defn
   gost-file-bytes
   "[Hash] GOST 34.11-94 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-gost1106 x)))
  (clojure.core/defn
   gost
   "[Hash] GOST 34.11-94 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-gost1106 x)))
  (clojure.core/defn
   gost-file
   "[Hash] GOST 34.11-94 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-gost1106 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1112
   (compute-gost1109 [data1110 key1111]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1112 #'compute-gost1109]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1112
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-gost1109
    [data1110 key1111]
    (clojure.core/let
     [buf__869__auto__
      data1110
      k__870__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key1111)
      mac__871__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.GOST3411Digest))
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
   (compute-gost1109
    [data1110 key1111]
    (clojure.core/let
     [data1110 (.getBytes data1110 "UTF-8")]
     (clojure.core/let
      [buf__869__auto__
       data1110
       k__870__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key1111)
       mac__871__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.GOST3411Digest))
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
   G__1112
   java.io.InputStream
   (compute-gost1109
    [data1110 key1111]
    (clojure.core/let
     [s__874__auto__
      data1110
      c__875__auto__
      (clojure.core/int *buffer-size*)
      mac__876__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.GOST3411Digest))
      k__877__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key1111)
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
   (compute-gost1109
    [data1110 key1111]
    (clojure.core/with-open
     [data1110 (clojure.java.io/input-stream data1110)]
     (clojure.core/let
      [s__874__auto__
       data1110
       c__875__auto__
       (clojure.core/int *buffer-size*)
       mac__876__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.GOST3411Digest))
       k__877__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key1111)
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
  'G__1112)
 (do
  (clojure.core/defn
   gost-hmac*
   "[HMAC] GOST 34.11-94 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-gost1109
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   gost-hmac-file*
   "[HMAC] GOST 34.11-94 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-gost1109
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   gost-hmac-bytes
   "[HMAC] GOST 34.11-94 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-gost1109
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   gost-hmac-file-bytes
   "[HMAC] GOST 34.11-94 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-gost1109
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   gost-hmac
   "[HMAC] GOST 34.11-94 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-gost1109
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   gost-hmac-file
   "[HMAC] GOST 34.11-94 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-gost1109
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
