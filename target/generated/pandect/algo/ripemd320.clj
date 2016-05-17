(ns
 pandect.algo.ripemd320
 "RipeMD320 algorithm implementation\n\n(requires `org.bouncycastle/bcprov-jdk15on` to be on the classpath)"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  pandect.utils.bouncy-castle-provider))
(do
 (do
  (clojure.core/defprotocol G__1265 (compute-ripemd3201263 [data1264]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1265 #'compute-ripemd3201263]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1265
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-ripemd3201263
    [data1264]
    (clojure.core/let
     [md__668__auto__
      (java.security.MessageDigest/getInstance "RipeMD320")]
     (.digest md__668__auto__ data1264)))
   java.lang.String
   (compute-ripemd3201263
    [data1264]
    (clojure.core/let
     [data1264 (.getBytes data1264 "UTF-8")]
     (clojure.core/let
      [md__668__auto__
       (java.security.MessageDigest/getInstance "RipeMD320")]
      (.digest md__668__auto__ data1264)))))
  (clojure.core/extend-protocol
   G__1265
   java.io.InputStream
   (compute-ripemd3201263
    [data1264]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "RipeMD320")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1264]
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
   (compute-ripemd3201263
    [data1264]
    (clojure.core/with-open
     [data1264 (clojure.java.io/input-stream data1264)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "RipeMD320")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1264]
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
  'G__1265)
 (do
  (clojure.core/defn
   ripemd320*
   "[Hash] RipeMD320 (raw value)"
   [x]
   (compute-ripemd3201263 x))
  (clojure.core/defn
   ripemd320-file*
   "[Hash] RipeMD320 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd3201263 x)))
  (clojure.core/defn
   ripemd320-bytes
   "[Hash] RipeMD320 (value -> byte array)"
   [x]
   (compute-ripemd3201263 x))
  (clojure.core/defn
   ripemd320-file-bytes
   "[Hash] RipeMD320 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd3201263 x)))
  (clojure.core/defn
   ripemd320
   "[Hash] RipeMD320 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-ripemd3201263 x)))
  (clojure.core/defn
   ripemd320-file
   "[Hash] RipeMD320 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-ripemd3201263 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1299
   (compute-ripemd3201296 [data1297 key1298]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1299 #'compute-ripemd3201296]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1299
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-ripemd3201296
    [data1297 key1298]
    (clojure.core/let
     [mac__543__auto__
      (javax.crypto.Mac/getInstance "Hmac-RipeMD320")
      msg__544__auto__
      (clojure.core/bytes data1297)
      k__545__auto__
      (javax.crypto.spec.SecretKeySpec. key1298 "Hmac-RipeMD320")]
     (clojure.core/->
      (clojure.core/doto
       mac__543__auto__
       (.init k__545__auto__)
       (.update msg__544__auto__))
      (.doFinal))))
   java.lang.String
   (compute-ripemd3201296
    [data1297 key1298]
    (clojure.core/let
     [data1297 (.getBytes data1297 "UTF-8")]
     (clojure.core/let
      [mac__543__auto__
       (javax.crypto.Mac/getInstance "Hmac-RipeMD320")
       msg__544__auto__
       (clojure.core/bytes data1297)
       k__545__auto__
       (javax.crypto.spec.SecretKeySpec. key1298 "Hmac-RipeMD320")]
      (clojure.core/->
       (clojure.core/doto
        mac__543__auto__
        (.init k__545__auto__)
        (.update msg__544__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1299
   java.io.InputStream
   (compute-ripemd3201296
    [data1297 key1298]
    (clojure.core/let
     [mac__546__auto__
      (javax.crypto.Mac/getInstance "Hmac-RipeMD320")
      k__547__auto__
      (javax.crypto.spec.SecretKeySpec. key1298 "Hmac-RipeMD320")
      c__548__auto__
      (clojure.core/int *buffer-size*)
      buf__549__auto__
      (clojure.core/byte-array c__548__auto__)
      s__550__auto__
      data1297]
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
   (compute-ripemd3201296
    [data1297 key1298]
    (clojure.core/with-open
     [data1297 (clojure.java.io/input-stream data1297)]
     (clojure.core/let
      [mac__546__auto__
       (javax.crypto.Mac/getInstance "Hmac-RipeMD320")
       k__547__auto__
       (javax.crypto.spec.SecretKeySpec. key1298 "Hmac-RipeMD320")
       c__548__auto__
       (clojure.core/int *buffer-size*)
       buf__549__auto__
       (clojure.core/byte-array c__548__auto__)
       s__550__auto__
       data1297]
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
  'G__1299)
 (do
  (clojure.core/defn
   ripemd320-hmac*
   "[HMAC] Hmac-RipeMD320 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-ripemd3201296
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   ripemd320-hmac-file*
   "[HMAC] Hmac-RipeMD320 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd3201296
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd320-hmac-bytes
   "[HMAC] Hmac-RipeMD320 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-ripemd3201296
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   ripemd320-hmac-file-bytes
   "[HMAC] Hmac-RipeMD320 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd3201296
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd320-hmac
   "[HMAC] Hmac-RipeMD320 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-ripemd3201296
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd320-hmac-file
   "[HMAC] Hmac-RipeMD320 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-ripemd3201296
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
