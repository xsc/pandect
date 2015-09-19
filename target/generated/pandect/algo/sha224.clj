(ns
 pandect.algo.sha224
 "SHA-224 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1089 (compute-sha2241087 [data1088]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1089 #'compute-sha2241087]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1089
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha2241087
    [data1088]
    (clojure.core/let
     [buf__882__auto__
      data1088
      digest__883__auto__
      (new org.bouncycastle.crypto.digests.SHA224Digest)
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
   (compute-sha2241087
    [data1088]
    (clojure.core/let
     [data1088 (.getBytes data1088 "UTF-8")]
     (clojure.core/let
      [buf__882__auto__
       data1088
       digest__883__auto__
       (new org.bouncycastle.crypto.digests.SHA224Digest)
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
   G__1089
   java.io.InputStream
   (compute-sha2241087
    [data1088]
    (clojure.core/let
     [s__886__auto__
      data1088
      c__887__auto__
      (clojure.core/int *buffer-size*)
      digest__888__auto__
      (new org.bouncycastle.crypto.digests.SHA224Digest)
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
   (compute-sha2241087
    [data1088]
    (clojure.core/with-open
     [data1088 (clojure.java.io/input-stream data1088)]
     (clojure.core/let
      [s__886__auto__
       data1088
       c__887__auto__
       (clojure.core/int *buffer-size*)
       digest__888__auto__
       (new org.bouncycastle.crypto.digests.SHA224Digest)
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
  'G__1089)
 (do
  (clojure.core/defn
   sha224*
   "[Hash] SHA-224 (raw value)"
   [x]
   (compute-sha2241087 x))
  (clojure.core/defn
   sha224-file*
   "[Hash] SHA-224 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2241087 x)))
  (clojure.core/defn
   sha224-bytes
   "[Hash] SHA-224 (value -> byte array)"
   [x]
   (compute-sha2241087 x))
  (clojure.core/defn
   sha224-file-bytes
   "[Hash] SHA-224 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2241087 x)))
  (clojure.core/defn
   sha224
   "[Hash] SHA-224 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha2241087 x)))
  (clojure.core/defn
   sha224-file
   "[Hash] SHA-224 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-sha2241087 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1093
   (compute-sha2241090 [data1091 key1092]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1093 #'compute-sha2241090]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1093
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha2241090
    [data1091 key1092]
    (clojure.core/let
     [buf__869__auto__
      data1091
      k__870__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key1092)
      mac__871__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.SHA224Digest))
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
   (compute-sha2241090
    [data1091 key1092]
    (clojure.core/let
     [data1091 (.getBytes data1091 "UTF-8")]
     (clojure.core/let
      [buf__869__auto__
       data1091
       k__870__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key1092)
       mac__871__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.SHA224Digest))
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
   G__1093
   java.io.InputStream
   (compute-sha2241090
    [data1091 key1092]
    (clojure.core/let
     [s__874__auto__
      data1091
      c__875__auto__
      (clojure.core/int *buffer-size*)
      mac__876__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.SHA224Digest))
      k__877__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key1092)
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
   (compute-sha2241090
    [data1091 key1092]
    (clojure.core/with-open
     [data1091 (clojure.java.io/input-stream data1091)]
     (clojure.core/let
      [s__874__auto__
       data1091
       c__875__auto__
       (clojure.core/int *buffer-size*)
       mac__876__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.SHA224Digest))
       k__877__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key1092)
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
  'G__1093)
 (do
  (clojure.core/defn
   sha224-hmac*
   "[HMAC] SHA-224 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha2241090
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha224-hmac-file*
   "[HMAC] SHA-224 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2241090
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha224-hmac-bytes
   "[HMAC] SHA-224 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha2241090
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha224-hmac-file-bytes
   "[HMAC] SHA-224 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2241090
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha224-hmac
   "[HMAC] SHA-224 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha2241090
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha224-hmac-file
   "[HMAC] SHA-224 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha2241090
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
