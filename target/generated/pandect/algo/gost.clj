(ns
 pandect.algo.gost
 "GOST3411 algorithm implementation\n\n(requires `org.bouncycastle/bcprov-jdk15on` to be on the classpath)"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  pandect.utils.bouncy-castle-provider))
(do
 (do
  (clojure.core/defprotocol G__1180 (compute-gost1177 [data1179]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1180 #'compute-gost1177]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1180
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-gost1177
    [data1179]
    (clojure.core/let
     [md__668__auto__
      (java.security.MessageDigest/getInstance "GOST3411")]
     (.digest md__668__auto__ data1179)))
   java.lang.String
   (compute-gost1177
    [data1179]
    (clojure.core/let
     [data1179 (.getBytes data1179 "UTF-8")]
     (clojure.core/let
      [md__668__auto__
       (java.security.MessageDigest/getInstance "GOST3411")]
      (.digest md__668__auto__ data1179)))))
  (clojure.core/extend-protocol
   G__1180
   java.io.InputStream
   (compute-gost1177
    [data1179]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "GOST3411")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1179]
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
   (compute-gost1177
    [data1179]
    (clojure.core/with-open
     [data1179 (clojure.java.io/input-stream data1179)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "GOST3411")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1179]
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
  'G__1180)
 (do
  (clojure.core/defn
   gost*
   "[Hash] GOST3411 (raw value)"
   [x]
   (compute-gost1177 x))
  (clojure.core/defn
   gost-file*
   "[Hash] GOST3411 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-gost1177 x)))
  (clojure.core/defn
   gost-bytes
   "[Hash] GOST3411 (value -> byte array)"
   [x]
   (compute-gost1177 x))
  (clojure.core/defn
   gost-file-bytes
   "[Hash] GOST3411 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-gost1177 x)))
  (clojure.core/defn
   gost
   "[Hash] GOST3411 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-gost1177 x)))
  (clojure.core/defn
   gost-file
   "[Hash] GOST3411 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-gost1177 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1204
   (compute-gost1201 [data1202 key1203]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1204 #'compute-gost1201]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1204
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-gost1201
    [data1202 key1203]
    (clojure.core/let
     [mac__543__auto__
      (javax.crypto.Mac/getInstance "Hmac-GOST3411")
      msg__544__auto__
      (clojure.core/bytes data1202)
      k__545__auto__
      (javax.crypto.spec.SecretKeySpec. key1203 "Hmac-GOST3411")]
     (clojure.core/->
      (clojure.core/doto
       mac__543__auto__
       (.init k__545__auto__)
       (.update msg__544__auto__))
      (.doFinal))))
   java.lang.String
   (compute-gost1201
    [data1202 key1203]
    (clojure.core/let
     [data1202 (.getBytes data1202 "UTF-8")]
     (clojure.core/let
      [mac__543__auto__
       (javax.crypto.Mac/getInstance "Hmac-GOST3411")
       msg__544__auto__
       (clojure.core/bytes data1202)
       k__545__auto__
       (javax.crypto.spec.SecretKeySpec. key1203 "Hmac-GOST3411")]
      (clojure.core/->
       (clojure.core/doto
        mac__543__auto__
        (.init k__545__auto__)
        (.update msg__544__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1204
   java.io.InputStream
   (compute-gost1201
    [data1202 key1203]
    (clojure.core/let
     [mac__546__auto__
      (javax.crypto.Mac/getInstance "Hmac-GOST3411")
      k__547__auto__
      (javax.crypto.spec.SecretKeySpec. key1203 "Hmac-GOST3411")
      c__548__auto__
      (clojure.core/int *buffer-size*)
      buf__549__auto__
      (clojure.core/byte-array c__548__auto__)
      s__550__auto__
      data1202]
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
   (compute-gost1201
    [data1202 key1203]
    (clojure.core/with-open
     [data1202 (clojure.java.io/input-stream data1202)]
     (clojure.core/let
      [mac__546__auto__
       (javax.crypto.Mac/getInstance "Hmac-GOST3411")
       k__547__auto__
       (javax.crypto.spec.SecretKeySpec. key1203 "Hmac-GOST3411")
       c__548__auto__
       (clojure.core/int *buffer-size*)
       buf__549__auto__
       (clojure.core/byte-array c__548__auto__)
       s__550__auto__
       data1202]
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
  'G__1204)
 (do
  (clojure.core/defn
   gost-hmac*
   "[HMAC] Hmac-GOST3411 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-gost1201
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   gost-hmac-file*
   "[HMAC] Hmac-GOST3411 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-gost1201
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   gost-hmac-bytes
   "[HMAC] Hmac-GOST3411 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-gost1201
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   gost-hmac-file-bytes
   "[HMAC] Hmac-GOST3411 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-gost1201
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   gost-hmac
   "[HMAC] Hmac-GOST3411 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-gost1201
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   gost-hmac-file
   "[HMAC] Hmac-GOST3411 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-gost1201
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
