(ns
 pandect.algo.keccak-384
 "Keccak-384 algorithm implementation\n\n(requires `org.bouncycastle/bcprov-jdk15on` to be on the classpath)"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  pandect.utils.bouncy-castle-provider))
(do
 (do
  (clojure.core/defprotocol
   G__1196
   (compute-keccak-3841194 [data1195]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1196 #'compute-keccak-3841194]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1196
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-keccak-3841194
    [data1195]
    (clojure.core/let
     [md__668__auto__
      (java.security.MessageDigest/getInstance "Keccak-384")]
     (.digest md__668__auto__ data1195)))
   java.lang.String
   (compute-keccak-3841194
    [data1195]
    (clojure.core/let
     [data1195 (.getBytes data1195 "UTF-8")]
     (clojure.core/let
      [md__668__auto__
       (java.security.MessageDigest/getInstance "Keccak-384")]
      (.digest md__668__auto__ data1195)))))
  (clojure.core/extend-protocol
   G__1196
   java.io.InputStream
   (compute-keccak-3841194
    [data1195]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "Keccak-384")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1195]
     (clojure.core/loop
      []
      (clojure.core/let
       [r__673__auto__
        (.read s__672__auto__ buf__671__auto__ 0 c__670__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__673__auto__ -1)
        (.update md__669__auto__ buf__671__auto__ 0 r__673__auto__)
        (recur))))
     (.digest md__669__auto__)))
   java.io.File
   (compute-keccak-3841194
    [data1195]
    (clojure.core/with-open
     [data1195 (clojure.java.io/input-stream data1195)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "Keccak-384")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1195]
      (clojure.core/loop
       []
       (clojure.core/let
        [r__673__auto__
         (.read s__672__auto__ buf__671__auto__ 0 c__670__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__673__auto__ -1)
         (.update md__669__auto__ buf__671__auto__ 0 r__673__auto__)
         (recur))))
      (.digest md__669__auto__)))))
  'G__1196)
 (do
  (clojure.core/defn
   keccak-384*
   "[Hash] Keccak-384 (raw value)"
   [x]
   (compute-keccak-3841194 x))
  (clojure.core/defn
   keccak-384-file*
   "[Hash] Keccak-384 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-keccak-3841194 x)))
  (clojure.core/defn
   keccak-384-bytes
   "[Hash] Keccak-384 (value -> byte array)"
   [x]
   (compute-keccak-3841194 x))
  (clojure.core/defn
   keccak-384-file-bytes
   "[Hash] Keccak-384 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-keccak-3841194 x)))
  (clojure.core/defn
   keccak-384
   "[Hash] Keccak-384 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-keccak-3841194 x)))
  (clojure.core/defn
   keccak-384-file
   "[Hash] Keccak-384 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-keccak-3841194 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1215
   (compute-keccak-3841212 [data1213 key1214]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1215 #'compute-keccak-3841212]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1215
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-keccak-3841212
    [data1213 key1214]
    (clojure.core/let
     [mac__543__auto__
      (javax.crypto.Mac/getInstance "Hmac-Keccak384")
      msg__544__auto__
      (clojure.core/bytes data1213)
      k__545__auto__
      (javax.crypto.spec.SecretKeySpec. key1214 "Hmac-Keccak384")]
     (clojure.core/->
      (clojure.core/doto
       mac__543__auto__
       (.init k__545__auto__)
       (.update msg__544__auto__))
      (.doFinal))))
   java.lang.String
   (compute-keccak-3841212
    [data1213 key1214]
    (clojure.core/let
     [data1213 (.getBytes data1213 "UTF-8")]
     (clojure.core/let
      [mac__543__auto__
       (javax.crypto.Mac/getInstance "Hmac-Keccak384")
       msg__544__auto__
       (clojure.core/bytes data1213)
       k__545__auto__
       (javax.crypto.spec.SecretKeySpec. key1214 "Hmac-Keccak384")]
      (clojure.core/->
       (clojure.core/doto
        mac__543__auto__
        (.init k__545__auto__)
        (.update msg__544__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1215
   java.io.InputStream
   (compute-keccak-3841212
    [data1213 key1214]
    (clojure.core/let
     [mac__546__auto__
      (javax.crypto.Mac/getInstance "Hmac-Keccak384")
      k__547__auto__
      (javax.crypto.spec.SecretKeySpec. key1214 "Hmac-Keccak384")
      c__548__auto__
      (clojure.core/int *buffer-size*)
      buf__549__auto__
      (clojure.core/byte-array c__548__auto__)
      s__550__auto__
      data1213]
     (.init mac__546__auto__ k__547__auto__)
     (clojure.core/loop
      []
      (clojure.core/let
       [r__551__auto__
        (.read s__550__auto__ buf__549__auto__ 0 c__548__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__551__auto__ -1)
        (.update mac__546__auto__ buf__549__auto__ 0 r__551__auto__)
        (recur))))
     (.doFinal mac__546__auto__)))
   java.io.File
   (compute-keccak-3841212
    [data1213 key1214]
    (clojure.core/with-open
     [data1213 (clojure.java.io/input-stream data1213)]
     (clojure.core/let
      [mac__546__auto__
       (javax.crypto.Mac/getInstance "Hmac-Keccak384")
       k__547__auto__
       (javax.crypto.spec.SecretKeySpec. key1214 "Hmac-Keccak384")
       c__548__auto__
       (clojure.core/int *buffer-size*)
       buf__549__auto__
       (clojure.core/byte-array c__548__auto__)
       s__550__auto__
       data1213]
      (.init mac__546__auto__ k__547__auto__)
      (clojure.core/loop
       []
       (clojure.core/let
        [r__551__auto__
         (.read s__550__auto__ buf__549__auto__ 0 c__548__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__551__auto__ -1)
         (.update mac__546__auto__ buf__549__auto__ 0 r__551__auto__)
         (recur))))
      (.doFinal mac__546__auto__)))))
  'G__1215)
 (do
  (clojure.core/defn
   keccak-384-hmac*
   "[HMAC] Hmac-Keccak384 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-keccak-3841212
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   keccak-384-hmac-file*
   "[HMAC] Hmac-Keccak384 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-keccak-3841212
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   keccak-384-hmac-bytes
   "[HMAC] Hmac-Keccak384 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-keccak-3841212
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   keccak-384-hmac-file-bytes
   "[HMAC] Hmac-Keccak384 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-keccak-3841212
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   keccak-384-hmac
   "[HMAC] Hmac-Keccak384 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-keccak-3841212
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   keccak-384-hmac-file
   "[HMAC] Hmac-Keccak384 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-keccak-3841212
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
