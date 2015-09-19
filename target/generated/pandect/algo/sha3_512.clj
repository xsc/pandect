(ns
 pandect.algo.sha3-512
 "SHA-3 (512) algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__995 (compute-sha3-512993 [data994]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__995 #'compute-sha3-512993]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__995
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha3-512993
    [data994]
    (clojure.core/let
     [buf__882__auto__
      data994
      digest__883__auto__
      (new org.bouncycastle.crypto.digests.SHA3Digest 512)
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
   (compute-sha3-512993
    [data994]
    (clojure.core/let
     [data994 (.getBytes data994 "UTF-8")]
     (clojure.core/let
      [buf__882__auto__
       data994
       digest__883__auto__
       (new org.bouncycastle.crypto.digests.SHA3Digest 512)
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
   G__995
   java.io.InputStream
   (compute-sha3-512993
    [data994]
    (clojure.core/let
     [s__886__auto__
      data994
      c__887__auto__
      (clojure.core/int *buffer-size*)
      digest__888__auto__
      (new org.bouncycastle.crypto.digests.SHA3Digest 512)
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
   (compute-sha3-512993
    [data994]
    (clojure.core/with-open
     [data994 (clojure.java.io/input-stream data994)]
     (clojure.core/let
      [s__886__auto__
       data994
       c__887__auto__
       (clojure.core/int *buffer-size*)
       digest__888__auto__
       (new org.bouncycastle.crypto.digests.SHA3Digest 512)
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
  'G__995)
 (do
  (clojure.core/defn
   sha3-512*
   "[Hash] SHA-3 (512) (raw value)"
   [x]
   (compute-sha3-512993 x))
  (clojure.core/defn
   sha3-512-file*
   "[Hash] SHA-3 (512) (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3-512993 x)))
  (clojure.core/defn
   sha3-512-bytes
   "[Hash] SHA-3 (512) (value -> byte array)"
   [x]
   (compute-sha3-512993 x))
  (clojure.core/defn
   sha3-512-file-bytes
   "[Hash] SHA-3 (512) (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3-512993 x)))
  (clojure.core/defn
   sha3-512
   "[Hash] SHA-3 (512) (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha3-512993 x)))
  (clojure.core/defn
   sha3-512-file
   "[Hash] SHA-3 (512) (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-sha3-512993 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__999
   (compute-sha3-512996 [data997 key998]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__999 #'compute-sha3-512996]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__999
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha3-512996
    [data997 key998]
    (clojure.core/let
     [buf__869__auto__
      data997
      k__870__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key998)
      mac__871__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.SHA3Digest 512))
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
   (compute-sha3-512996
    [data997 key998]
    (clojure.core/let
     [data997 (.getBytes data997 "UTF-8")]
     (clojure.core/let
      [buf__869__auto__
       data997
       k__870__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key998)
       mac__871__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.SHA3Digest 512))
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
   G__999
   java.io.InputStream
   (compute-sha3-512996
    [data997 key998]
    (clojure.core/let
     [s__874__auto__
      data997
      c__875__auto__
      (clojure.core/int *buffer-size*)
      mac__876__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.SHA3Digest 512))
      k__877__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key998)
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
   (compute-sha3-512996
    [data997 key998]
    (clojure.core/with-open
     [data997 (clojure.java.io/input-stream data997)]
     (clojure.core/let
      [s__874__auto__
       data997
       c__875__auto__
       (clojure.core/int *buffer-size*)
       mac__876__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.SHA3Digest 512))
       k__877__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key998)
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
  'G__999)
 (do
  (clojure.core/defn
   sha3-512-hmac*
   "[HMAC] SHA-3 (512) (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha3-512996
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha3-512-hmac-file*
   "[HMAC] SHA-3 (512) (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3-512996
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha3-512-hmac-bytes
   "[HMAC] SHA-3 (512) (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha3-512996
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha3-512-hmac-file-bytes
   "[HMAC] SHA-3 (512) (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3-512996
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha3-512-hmac
   "[HMAC] SHA-3 (512) (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha3-512996
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha3-512-hmac-file
   "[HMAC] SHA-3 (512) (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha3-512996
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
