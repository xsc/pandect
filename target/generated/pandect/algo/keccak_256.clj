(ns
 pandect.algo.keccak-256
 "Keccak-256 algorithm implementation\n\n(requires `org.bouncycastle/bcprov-jdk15on` to be on the classpath)"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  pandect.utils.bouncy-castle-provider))
(do
 (do
  (clojure.core/defprotocol
   G__1190
   (compute-keccak-2561184 [data1187]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1190 #'compute-keccak-2561184]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1190
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-keccak-2561184
    [data1187]
    (clojure.core/let
     [md__668__auto__
      (java.security.MessageDigest/getInstance "Keccak-256")]
     (.digest md__668__auto__ data1187)))
   java.lang.String
   (compute-keccak-2561184
    [data1187]
    (clojure.core/let
     [data1187 (.getBytes data1187 "UTF-8")]
     (clojure.core/let
      [md__668__auto__
       (java.security.MessageDigest/getInstance "Keccak-256")]
      (.digest md__668__auto__ data1187)))))
  (clojure.core/extend-protocol
   G__1190
   java.io.InputStream
   (compute-keccak-2561184
    [data1187]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "Keccak-256")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1187]
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
   (compute-keccak-2561184
    [data1187]
    (clojure.core/with-open
     [data1187 (clojure.java.io/input-stream data1187)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "Keccak-256")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1187]
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
  'G__1190)
 (do
  (clojure.core/defn
   keccak-256*
   "[Hash] Keccak-256 (raw value)"
   [x]
   (compute-keccak-2561184 x))
  (clojure.core/defn
   keccak-256-file*
   "[Hash] Keccak-256 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-keccak-2561184 x)))
  (clojure.core/defn
   keccak-256-bytes
   "[Hash] Keccak-256 (value -> byte array)"
   [x]
   (compute-keccak-2561184 x))
  (clojure.core/defn
   keccak-256-file-bytes
   "[Hash] Keccak-256 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-keccak-2561184 x)))
  (clojure.core/defn
   keccak-256
   "[Hash] Keccak-256 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-keccak-2561184 x)))
  (clojure.core/defn
   keccak-256-file
   "[Hash] Keccak-256 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-keccak-2561184 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1219
   (compute-keccak-2561216 [data1217 key1218]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1219 #'compute-keccak-2561216]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1219
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-keccak-2561216
    [data1217 key1218]
    (clojure.core/let
     [mac__543__auto__
      (javax.crypto.Mac/getInstance "Hmac-Keccak256")
      msg__544__auto__
      (clojure.core/bytes data1217)
      k__545__auto__
      (javax.crypto.spec.SecretKeySpec. key1218 "Hmac-Keccak256")]
     (clojure.core/->
      (clojure.core/doto
       mac__543__auto__
       (.init k__545__auto__)
       (.update msg__544__auto__))
      (.doFinal))))
   java.lang.String
   (compute-keccak-2561216
    [data1217 key1218]
    (clojure.core/let
     [data1217 (.getBytes data1217 "UTF-8")]
     (clojure.core/let
      [mac__543__auto__
       (javax.crypto.Mac/getInstance "Hmac-Keccak256")
       msg__544__auto__
       (clojure.core/bytes data1217)
       k__545__auto__
       (javax.crypto.spec.SecretKeySpec. key1218 "Hmac-Keccak256")]
      (clojure.core/->
       (clojure.core/doto
        mac__543__auto__
        (.init k__545__auto__)
        (.update msg__544__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1219
   java.io.InputStream
   (compute-keccak-2561216
    [data1217 key1218]
    (clojure.core/let
     [mac__546__auto__
      (javax.crypto.Mac/getInstance "Hmac-Keccak256")
      k__547__auto__
      (javax.crypto.spec.SecretKeySpec. key1218 "Hmac-Keccak256")
      c__548__auto__
      (clojure.core/int *buffer-size*)
      buf__549__auto__
      (clojure.core/byte-array c__548__auto__)
      s__550__auto__
      data1217]
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
   (compute-keccak-2561216
    [data1217 key1218]
    (clojure.core/with-open
     [data1217 (clojure.java.io/input-stream data1217)]
     (clojure.core/let
      [mac__546__auto__
       (javax.crypto.Mac/getInstance "Hmac-Keccak256")
       k__547__auto__
       (javax.crypto.spec.SecretKeySpec. key1218 "Hmac-Keccak256")
       c__548__auto__
       (clojure.core/int *buffer-size*)
       buf__549__auto__
       (clojure.core/byte-array c__548__auto__)
       s__550__auto__
       data1217]
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
  'G__1219)
 (do
  (clojure.core/defn
   keccak-256-hmac*
   "[HMAC] Hmac-Keccak256 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-keccak-2561216
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   keccak-256-hmac-file*
   "[HMAC] Hmac-Keccak256 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-keccak-2561216
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   keccak-256-hmac-bytes
   "[HMAC] Hmac-Keccak256 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-keccak-2561216
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   keccak-256-hmac-file-bytes
   "[HMAC] Hmac-Keccak256 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-keccak-2561216
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   keccak-256-hmac
   "[HMAC] Hmac-Keccak256 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-keccak-2561216
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   keccak-256-hmac-file
   "[HMAC] Hmac-Keccak256 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-keccak-2561216
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
