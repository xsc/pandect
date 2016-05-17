(ns
 pandect.algo.sha224
 "SHA-224 algorithm implementation\n\n(requires `org.bouncycastle/bcprov-jdk15on` to be on the classpath)"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  pandect.utils.bouncy-castle-provider))
(do
 (do
  (clojure.core/defprotocol G__1274 (compute-sha2241272 [data1273]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1274 #'compute-sha2241272]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1274
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha2241272
    [data1273]
    (clojure.core/let
     [md__668__auto__
      (java.security.MessageDigest/getInstance "SHA-224")]
     (.digest md__668__auto__ data1273)))
   java.lang.String
   (compute-sha2241272
    [data1273]
    (clojure.core/let
     [data1273 (.getBytes data1273 "UTF-8")]
     (clojure.core/let
      [md__668__auto__
       (java.security.MessageDigest/getInstance "SHA-224")]
      (.digest md__668__auto__ data1273)))))
  (clojure.core/extend-protocol
   G__1274
   java.io.InputStream
   (compute-sha2241272
    [data1273]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "SHA-224")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1273]
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
   (compute-sha2241272
    [data1273]
    (clojure.core/with-open
     [data1273 (clojure.java.io/input-stream data1273)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "SHA-224")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1273]
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
  'G__1274)
 (do
  (clojure.core/defn
   sha224*
   "[Hash] SHA-224 (raw value)"
   [x]
   (compute-sha2241272 x))
  (clojure.core/defn
   sha224-file*
   "[Hash] SHA-224 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2241272 x)))
  (clojure.core/defn
   sha224-bytes
   "[Hash] SHA-224 (value -> byte array)"
   [x]
   (compute-sha2241272 x))
  (clojure.core/defn
   sha224-file-bytes
   "[Hash] SHA-224 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2241272 x)))
  (clojure.core/defn
   sha224
   "[Hash] SHA-224 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha2241272 x)))
  (clojure.core/defn
   sha224-file
   "[Hash] SHA-224 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-sha2241272 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1291
   (compute-sha2241288 [data1289 key1290]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1291 #'compute-sha2241288]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1291
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha2241288
    [data1289 key1290]
    (clojure.core/let
     [mac__543__auto__
      (javax.crypto.Mac/getInstance "Hmac-SHA224")
      msg__544__auto__
      (clojure.core/bytes data1289)
      k__545__auto__
      (javax.crypto.spec.SecretKeySpec. key1290 "Hmac-SHA224")]
     (clojure.core/->
      (clojure.core/doto
       mac__543__auto__
       (.init k__545__auto__)
       (.update msg__544__auto__))
      (.doFinal))))
   java.lang.String
   (compute-sha2241288
    [data1289 key1290]
    (clojure.core/let
     [data1289 (.getBytes data1289 "UTF-8")]
     (clojure.core/let
      [mac__543__auto__
       (javax.crypto.Mac/getInstance "Hmac-SHA224")
       msg__544__auto__
       (clojure.core/bytes data1289)
       k__545__auto__
       (javax.crypto.spec.SecretKeySpec. key1290 "Hmac-SHA224")]
      (clojure.core/->
       (clojure.core/doto
        mac__543__auto__
        (.init k__545__auto__)
        (.update msg__544__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1291
   java.io.InputStream
   (compute-sha2241288
    [data1289 key1290]
    (clojure.core/let
     [mac__546__auto__
      (javax.crypto.Mac/getInstance "Hmac-SHA224")
      k__547__auto__
      (javax.crypto.spec.SecretKeySpec. key1290 "Hmac-SHA224")
      c__548__auto__
      (clojure.core/int *buffer-size*)
      buf__549__auto__
      (clojure.core/byte-array c__548__auto__)
      s__550__auto__
      data1289]
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
   (compute-sha2241288
    [data1289 key1290]
    (clojure.core/with-open
     [data1289 (clojure.java.io/input-stream data1289)]
     (clojure.core/let
      [mac__546__auto__
       (javax.crypto.Mac/getInstance "Hmac-SHA224")
       k__547__auto__
       (javax.crypto.spec.SecretKeySpec. key1290 "Hmac-SHA224")
       c__548__auto__
       (clojure.core/int *buffer-size*)
       buf__549__auto__
       (clojure.core/byte-array c__548__auto__)
       s__550__auto__
       data1289]
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
  'G__1291)
 (do
  (clojure.core/defn
   sha224-hmac*
   "[HMAC] Hmac-SHA224 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha2241288
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha224-hmac-file*
   "[HMAC] Hmac-SHA224 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2241288
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha224-hmac-bytes
   "[HMAC] Hmac-SHA224 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha2241288
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha224-hmac-file-bytes
   "[HMAC] Hmac-SHA224 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2241288
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha224-hmac
   "[HMAC] Hmac-SHA224 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha2241288
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha224-hmac-file
   "[HMAC] Hmac-SHA224 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha2241288
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
