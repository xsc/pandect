(ns
 pandect.algo.keccak-224
 "Keccak-224 algorithm implementation\n\n(requires `org.bouncycastle/bcprov-jdk15on` to be on the classpath)"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  pandect.utils.bouncy-castle-provider))
(do
 (do
  (clojure.core/defprotocol
   G__1175
   (compute-keccak-2241165 [data1170]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1175 #'compute-keccak-2241165]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1175
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-keccak-2241165
    [data1170]
    (clojure.core/let
     [md__668__auto__
      (java.security.MessageDigest/getInstance "Keccak-224")]
     (.digest md__668__auto__ data1170)))
   java.lang.String
   (compute-keccak-2241165
    [data1170]
    (clojure.core/let
     [data1170 (.getBytes data1170 "UTF-8")]
     (clojure.core/let
      [md__668__auto__
       (java.security.MessageDigest/getInstance "Keccak-224")]
      (.digest md__668__auto__ data1170)))))
  (clojure.core/extend-protocol
   G__1175
   java.io.InputStream
   (compute-keccak-2241165
    [data1170]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "Keccak-224")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1170]
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
   (compute-keccak-2241165
    [data1170]
    (clojure.core/with-open
     [data1170 (clojure.java.io/input-stream data1170)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "Keccak-224")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1170]
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
  'G__1175)
 (do
  (clojure.core/defn
   keccak-224*
   "[Hash] Keccak-224 (raw value)"
   [x]
   (compute-keccak-2241165 x))
  (clojure.core/defn
   keccak-224-file*
   "[Hash] Keccak-224 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-keccak-2241165 x)))
  (clojure.core/defn
   keccak-224-bytes
   "[Hash] Keccak-224 (value -> byte array)"
   [x]
   (compute-keccak-2241165 x))
  (clojure.core/defn
   keccak-224-file-bytes
   "[Hash] Keccak-224 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-keccak-2241165 x)))
  (clojure.core/defn
   keccak-224
   "[Hash] Keccak-224 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-keccak-2241165 x)))
  (clojure.core/defn
   keccak-224-file
   "[Hash] Keccak-224 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-keccak-2241165 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1200
   (compute-keccak-2241197 [data1198 key1199]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1200 #'compute-keccak-2241197]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1200
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-keccak-2241197
    [data1198 key1199]
    (clojure.core/let
     [mac__543__auto__
      (javax.crypto.Mac/getInstance "Hmac-Keccak224")
      msg__544__auto__
      (clojure.core/bytes data1198)
      k__545__auto__
      (javax.crypto.spec.SecretKeySpec. key1199 "Hmac-Keccak224")]
     (clojure.core/->
      (clojure.core/doto
       mac__543__auto__
       (.init k__545__auto__)
       (.update msg__544__auto__))
      (.doFinal))))
   java.lang.String
   (compute-keccak-2241197
    [data1198 key1199]
    (clojure.core/let
     [data1198 (.getBytes data1198 "UTF-8")]
     (clojure.core/let
      [mac__543__auto__
       (javax.crypto.Mac/getInstance "Hmac-Keccak224")
       msg__544__auto__
       (clojure.core/bytes data1198)
       k__545__auto__
       (javax.crypto.spec.SecretKeySpec. key1199 "Hmac-Keccak224")]
      (clojure.core/->
       (clojure.core/doto
        mac__543__auto__
        (.init k__545__auto__)
        (.update msg__544__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1200
   java.io.InputStream
   (compute-keccak-2241197
    [data1198 key1199]
    (clojure.core/let
     [mac__546__auto__
      (javax.crypto.Mac/getInstance "Hmac-Keccak224")
      k__547__auto__
      (javax.crypto.spec.SecretKeySpec. key1199 "Hmac-Keccak224")
      c__548__auto__
      (clojure.core/int *buffer-size*)
      buf__549__auto__
      (clojure.core/byte-array c__548__auto__)
      s__550__auto__
      data1198]
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
   (compute-keccak-2241197
    [data1198 key1199]
    (clojure.core/with-open
     [data1198 (clojure.java.io/input-stream data1198)]
     (clojure.core/let
      [mac__546__auto__
       (javax.crypto.Mac/getInstance "Hmac-Keccak224")
       k__547__auto__
       (javax.crypto.spec.SecretKeySpec. key1199 "Hmac-Keccak224")
       c__548__auto__
       (clojure.core/int *buffer-size*)
       buf__549__auto__
       (clojure.core/byte-array c__548__auto__)
       s__550__auto__
       data1198]
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
  'G__1200)
 (do
  (clojure.core/defn
   keccak-224-hmac*
   "[HMAC] Hmac-Keccak224 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-keccak-2241197
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   keccak-224-hmac-file*
   "[HMAC] Hmac-Keccak224 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-keccak-2241197
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   keccak-224-hmac-bytes
   "[HMAC] Hmac-Keccak224 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-keccak-2241197
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   keccak-224-hmac-file-bytes
   "[HMAC] Hmac-Keccak224 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-keccak-2241197
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   keccak-224-hmac
   "[HMAC] Hmac-Keccak224 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-keccak-2241197
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   keccak-224-hmac-file
   "[HMAC] Hmac-Keccak224 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-keccak-2241197
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
