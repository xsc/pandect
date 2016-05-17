(ns
 pandect.algo.whirlpool
 "Whirlpool algorithm implementation\n\n(requires `org.bouncycastle/bcprov-jdk15on` to be on the classpath)"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  pandect.utils.bouncy-castle-provider))
(do
 (do
  (clojure.core/defprotocol G__1356 (compute-whirlpool1353 [data1354]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1356 #'compute-whirlpool1353]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1356
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-whirlpool1353
    [data1354]
    (clojure.core/let
     [md__668__auto__
      (java.security.MessageDigest/getInstance "Whirlpool")]
     (.digest md__668__auto__ data1354)))
   java.lang.String
   (compute-whirlpool1353
    [data1354]
    (clojure.core/let
     [data1354 (.getBytes data1354 "UTF-8")]
     (clojure.core/let
      [md__668__auto__
       (java.security.MessageDigest/getInstance "Whirlpool")]
      (.digest md__668__auto__ data1354)))))
  (clojure.core/extend-protocol
   G__1356
   java.io.InputStream
   (compute-whirlpool1353
    [data1354]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "Whirlpool")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1354]
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
   (compute-whirlpool1353
    [data1354]
    (clojure.core/with-open
     [data1354 (clojure.java.io/input-stream data1354)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "Whirlpool")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1354]
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
  'G__1356)
 (do
  (clojure.core/defn
   whirlpool*
   "[Hash] Whirlpool (raw value)"
   [x]
   (compute-whirlpool1353 x))
  (clojure.core/defn
   whirlpool-file*
   "[Hash] Whirlpool (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-whirlpool1353 x)))
  (clojure.core/defn
   whirlpool-bytes
   "[Hash] Whirlpool (value -> byte array)"
   [x]
   (compute-whirlpool1353 x))
  (clojure.core/defn
   whirlpool-file-bytes
   "[Hash] Whirlpool (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-whirlpool1353 x)))
  (clojure.core/defn
   whirlpool
   "[Hash] Whirlpool (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-whirlpool1353 x)))
  (clojure.core/defn
   whirlpool-file
   "[Hash] Whirlpool (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-whirlpool1353 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1365
   (compute-whirlpool1362 [data1363 key1364]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1365 #'compute-whirlpool1362]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1365
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-whirlpool1362
    [data1363 key1364]
    (clojure.core/let
     [mac__543__auto__
      (javax.crypto.Mac/getInstance "Hmac-Whirlpool")
      msg__544__auto__
      (clojure.core/bytes data1363)
      k__545__auto__
      (javax.crypto.spec.SecretKeySpec. key1364 "Hmac-Whirlpool")]
     (clojure.core/->
      (clojure.core/doto
       mac__543__auto__
       (.init k__545__auto__)
       (.update msg__544__auto__))
      (.doFinal))))
   java.lang.String
   (compute-whirlpool1362
    [data1363 key1364]
    (clojure.core/let
     [data1363 (.getBytes data1363 "UTF-8")]
     (clojure.core/let
      [mac__543__auto__
       (javax.crypto.Mac/getInstance "Hmac-Whirlpool")
       msg__544__auto__
       (clojure.core/bytes data1363)
       k__545__auto__
       (javax.crypto.spec.SecretKeySpec. key1364 "Hmac-Whirlpool")]
      (clojure.core/->
       (clojure.core/doto
        mac__543__auto__
        (.init k__545__auto__)
        (.update msg__544__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1365
   java.io.InputStream
   (compute-whirlpool1362
    [data1363 key1364]
    (clojure.core/let
     [mac__546__auto__
      (javax.crypto.Mac/getInstance "Hmac-Whirlpool")
      k__547__auto__
      (javax.crypto.spec.SecretKeySpec. key1364 "Hmac-Whirlpool")
      c__548__auto__
      (clojure.core/int *buffer-size*)
      buf__549__auto__
      (clojure.core/byte-array c__548__auto__)
      s__550__auto__
      data1363]
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
   (compute-whirlpool1362
    [data1363 key1364]
    (clojure.core/with-open
     [data1363 (clojure.java.io/input-stream data1363)]
     (clojure.core/let
      [mac__546__auto__
       (javax.crypto.Mac/getInstance "Hmac-Whirlpool")
       k__547__auto__
       (javax.crypto.spec.SecretKeySpec. key1364 "Hmac-Whirlpool")
       c__548__auto__
       (clojure.core/int *buffer-size*)
       buf__549__auto__
       (clojure.core/byte-array c__548__auto__)
       s__550__auto__
       data1363]
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
  'G__1365)
 (do
  (clojure.core/defn
   whirlpool-hmac*
   "[HMAC] Hmac-Whirlpool (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-whirlpool1362
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   whirlpool-hmac-file*
   "[HMAC] Hmac-Whirlpool (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-whirlpool1362
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   whirlpool-hmac-bytes
   "[HMAC] Hmac-Whirlpool (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-whirlpool1362
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   whirlpool-hmac-file-bytes
   "[HMAC] Hmac-Whirlpool (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-whirlpool1362
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   whirlpool-hmac
   "[HMAC] Hmac-Whirlpool (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-whirlpool1362
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   whirlpool-hmac-file
   "[HMAC] Hmac-Whirlpool (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-whirlpool1362
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
