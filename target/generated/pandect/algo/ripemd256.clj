(ns
 pandect.algo.ripemd256
 "RIPEMD-256 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__981 (compute-ripemd256979 [data980]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__981 #'compute-ripemd256979]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__981
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-ripemd256979
    [data980]
    (clojure.core/let
     [buf__882__auto__
      data980
      digest__883__auto__
      (new org.bouncycastle.crypto.digests.RIPEMD256Digest)
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
   (compute-ripemd256979
    [data980]
    (clojure.core/let
     [data980 (.getBytes data980 "UTF-8")]
     (clojure.core/let
      [buf__882__auto__
       data980
       digest__883__auto__
       (new org.bouncycastle.crypto.digests.RIPEMD256Digest)
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
   G__981
   java.io.InputStream
   (compute-ripemd256979
    [data980]
    (clojure.core/let
     [s__886__auto__
      data980
      c__887__auto__
      (clojure.core/int *buffer-size*)
      digest__888__auto__
      (new org.bouncycastle.crypto.digests.RIPEMD256Digest)
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
   (compute-ripemd256979
    [data980]
    (clojure.core/with-open
     [data980 (clojure.java.io/input-stream data980)]
     (clojure.core/let
      [s__886__auto__
       data980
       c__887__auto__
       (clojure.core/int *buffer-size*)
       digest__888__auto__
       (new org.bouncycastle.crypto.digests.RIPEMD256Digest)
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
  'G__981)
 (do
  (clojure.core/defn
   ripemd256*
   "[Hash] RIPEMD-256 (raw value)"
   [x]
   (compute-ripemd256979 x))
  (clojure.core/defn
   ripemd256-file*
   "[Hash] RIPEMD-256 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd256979 x)))
  (clojure.core/defn
   ripemd256-bytes
   "[Hash] RIPEMD-256 (value -> byte array)"
   [x]
   (compute-ripemd256979 x))
  (clojure.core/defn
   ripemd256-file-bytes
   "[Hash] RIPEMD-256 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd256979 x)))
  (clojure.core/defn
   ripemd256
   "[Hash] RIPEMD-256 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-ripemd256979 x)))
  (clojure.core/defn
   ripemd256-file
   "[Hash] RIPEMD-256 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-ripemd256979 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__985
   (compute-ripemd256982 [data983 key984]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__985 #'compute-ripemd256982]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__985
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-ripemd256982
    [data983 key984]
    (clojure.core/let
     [buf__869__auto__
      data983
      k__870__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key984)
      mac__871__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.RIPEMD256Digest))
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
   (compute-ripemd256982
    [data983 key984]
    (clojure.core/let
     [data983 (.getBytes data983 "UTF-8")]
     (clojure.core/let
      [buf__869__auto__
       data983
       k__870__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key984)
       mac__871__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.RIPEMD256Digest))
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
   G__985
   java.io.InputStream
   (compute-ripemd256982
    [data983 key984]
    (clojure.core/let
     [s__874__auto__
      data983
      c__875__auto__
      (clojure.core/int *buffer-size*)
      mac__876__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.RIPEMD256Digest))
      k__877__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key984)
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
   (compute-ripemd256982
    [data983 key984]
    (clojure.core/with-open
     [data983 (clojure.java.io/input-stream data983)]
     (clojure.core/let
      [s__874__auto__
       data983
       c__875__auto__
       (clojure.core/int *buffer-size*)
       mac__876__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.RIPEMD256Digest))
       k__877__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key984)
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
  'G__985)
 (do
  (clojure.core/defn
   ripemd256-hmac*
   "[HMAC] RIPEMD-256 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-ripemd256982
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   ripemd256-hmac-file*
   "[HMAC] RIPEMD-256 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd256982
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd256-hmac-bytes
   "[HMAC] RIPEMD-256 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-ripemd256982
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   ripemd256-hmac-file-bytes
   "[HMAC] RIPEMD-256 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd256982
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd256-hmac
   "[HMAC] RIPEMD-256 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-ripemd256982
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd256-hmac-file
   "[HMAC] RIPEMD-256 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-ripemd256982
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
