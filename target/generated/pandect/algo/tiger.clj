(ns
 pandect.algo.tiger
 "Tiger (192,3) algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__988 (compute-tiger986 [data987]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__988 #'compute-tiger986]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__988
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-tiger986
    [data987]
    (clojure.core/let
     [buf__882__auto__
      data987
      digest__883__auto__
      (new org.bouncycastle.crypto.digests.TigerDigest)
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
   (compute-tiger986
    [data987]
    (clojure.core/let
     [data987 (.getBytes data987 "UTF-8")]
     (clojure.core/let
      [buf__882__auto__
       data987
       digest__883__auto__
       (new org.bouncycastle.crypto.digests.TigerDigest)
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
   G__988
   java.io.InputStream
   (compute-tiger986
    [data987]
    (clojure.core/let
     [s__886__auto__
      data987
      c__887__auto__
      (clojure.core/int *buffer-size*)
      digest__888__auto__
      (new org.bouncycastle.crypto.digests.TigerDigest)
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
   (compute-tiger986
    [data987]
    (clojure.core/with-open
     [data987 (clojure.java.io/input-stream data987)]
     (clojure.core/let
      [s__886__auto__
       data987
       c__887__auto__
       (clojure.core/int *buffer-size*)
       digest__888__auto__
       (new org.bouncycastle.crypto.digests.TigerDigest)
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
  'G__988)
 (do
  (clojure.core/defn
   tiger*
   "[Hash] Tiger (192,3) (raw value)"
   [x]
   (compute-tiger986 x))
  (clojure.core/defn
   tiger-file*
   "[Hash] Tiger (192,3) (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-tiger986 x)))
  (clojure.core/defn
   tiger-bytes
   "[Hash] Tiger (192,3) (value -> byte array)"
   [x]
   (compute-tiger986 x))
  (clojure.core/defn
   tiger-file-bytes
   "[Hash] Tiger (192,3) (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-tiger986 x)))
  (clojure.core/defn
   tiger
   "[Hash] Tiger (192,3) (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-tiger986 x)))
  (clojure.core/defn
   tiger-file
   "[Hash] Tiger (192,3) (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-tiger986 x))))))
(do
 (do
  (clojure.core/defprotocol G__992 (compute-tiger989 [data990 key991]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__992 #'compute-tiger989]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__992
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-tiger989
    [data990 key991]
    (clojure.core/let
     [buf__869__auto__
      data990
      k__870__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key991)
      mac__871__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.TigerDigest))
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
   (compute-tiger989
    [data990 key991]
    (clojure.core/let
     [data990 (.getBytes data990 "UTF-8")]
     (clojure.core/let
      [buf__869__auto__
       data990
       k__870__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key991)
       mac__871__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.TigerDigest))
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
   G__992
   java.io.InputStream
   (compute-tiger989
    [data990 key991]
    (clojure.core/let
     [s__874__auto__
      data990
      c__875__auto__
      (clojure.core/int *buffer-size*)
      mac__876__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.TigerDigest))
      k__877__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key991)
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
   (compute-tiger989
    [data990 key991]
    (clojure.core/with-open
     [data990 (clojure.java.io/input-stream data990)]
     (clojure.core/let
      [s__874__auto__
       data990
       c__875__auto__
       (clojure.core/int *buffer-size*)
       mac__876__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.TigerDigest))
       k__877__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key991)
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
  'G__992)
 (do
  (clojure.core/defn
   tiger-hmac*
   "[HMAC] Tiger (192,3) (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-tiger989
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   tiger-hmac-file*
   "[HMAC] Tiger (192,3) (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-tiger989
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   tiger-hmac-bytes
   "[HMAC] Tiger (192,3) (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-tiger989
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   tiger-hmac-file-bytes
   "[HMAC] Tiger (192,3) (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-tiger989
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   tiger-hmac
   "[HMAC] Tiger (192,3) (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-tiger989
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   tiger-hmac-file
   "[HMAC] Tiger (192,3) (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-tiger989
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
