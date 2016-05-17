(ns
 pandect.algo.ripemd256
 "RipeMD256 algorithm implementation\n\n(requires `org.bouncycastle/bcprov-jdk15on` to be on the classpath)"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  pandect.utils.bouncy-castle-provider))
(do
 (do
  (clojure.core/defprotocol G__1234 (compute-ripemd2561232 [data1233]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1234 #'compute-ripemd2561232]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1234
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-ripemd2561232
    [data1233]
    (clojure.core/let
     [md__668__auto__
      (java.security.MessageDigest/getInstance "RipeMD256")]
     (.digest md__668__auto__ data1233)))
   java.lang.String
   (compute-ripemd2561232
    [data1233]
    (clojure.core/let
     [data1233 (.getBytes data1233 "UTF-8")]
     (clojure.core/let
      [md__668__auto__
       (java.security.MessageDigest/getInstance "RipeMD256")]
      (.digest md__668__auto__ data1233)))))
  (clojure.core/extend-protocol
   G__1234
   java.io.InputStream
   (compute-ripemd2561232
    [data1233]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "RipeMD256")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1233]
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
   (compute-ripemd2561232
    [data1233]
    (clojure.core/with-open
     [data1233 (clojure.java.io/input-stream data1233)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "RipeMD256")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1233]
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
  'G__1234)
 (do
  (clojure.core/defn
   ripemd256*
   "[Hash] RipeMD256 (raw value)"
   [x]
   (compute-ripemd2561232 x))
  (clojure.core/defn
   ripemd256-file*
   "[Hash] RipeMD256 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd2561232 x)))
  (clojure.core/defn
   ripemd256-bytes
   "[Hash] RipeMD256 (value -> byte array)"
   [x]
   (compute-ripemd2561232 x))
  (clojure.core/defn
   ripemd256-file-bytes
   "[Hash] RipeMD256 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd2561232 x)))
  (clojure.core/defn
   ripemd256
   "[Hash] RipeMD256 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-ripemd2561232 x)))
  (clojure.core/defn
   ripemd256-file
   "[Hash] RipeMD256 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-ripemd2561232 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1259
   (compute-ripemd2561256 [data1257 key1258]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1259 #'compute-ripemd2561256]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1259
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-ripemd2561256
    [data1257 key1258]
    (clojure.core/let
     [mac__543__auto__
      (javax.crypto.Mac/getInstance "Hmac-RipeMD256")
      msg__544__auto__
      (clojure.core/bytes data1257)
      k__545__auto__
      (javax.crypto.spec.SecretKeySpec. key1258 "Hmac-RipeMD256")]
     (clojure.core/->
      (clojure.core/doto
       mac__543__auto__
       (.init k__545__auto__)
       (.update msg__544__auto__))
      (.doFinal))))
   java.lang.String
   (compute-ripemd2561256
    [data1257 key1258]
    (clojure.core/let
     [data1257 (.getBytes data1257 "UTF-8")]
     (clojure.core/let
      [mac__543__auto__
       (javax.crypto.Mac/getInstance "Hmac-RipeMD256")
       msg__544__auto__
       (clojure.core/bytes data1257)
       k__545__auto__
       (javax.crypto.spec.SecretKeySpec. key1258 "Hmac-RipeMD256")]
      (clojure.core/->
       (clojure.core/doto
        mac__543__auto__
        (.init k__545__auto__)
        (.update msg__544__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1259
   java.io.InputStream
   (compute-ripemd2561256
    [data1257 key1258]
    (clojure.core/let
     [mac__546__auto__
      (javax.crypto.Mac/getInstance "Hmac-RipeMD256")
      k__547__auto__
      (javax.crypto.spec.SecretKeySpec. key1258 "Hmac-RipeMD256")
      c__548__auto__
      (clojure.core/int *buffer-size*)
      buf__549__auto__
      (clojure.core/byte-array c__548__auto__)
      s__550__auto__
      data1257]
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
   (compute-ripemd2561256
    [data1257 key1258]
    (clojure.core/with-open
     [data1257 (clojure.java.io/input-stream data1257)]
     (clojure.core/let
      [mac__546__auto__
       (javax.crypto.Mac/getInstance "Hmac-RipeMD256")
       k__547__auto__
       (javax.crypto.spec.SecretKeySpec. key1258 "Hmac-RipeMD256")
       c__548__auto__
       (clojure.core/int *buffer-size*)
       buf__549__auto__
       (clojure.core/byte-array c__548__auto__)
       s__550__auto__
       data1257]
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
  'G__1259)
 (do
  (clojure.core/defn
   ripemd256-hmac*
   "[HMAC] Hmac-RipeMD256 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-ripemd2561256
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   ripemd256-hmac-file*
   "[HMAC] Hmac-RipeMD256 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd2561256
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd256-hmac-bytes
   "[HMAC] Hmac-RipeMD256 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-ripemd2561256
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   ripemd256-hmac-file-bytes
   "[HMAC] Hmac-RipeMD256 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-ripemd2561256
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd256-hmac
   "[HMAC] Hmac-RipeMD256 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-ripemd2561256
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd256-hmac-file
   "[HMAC] Hmac-RipeMD256 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-ripemd2561256
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
