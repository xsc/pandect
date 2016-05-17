(ns
 pandect.algo.keccak-512
 "Keccak-512 algorithm implementation\n\n(requires `org.bouncycastle/bcprov-jdk15on` to be on the classpath)"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  pandect.utils.bouncy-castle-provider))
(do
 (do
  (clojure.core/defprotocol
   G__1182
   (compute-keccak-5121164 [data1181]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1182 #'compute-keccak-5121164]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1182
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-keccak-5121164
    [data1181]
    (clojure.core/let
     [md__668__auto__
      (java.security.MessageDigest/getInstance "Keccak-512")]
     (.digest md__668__auto__ data1181)))
   java.lang.String
   (compute-keccak-5121164
    [data1181]
    (clojure.core/let
     [data1181 (.getBytes data1181 "UTF-8")]
     (clojure.core/let
      [md__668__auto__
       (java.security.MessageDigest/getInstance "Keccak-512")]
      (.digest md__668__auto__ data1181)))))
  (clojure.core/extend-protocol
   G__1182
   java.io.InputStream
   (compute-keccak-5121164
    [data1181]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "Keccak-512")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1181]
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
   (compute-keccak-5121164
    [data1181]
    (clojure.core/with-open
     [data1181 (clojure.java.io/input-stream data1181)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "Keccak-512")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1181]
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
  'G__1182)
 (do
  (clojure.core/defn
   keccak-512*
   "[Hash] Keccak-512 (raw value)"
   [x]
   (compute-keccak-5121164 x))
  (clojure.core/defn
   keccak-512-file*
   "[Hash] Keccak-512 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-keccak-5121164 x)))
  (clojure.core/defn
   keccak-512-bytes
   "[Hash] Keccak-512 (value -> byte array)"
   [x]
   (compute-keccak-5121164 x))
  (clojure.core/defn
   keccak-512-file-bytes
   "[Hash] Keccak-512 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-keccak-5121164 x)))
  (clojure.core/defn
   keccak-512
   "[Hash] Keccak-512 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-keccak-5121164 x)))
  (clojure.core/defn
   keccak-512-file
   "[Hash] Keccak-512 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-keccak-5121164 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1211
   (compute-keccak-5121208 [data1209 key1210]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1211 #'compute-keccak-5121208]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1211
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-keccak-5121208
    [data1209 key1210]
    (clojure.core/let
     [mac__543__auto__
      (javax.crypto.Mac/getInstance "Hmac-Keccak512")
      msg__544__auto__
      (clojure.core/bytes data1209)
      k__545__auto__
      (javax.crypto.spec.SecretKeySpec. key1210 "Hmac-Keccak512")]
     (clojure.core/->
      (clojure.core/doto
       mac__543__auto__
       (.init k__545__auto__)
       (.update msg__544__auto__))
      (.doFinal))))
   java.lang.String
   (compute-keccak-5121208
    [data1209 key1210]
    (clojure.core/let
     [data1209 (.getBytes data1209 "UTF-8")]
     (clojure.core/let
      [mac__543__auto__
       (javax.crypto.Mac/getInstance "Hmac-Keccak512")
       msg__544__auto__
       (clojure.core/bytes data1209)
       k__545__auto__
       (javax.crypto.spec.SecretKeySpec. key1210 "Hmac-Keccak512")]
      (clojure.core/->
       (clojure.core/doto
        mac__543__auto__
        (.init k__545__auto__)
        (.update msg__544__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1211
   java.io.InputStream
   (compute-keccak-5121208
    [data1209 key1210]
    (clojure.core/let
     [mac__546__auto__
      (javax.crypto.Mac/getInstance "Hmac-Keccak512")
      k__547__auto__
      (javax.crypto.spec.SecretKeySpec. key1210 "Hmac-Keccak512")
      c__548__auto__
      (clojure.core/int *buffer-size*)
      buf__549__auto__
      (clojure.core/byte-array c__548__auto__)
      s__550__auto__
      data1209]
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
   (compute-keccak-5121208
    [data1209 key1210]
    (clojure.core/with-open
     [data1209 (clojure.java.io/input-stream data1209)]
     (clojure.core/let
      [mac__546__auto__
       (javax.crypto.Mac/getInstance "Hmac-Keccak512")
       k__547__auto__
       (javax.crypto.spec.SecretKeySpec. key1210 "Hmac-Keccak512")
       c__548__auto__
       (clojure.core/int *buffer-size*)
       buf__549__auto__
       (clojure.core/byte-array c__548__auto__)
       s__550__auto__
       data1209]
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
  'G__1211)
 (do
  (clojure.core/defn
   keccak-512-hmac*
   "[HMAC] Hmac-Keccak512 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-keccak-5121208
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   keccak-512-hmac-file*
   "[HMAC] Hmac-Keccak512 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-keccak-5121208
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   keccak-512-hmac-bytes
   "[HMAC] Hmac-Keccak512 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-keccak-5121208
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   keccak-512-hmac-file-bytes
   "[HMAC] Hmac-Keccak512 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-keccak-5121208
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   keccak-512-hmac
   "[HMAC] Hmac-Keccak512 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-keccak-5121208
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   keccak-512-hmac-file
   "[HMAC] Hmac-Keccak512 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-keccak-5121208
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
