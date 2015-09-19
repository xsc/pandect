(ns
 pandect.algo.md4
 "MD4 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1149 (compute-md41147 [data1148]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1149 #'compute-md41147]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1149
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md41147
    [data1148]
    (clojure.core/let
     [buf__882__auto__
      data1148
      digest__883__auto__
      (new org.bouncycastle.crypto.digests.MD4Digest)
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
   (compute-md41147
    [data1148]
    (clojure.core/let
     [data1148 (.getBytes data1148 "UTF-8")]
     (clojure.core/let
      [buf__882__auto__
       data1148
       digest__883__auto__
       (new org.bouncycastle.crypto.digests.MD4Digest)
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
   G__1149
   java.io.InputStream
   (compute-md41147
    [data1148]
    (clojure.core/let
     [s__886__auto__
      data1148
      c__887__auto__
      (clojure.core/int *buffer-size*)
      digest__888__auto__
      (new org.bouncycastle.crypto.digests.MD4Digest)
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
   (compute-md41147
    [data1148]
    (clojure.core/with-open
     [data1148 (clojure.java.io/input-stream data1148)]
     (clojure.core/let
      [s__886__auto__
       data1148
       c__887__auto__
       (clojure.core/int *buffer-size*)
       digest__888__auto__
       (new org.bouncycastle.crypto.digests.MD4Digest)
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
  'G__1149)
 (do
  (clojure.core/defn
   md4*
   "[Hash] MD4 (raw value)"
   [x]
   (compute-md41147 x))
  (clojure.core/defn
   md4-file*
   "[Hash] MD4 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md41147 x)))
  (clojure.core/defn
   md4-bytes
   "[Hash] MD4 (value -> byte array)"
   [x]
   (compute-md41147 x))
  (clojure.core/defn
   md4-file-bytes
   "[Hash] MD4 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md41147 x)))
  (clojure.core/defn
   md4
   "[Hash] MD4 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-md41147 x)))
  (clojure.core/defn
   md4-file
   "[Hash] MD4 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-md41147 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1153
   (compute-md41150 [data1151 key1152]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1153 #'compute-md41150]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1153
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md41150
    [data1151 key1152]
    (clojure.core/let
     [buf__869__auto__
      data1151
      k__870__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key1152)
      mac__871__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.MD4Digest))
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
   (compute-md41150
    [data1151 key1152]
    (clojure.core/let
     [data1151 (.getBytes data1151 "UTF-8")]
     (clojure.core/let
      [buf__869__auto__
       data1151
       k__870__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key1152)
       mac__871__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.MD4Digest))
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
   G__1153
   java.io.InputStream
   (compute-md41150
    [data1151 key1152]
    (clojure.core/let
     [s__874__auto__
      data1151
      c__875__auto__
      (clojure.core/int *buffer-size*)
      mac__876__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.MD4Digest))
      k__877__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key1152)
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
   (compute-md41150
    [data1151 key1152]
    (clojure.core/with-open
     [data1151 (clojure.java.io/input-stream data1151)]
     (clojure.core/let
      [s__874__auto__
       data1151
       c__875__auto__
       (clojure.core/int *buffer-size*)
       mac__876__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.MD4Digest))
       k__877__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key1152)
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
  'G__1153)
 (do
  (clojure.core/defn
   md4-hmac*
   "[HMAC] MD4 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-md41150
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   md4-hmac-file*
   "[HMAC] MD4 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md41150
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   md4-hmac-bytes
   "[HMAC] MD4 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-md41150
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   md4-hmac-file-bytes
   "[HMAC] MD4 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md41150
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   md4-hmac
   "[HMAC] MD4 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-md41150
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   md4-hmac-file
   "[HMAC] MD4 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-md41150
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
