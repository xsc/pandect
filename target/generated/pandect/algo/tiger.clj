(ns
 pandect.algo.tiger
 "Tiger algorithm implementation\n\n(requires `org.bouncycastle/bcprov-jdk15on` to be on the classpath)"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  pandect.utils.bouncy-castle-provider))
(do
 (do
  (clojure.core/defprotocol G__1357 (compute-tiger1352 [data1355]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1357 #'compute-tiger1352]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1357
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-tiger1352
    [data1355]
    (clojure.core/let
     [md__668__auto__
      (java.security.MessageDigest/getInstance "Tiger")]
     (.digest md__668__auto__ data1355)))
   java.lang.String
   (compute-tiger1352
    [data1355]
    (clojure.core/let
     [data1355 (.getBytes data1355 "UTF-8")]
     (clojure.core/let
      [md__668__auto__
       (java.security.MessageDigest/getInstance "Tiger")]
      (.digest md__668__auto__ data1355)))))
  (clojure.core/extend-protocol
   G__1357
   java.io.InputStream
   (compute-tiger1352
    [data1355]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "Tiger")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1355]
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
   (compute-tiger1352
    [data1355]
    (clojure.core/with-open
     [data1355 (clojure.java.io/input-stream data1355)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "Tiger")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1355]
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
  'G__1357)
 (do
  (clojure.core/defn
   tiger*
   "[Hash] Tiger (raw value)"
   [x]
   (compute-tiger1352 x))
  (clojure.core/defn
   tiger-file*
   "[Hash] Tiger (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-tiger1352 x)))
  (clojure.core/defn
   tiger-bytes
   "[Hash] Tiger (value -> byte array)"
   [x]
   (compute-tiger1352 x))
  (clojure.core/defn
   tiger-file-bytes
   "[Hash] Tiger (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-tiger1352 x)))
  (clojure.core/defn
   tiger
   "[Hash] Tiger (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-tiger1352 x)))
  (clojure.core/defn
   tiger-file
   "[Hash] Tiger (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-tiger1352 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1361
   (compute-tiger1358 [data1359 key1360]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1361 #'compute-tiger1358]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1361
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-tiger1358
    [data1359 key1360]
    (clojure.core/let
     [mac__543__auto__
      (javax.crypto.Mac/getInstance "Hmac-Tiger")
      msg__544__auto__
      (clojure.core/bytes data1359)
      k__545__auto__
      (javax.crypto.spec.SecretKeySpec. key1360 "Hmac-Tiger")]
     (clojure.core/->
      (clojure.core/doto
       mac__543__auto__
       (.init k__545__auto__)
       (.update msg__544__auto__))
      (.doFinal))))
   java.lang.String
   (compute-tiger1358
    [data1359 key1360]
    (clojure.core/let
     [data1359 (.getBytes data1359 "UTF-8")]
     (clojure.core/let
      [mac__543__auto__
       (javax.crypto.Mac/getInstance "Hmac-Tiger")
       msg__544__auto__
       (clojure.core/bytes data1359)
       k__545__auto__
       (javax.crypto.spec.SecretKeySpec. key1360 "Hmac-Tiger")]
      (clojure.core/->
       (clojure.core/doto
        mac__543__auto__
        (.init k__545__auto__)
        (.update msg__544__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1361
   java.io.InputStream
   (compute-tiger1358
    [data1359 key1360]
    (clojure.core/let
     [mac__546__auto__
      (javax.crypto.Mac/getInstance "Hmac-Tiger")
      k__547__auto__
      (javax.crypto.spec.SecretKeySpec. key1360 "Hmac-Tiger")
      c__548__auto__
      (clojure.core/int *buffer-size*)
      buf__549__auto__
      (clojure.core/byte-array c__548__auto__)
      s__550__auto__
      data1359]
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
   (compute-tiger1358
    [data1359 key1360]
    (clojure.core/with-open
     [data1359 (clojure.java.io/input-stream data1359)]
     (clojure.core/let
      [mac__546__auto__
       (javax.crypto.Mac/getInstance "Hmac-Tiger")
       k__547__auto__
       (javax.crypto.spec.SecretKeySpec. key1360 "Hmac-Tiger")
       c__548__auto__
       (clojure.core/int *buffer-size*)
       buf__549__auto__
       (clojure.core/byte-array c__548__auto__)
       s__550__auto__
       data1359]
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
  'G__1361)
 (do
  (clojure.core/defn
   tiger-hmac*
   "[HMAC] Hmac-Tiger (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-tiger1358
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   tiger-hmac-file*
   "[HMAC] Hmac-Tiger (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-tiger1358
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   tiger-hmac-bytes
   "[HMAC] Hmac-Tiger (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-tiger1358
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   tiger-hmac-file-bytes
   "[HMAC] Hmac-Tiger (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-tiger1358
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   tiger-hmac
   "[HMAC] Hmac-Tiger (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-tiger1358
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   tiger-hmac-file
   "[HMAC] Hmac-Tiger (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-tiger1358
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
