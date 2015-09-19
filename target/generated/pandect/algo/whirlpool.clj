(ns
 pandect.algo.whirlpool
 "Whirlpool algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1054 (compute-whirlpool1052 [data1053]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1054 #'compute-whirlpool1052]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1054
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-whirlpool1052
    [data1053]
    (clojure.core/let
     [buf__882__auto__
      data1053
      digest__883__auto__
      (new org.bouncycastle.crypto.digests.WhirlpoolDigest)
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
   (compute-whirlpool1052
    [data1053]
    (clojure.core/let
     [data1053 (.getBytes data1053 "UTF-8")]
     (clojure.core/let
      [buf__882__auto__
       data1053
       digest__883__auto__
       (new org.bouncycastle.crypto.digests.WhirlpoolDigest)
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
   G__1054
   java.io.InputStream
   (compute-whirlpool1052
    [data1053]
    (clojure.core/let
     [s__886__auto__
      data1053
      c__887__auto__
      (clojure.core/int *buffer-size*)
      digest__888__auto__
      (new org.bouncycastle.crypto.digests.WhirlpoolDigest)
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
   (compute-whirlpool1052
    [data1053]
    (clojure.core/with-open
     [data1053 (clojure.java.io/input-stream data1053)]
     (clojure.core/let
      [s__886__auto__
       data1053
       c__887__auto__
       (clojure.core/int *buffer-size*)
       digest__888__auto__
       (new org.bouncycastle.crypto.digests.WhirlpoolDigest)
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
  'G__1054)
 (do
  (clojure.core/defn
   whirlpool*
   "[Hash] Whirlpool (raw value)"
   [x]
   (compute-whirlpool1052 x))
  (clojure.core/defn
   whirlpool-file*
   "[Hash] Whirlpool (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-whirlpool1052 x)))
  (clojure.core/defn
   whirlpool-bytes
   "[Hash] Whirlpool (value -> byte array)"
   [x]
   (compute-whirlpool1052 x))
  (clojure.core/defn
   whirlpool-file-bytes
   "[Hash] Whirlpool (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-whirlpool1052 x)))
  (clojure.core/defn
   whirlpool
   "[Hash] Whirlpool (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-whirlpool1052 x)))
  (clojure.core/defn
   whirlpool-file
   "[Hash] Whirlpool (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-whirlpool1052 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1058
   (compute-whirlpool1055 [data1056 key1057]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1058 #'compute-whirlpool1055]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1058
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-whirlpool1055
    [data1056 key1057]
    (clojure.core/let
     [buf__869__auto__
      data1056
      k__870__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key1057)
      mac__871__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.WhirlpoolDigest))
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
   (compute-whirlpool1055
    [data1056 key1057]
    (clojure.core/let
     [data1056 (.getBytes data1056 "UTF-8")]
     (clojure.core/let
      [buf__869__auto__
       data1056
       k__870__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key1057)
       mac__871__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.WhirlpoolDigest))
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
   G__1058
   java.io.InputStream
   (compute-whirlpool1055
    [data1056 key1057]
    (clojure.core/let
     [s__874__auto__
      data1056
      c__875__auto__
      (clojure.core/int *buffer-size*)
      mac__876__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.WhirlpoolDigest))
      k__877__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key1057)
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
   (compute-whirlpool1055
    [data1056 key1057]
    (clojure.core/with-open
     [data1056 (clojure.java.io/input-stream data1056)]
     (clojure.core/let
      [s__874__auto__
       data1056
       c__875__auto__
       (clojure.core/int *buffer-size*)
       mac__876__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.WhirlpoolDigest))
       k__877__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key1057)
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
  'G__1058)
 (do
  (clojure.core/defn
   whirlpool-hmac*
   "[HMAC] Whirlpool (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-whirlpool1055
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   whirlpool-hmac-file*
   "[HMAC] Whirlpool (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-whirlpool1055
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   whirlpool-hmac-bytes
   "[HMAC] Whirlpool (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-whirlpool1055
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   whirlpool-hmac-file-bytes
   "[HMAC] Whirlpool (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-whirlpool1055
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   whirlpool-hmac
   "[HMAC] Whirlpool (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-whirlpool1055
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   whirlpool-hmac-file
   "[HMAC] Whirlpool (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-whirlpool1055
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
