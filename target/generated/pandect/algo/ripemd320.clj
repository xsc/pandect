(ns
 pandect.algo.ripemd320
 "RIPEMD-320 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1021 (compute-ripemd3201019 [data1020]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1021 #'compute-ripemd3201019]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1021
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-ripemd3201019
    [data1020]
    (clojure.core/let
     [buf__882__auto__
      data1020
      digest__883__auto__
      (new org.bouncycastle.crypto.digests.RIPEMD320Digest)
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
   (compute-ripemd3201019
    [data1020]
    (clojure.core/let
     [data1020 (.getBytes data1020 "UTF-8")]
     (clojure.core/let
      [buf__882__auto__
       data1020
       digest__883__auto__
       (new org.bouncycastle.crypto.digests.RIPEMD320Digest)
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
   G__1021
   java.io.InputStream
   (compute-ripemd3201019
    [data1020]
    (clojure.core/let
     [s__886__auto__
      data1020
      c__887__auto__
      (clojure.core/int *buffer-size*)
      digest__888__auto__
      (new org.bouncycastle.crypto.digests.RIPEMD320Digest)
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
   (compute-ripemd3201019
    [data1020]
    (clojure.core/with-open
     [data1020 (clojure.java.io/input-stream data1020)]
     (clojure.core/let
      [s__886__auto__
       data1020
       c__887__auto__
       (clojure.core/int *buffer-size*)
       digest__888__auto__
       (new org.bouncycastle.crypto.digests.RIPEMD320Digest)
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
  'G__1021)
 (do
  (clojure.core/defn
   ripemd320*
   "[Hash] RIPEMD-320 (raw value)"
   [x]
   (compute-ripemd3201019 x))
  (clojure.core/defn
   ripemd320-file*
   "[Hash] RIPEMD-320 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd3201019 x)))
  (clojure.core/defn
   ripemd320-bytes
   "[Hash] RIPEMD-320 (value -> byte array)"
   [x]
   (compute-ripemd3201019 x))
  (clojure.core/defn
   ripemd320-file-bytes
   "[Hash] RIPEMD-320 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd3201019 x)))
  (clojure.core/defn
   ripemd320
   "[Hash] RIPEMD-320 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-ripemd3201019 x)))
  (clojure.core/defn
   ripemd320-file
   "[Hash] RIPEMD-320 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-ripemd3201019 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1025
   (compute-ripemd3201022 [data1023 key1024]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1025 #'compute-ripemd3201022]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1025
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-ripemd3201022
    [data1023 key1024]
    (clojure.core/let
     [buf__869__auto__
      data1023
      k__870__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key1024)
      mac__871__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.RIPEMD320Digest))
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
   (compute-ripemd3201022
    [data1023 key1024]
    (clojure.core/let
     [data1023 (.getBytes data1023 "UTF-8")]
     (clojure.core/let
      [buf__869__auto__
       data1023
       k__870__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key1024)
       mac__871__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.RIPEMD320Digest))
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
   G__1025
   java.io.InputStream
   (compute-ripemd3201022
    [data1023 key1024]
    (clojure.core/let
     [s__874__auto__
      data1023
      c__875__auto__
      (clojure.core/int *buffer-size*)
      mac__876__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.RIPEMD320Digest))
      k__877__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key1024)
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
   (compute-ripemd3201022
    [data1023 key1024]
    (clojure.core/with-open
     [data1023 (clojure.java.io/input-stream data1023)]
     (clojure.core/let
      [s__874__auto__
       data1023
       c__875__auto__
       (clojure.core/int *buffer-size*)
       mac__876__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.RIPEMD320Digest))
       k__877__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key1024)
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
  'G__1025)
 (do
  (clojure.core/defn
   ripemd320-hmac*
   "[HMAC] RIPEMD-320 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-ripemd3201022
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   ripemd320-hmac-file*
   "[HMAC] RIPEMD-320 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd3201022
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd320-hmac-bytes
   "[HMAC] RIPEMD-320 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-ripemd3201022
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   ripemd320-hmac-file-bytes
   "[HMAC] RIPEMD-320 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd3201022
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd320-hmac
   "[HMAC] RIPEMD-320 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-ripemd3201022
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd320-hmac-file
   "[HMAC] RIPEMD-320 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-ripemd3201022
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
