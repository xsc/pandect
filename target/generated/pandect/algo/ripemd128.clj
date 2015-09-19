(ns
 pandect.algo.ripemd128
 "RIPEMD-128 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1014 (compute-ripemd1281012 [data1013]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1014 #'compute-ripemd1281012]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1014
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-ripemd1281012
    [data1013]
    (clojure.core/let
     [buf__882__auto__
      data1013
      digest__883__auto__
      (new org.bouncycastle.crypto.digests.RIPEMD128Digest)
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
   (compute-ripemd1281012
    [data1013]
    (clojure.core/let
     [data1013 (.getBytes data1013 "UTF-8")]
     (clojure.core/let
      [buf__882__auto__
       data1013
       digest__883__auto__
       (new org.bouncycastle.crypto.digests.RIPEMD128Digest)
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
   G__1014
   java.io.InputStream
   (compute-ripemd1281012
    [data1013]
    (clojure.core/let
     [s__886__auto__
      data1013
      c__887__auto__
      (clojure.core/int *buffer-size*)
      digest__888__auto__
      (new org.bouncycastle.crypto.digests.RIPEMD128Digest)
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
   (compute-ripemd1281012
    [data1013]
    (clojure.core/with-open
     [data1013 (clojure.java.io/input-stream data1013)]
     (clojure.core/let
      [s__886__auto__
       data1013
       c__887__auto__
       (clojure.core/int *buffer-size*)
       digest__888__auto__
       (new org.bouncycastle.crypto.digests.RIPEMD128Digest)
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
  'G__1014)
 (do
  (clojure.core/defn
   ripemd128*
   "[Hash] RIPEMD-128 (raw value)"
   [x]
   (compute-ripemd1281012 x))
  (clojure.core/defn
   ripemd128-file*
   "[Hash] RIPEMD-128 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd1281012 x)))
  (clojure.core/defn
   ripemd128-bytes
   "[Hash] RIPEMD-128 (value -> byte array)"
   [x]
   (compute-ripemd1281012 x))
  (clojure.core/defn
   ripemd128-file-bytes
   "[Hash] RIPEMD-128 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd1281012 x)))
  (clojure.core/defn
   ripemd128
   "[Hash] RIPEMD-128 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-ripemd1281012 x)))
  (clojure.core/defn
   ripemd128-file
   "[Hash] RIPEMD-128 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-ripemd1281012 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1018
   (compute-ripemd1281015 [data1016 key1017]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1018 #'compute-ripemd1281015]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1018
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-ripemd1281015
    [data1016 key1017]
    (clojure.core/let
     [buf__869__auto__
      data1016
      k__870__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key1017)
      mac__871__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.RIPEMD128Digest))
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
   (compute-ripemd1281015
    [data1016 key1017]
    (clojure.core/let
     [data1016 (.getBytes data1016 "UTF-8")]
     (clojure.core/let
      [buf__869__auto__
       data1016
       k__870__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key1017)
       mac__871__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.RIPEMD128Digest))
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
   G__1018
   java.io.InputStream
   (compute-ripemd1281015
    [data1016 key1017]
    (clojure.core/let
     [s__874__auto__
      data1016
      c__875__auto__
      (clojure.core/int *buffer-size*)
      mac__876__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.RIPEMD128Digest))
      k__877__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key1017)
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
   (compute-ripemd1281015
    [data1016 key1017]
    (clojure.core/with-open
     [data1016 (clojure.java.io/input-stream data1016)]
     (clojure.core/let
      [s__874__auto__
       data1016
       c__875__auto__
       (clojure.core/int *buffer-size*)
       mac__876__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.RIPEMD128Digest))
       k__877__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key1017)
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
  'G__1018)
 (do
  (clojure.core/defn
   ripemd128-hmac*
   "[HMAC] RIPEMD-128 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-ripemd1281015
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   ripemd128-hmac-file*
   "[HMAC] RIPEMD-128 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd1281015
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd128-hmac-bytes
   "[HMAC] RIPEMD-128 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-ripemd1281015
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   ripemd128-hmac-file-bytes
   "[HMAC] RIPEMD-128 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd1281015
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd128-hmac
   "[HMAC] RIPEMD-128 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-ripemd1281015
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd128-hmac-file
   "[HMAC] RIPEMD-128 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-ripemd1281015
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
