(ns
 pandect.algo.md4
 "MD4 algorithm implementation\n\n(requires `org.bouncycastle/bcprov-jdk15on` to be on the classpath)"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  pandect.utils.bouncy-castle-provider))
(do
 (do
  (clojure.core/defprotocol G__1225 (compute-md41223 [data1224]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1225 #'compute-md41223]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1225
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md41223
    [data1224]
    (clojure.core/let
     [md__668__auto__ (java.security.MessageDigest/getInstance "MD4")]
     (.digest md__668__auto__ data1224)))
   java.lang.String
   (compute-md41223
    [data1224]
    (clojure.core/let
     [data1224 (.getBytes data1224 "UTF-8")]
     (clojure.core/let
      [md__668__auto__ (java.security.MessageDigest/getInstance "MD4")]
      (.digest md__668__auto__ data1224)))))
  (clojure.core/extend-protocol
   G__1225
   java.io.InputStream
   (compute-md41223
    [data1224]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "MD4")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1224]
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
   (compute-md41223
    [data1224]
    (clojure.core/with-open
     [data1224 (clojure.java.io/input-stream data1224)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "MD4")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1224]
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
  'G__1225)
 (do
  (clojure.core/defn
   md4*
   "[Hash] MD4 (raw value)"
   [x]
   (compute-md41223 x))
  (clojure.core/defn
   md4-file*
   "[Hash] MD4 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md41223 x)))
  (clojure.core/defn
   md4-bytes
   "[Hash] MD4 (value -> byte array)"
   [x]
   (compute-md41223 x))
  (clojure.core/defn
   md4-file-bytes
   "[Hash] MD4 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md41223 x)))
  (clojure.core/defn
   md4
   "[Hash] MD4 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-md41223 x)))
  (clojure.core/defn
   md4-file
   "[Hash] MD4 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-md41223 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1247
   (compute-md41244 [data1245 key1246]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1247 #'compute-md41244]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1247
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md41244
    [data1245 key1246]
    (clojure.core/let
     [mac__543__auto__
      (javax.crypto.Mac/getInstance "Hmac-MD4")
      msg__544__auto__
      (clojure.core/bytes data1245)
      k__545__auto__
      (javax.crypto.spec.SecretKeySpec. key1246 "Hmac-MD4")]
     (clojure.core/->
      (clojure.core/doto
       mac__543__auto__
       (.init k__545__auto__)
       (.update msg__544__auto__))
      (.doFinal))))
   java.lang.String
   (compute-md41244
    [data1245 key1246]
    (clojure.core/let
     [data1245 (.getBytes data1245 "UTF-8")]
     (clojure.core/let
      [mac__543__auto__
       (javax.crypto.Mac/getInstance "Hmac-MD4")
       msg__544__auto__
       (clojure.core/bytes data1245)
       k__545__auto__
       (javax.crypto.spec.SecretKeySpec. key1246 "Hmac-MD4")]
      (clojure.core/->
       (clojure.core/doto
        mac__543__auto__
        (.init k__545__auto__)
        (.update msg__544__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1247
   java.io.InputStream
   (compute-md41244
    [data1245 key1246]
    (clojure.core/let
     [mac__546__auto__
      (javax.crypto.Mac/getInstance "Hmac-MD4")
      k__547__auto__
      (javax.crypto.spec.SecretKeySpec. key1246 "Hmac-MD4")
      c__548__auto__
      (clojure.core/int *buffer-size*)
      buf__549__auto__
      (clojure.core/byte-array c__548__auto__)
      s__550__auto__
      data1245]
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
   (compute-md41244
    [data1245 key1246]
    (clojure.core/with-open
     [data1245 (clojure.java.io/input-stream data1245)]
     (clojure.core/let
      [mac__546__auto__
       (javax.crypto.Mac/getInstance "Hmac-MD4")
       k__547__auto__
       (javax.crypto.spec.SecretKeySpec. key1246 "Hmac-MD4")
       c__548__auto__
       (clojure.core/int *buffer-size*)
       buf__549__auto__
       (clojure.core/byte-array c__548__auto__)
       s__550__auto__
       data1245]
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
  'G__1247)
 (do
  (clojure.core/defn
   md4-hmac*
   "[HMAC] Hmac-MD4 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-md41244
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   md4-hmac-file*
   "[HMAC] Hmac-MD4 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md41244
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   md4-hmac-bytes
   "[HMAC] Hmac-MD4 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-md41244
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   md4-hmac-file-bytes
   "[HMAC] Hmac-MD4 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md41244
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   md4-hmac
   "[HMAC] Hmac-MD4 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-md41244
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   md4-hmac-file
   "[HMAC] Hmac-MD4 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-md41244
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
