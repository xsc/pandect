(ns
 pandect.algo.sha256
 "SHA-256 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1268 (compute-sha2561266 [data1267]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1268 #'compute-sha2561266]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1268
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha2561266
    [data1267]
    (clojure.core/let
     [md__668__auto__
      (java.security.MessageDigest/getInstance "SHA-256")]
     (.digest md__668__auto__ data1267)))
   java.lang.String
   (compute-sha2561266
    [data1267]
    (clojure.core/let
     [data1267 (.getBytes data1267 "UTF-8")]
     (clojure.core/let
      [md__668__auto__
       (java.security.MessageDigest/getInstance "SHA-256")]
      (.digest md__668__auto__ data1267)))))
  (clojure.core/extend-protocol
   G__1268
   java.io.InputStream
   (compute-sha2561266
    [data1267]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "SHA-256")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1267]
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
   (compute-sha2561266
    [data1267]
    (clojure.core/with-open
     [data1267 (clojure.java.io/input-stream data1267)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "SHA-256")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1267]
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
  'G__1268)
 (do
  (clojure.core/defn
   sha256*
   "[Hash] SHA-256 (raw value)"
   [x]
   (compute-sha2561266 x))
  (clojure.core/defn
   sha256-file*
   "[Hash] SHA-256 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2561266 x)))
  (clojure.core/defn
   sha256-bytes
   "[Hash] SHA-256 (value -> byte array)"
   [x]
   (compute-sha2561266 x))
  (clojure.core/defn
   sha256-file-bytes
   "[Hash] SHA-256 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2561266 x)))
  (clojure.core/defn
   sha256
   "[Hash] SHA-256 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha2561266 x)))
  (clojure.core/defn
   sha256-file
   "[Hash] SHA-256 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-sha2561266 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1287
   (compute-sha2561284 [data1285 key1286]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1287 #'compute-sha2561284]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1287
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha2561284
    [data1285 key1286]
    (clojure.core/let
     [mac__543__auto__
      (javax.crypto.Mac/getInstance "HmacSHA256")
      msg__544__auto__
      (clojure.core/bytes data1285)
      k__545__auto__
      (javax.crypto.spec.SecretKeySpec. key1286 "HmacSHA256")]
     (clojure.core/->
      (clojure.core/doto
       mac__543__auto__
       (.init k__545__auto__)
       (.update msg__544__auto__))
      (.doFinal))))
   java.lang.String
   (compute-sha2561284
    [data1285 key1286]
    (clojure.core/let
     [data1285 (.getBytes data1285 "UTF-8")]
     (clojure.core/let
      [mac__543__auto__
       (javax.crypto.Mac/getInstance "HmacSHA256")
       msg__544__auto__
       (clojure.core/bytes data1285)
       k__545__auto__
       (javax.crypto.spec.SecretKeySpec. key1286 "HmacSHA256")]
      (clojure.core/->
       (clojure.core/doto
        mac__543__auto__
        (.init k__545__auto__)
        (.update msg__544__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1287
   java.io.InputStream
   (compute-sha2561284
    [data1285 key1286]
    (clojure.core/let
     [mac__546__auto__
      (javax.crypto.Mac/getInstance "HmacSHA256")
      k__547__auto__
      (javax.crypto.spec.SecretKeySpec. key1286 "HmacSHA256")
      c__548__auto__
      (clojure.core/int *buffer-size*)
      buf__549__auto__
      (clojure.core/byte-array c__548__auto__)
      s__550__auto__
      data1285]
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
   (compute-sha2561284
    [data1285 key1286]
    (clojure.core/with-open
     [data1285 (clojure.java.io/input-stream data1285)]
     (clojure.core/let
      [mac__546__auto__
       (javax.crypto.Mac/getInstance "HmacSHA256")
       k__547__auto__
       (javax.crypto.spec.SecretKeySpec. key1286 "HmacSHA256")
       c__548__auto__
       (clojure.core/int *buffer-size*)
       buf__549__auto__
       (clojure.core/byte-array c__548__auto__)
       s__550__auto__
       data1285]
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
  'G__1287)
 (do
  (clojure.core/defn
   sha256-hmac*
   "[HMAC] HmacSHA256 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha2561284
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha256-hmac-file*
   "[HMAC] HmacSHA256 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2561284
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha256-hmac-bytes
   "[HMAC] HmacSHA256 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha2561284
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha256-hmac-file-bytes
   "[HMAC] HmacSHA256 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2561284
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha256-hmac
   "[HMAC] HmacSHA256 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha2561284
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha256-hmac-file
   "[HMAC] HmacSHA256 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha2561284
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
(do
 (do
  (clojure.core/defprotocol
   G__1314
   (compute-sha2561310 [data1311 key1313])
   (compute-sha2561310-verify [data1311 sig1312 key1313]))
  (clojure.core/doseq
   [v__186__auto__
    [#'G__1314 #'compute-sha2561310 #'compute-sha2561310-verify]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1314
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha2561310
    [data1311 key1313]
    (clojure.core/let
     [signer__847__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA256withRSA")
       (.initSign key1313)
       (.update (clojure.core/bytes data1311)))]
     (.sign signer__847__auto__)))
   (compute-sha2561310-verify
    [data1311 sig1312 key1313]
    (clojure.core/let
     [signer__848__auto__
      (java.security.Signature/getInstance "SHA256withRSA")
      key__849__auto__
      (pandect.utils.convert/as-public-key key1313)]
     (.initVerify signer__848__auto__ key__849__auto__)
     (.update signer__848__auto__ (clojure.core/bytes data1311))
     (.verify signer__848__auto__ (clojure.core/bytes sig1312))))
   java.lang.String
   (compute-sha2561310
    [data1311 key1313]
    (clojure.core/let
     [data1311 (.getBytes data1311 "UTF-8")]
     (clojure.core/let
      [signer__847__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA256withRSA")
        (.initSign key1313)
        (.update (clojure.core/bytes data1311)))]
      (.sign signer__847__auto__))))
   (compute-sha2561310-verify
    [data1311 sig1312 key1313]
    (clojure.core/let
     [data1311 (.getBytes data1311 "UTF-8")]
     (clojure.core/let
      [signer__848__auto__
       (java.security.Signature/getInstance "SHA256withRSA")
       key__849__auto__
       (pandect.utils.convert/as-public-key key1313)]
      (.initVerify signer__848__auto__ key__849__auto__)
      (.update signer__848__auto__ (clojure.core/bytes data1311))
      (.verify signer__848__auto__ (clojure.core/bytes sig1312))))))
  (clojure.core/extend-protocol
   G__1314
   java.io.InputStream
   (compute-sha2561310
    [data1311 key1313]
    (clojure.core/let
     [signer__850__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA256withRSA")
       (.initSign key1313))
      c__851__auto__
      (clojure.core/int *buffer-size*)
      buf__852__auto__
      (clojure.core/byte-array c__851__auto__)
      s__853__auto__
      data1311]
     (clojure.core/loop
      []
      (clojure.core/let
       [r__854__auto__
        (.read s__853__auto__ buf__852__auto__ 0 c__851__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__854__auto__ -1)
        (.update signer__850__auto__ buf__852__auto__ 0 r__854__auto__)
        (recur))))
     (.sign signer__850__auto__)))
   (compute-sha2561310-verify
    [data1311 sig1312 key1313]
    (clojure.core/let
     [signer__855__auto__
      (java.security.Signature/getInstance "SHA256withRSA")
      c__856__auto__
      (clojure.core/int *buffer-size*)
      buf__857__auto__
      (clojure.core/byte-array c__856__auto__)
      s__858__auto__
      data1311
      key__859__auto__
      (pandect.utils.convert/as-public-key key1313)]
     (.initVerify signer__855__auto__ key__859__auto__)
     (clojure.core/loop
      []
      (clojure.core/let
       [r__860__auto__
        (.read s__858__auto__ buf__857__auto__ 0 c__856__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__860__auto__ -1)
        (.update signer__855__auto__ buf__857__auto__ 0 r__860__auto__)
        (recur))))
     (.verify signer__855__auto__ (clojure.core/bytes sig1312))))
   java.io.File
   (compute-sha2561310
    [data1311 key1313]
    (clojure.core/with-open
     [data1311 (clojure.java.io/input-stream data1311)]
     (clojure.core/let
      [signer__850__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA256withRSA")
        (.initSign key1313))
       c__851__auto__
       (clojure.core/int *buffer-size*)
       buf__852__auto__
       (clojure.core/byte-array c__851__auto__)
       s__853__auto__
       data1311]
      (clojure.core/loop
       []
       (clojure.core/let
        [r__854__auto__
         (.read s__853__auto__ buf__852__auto__ 0 c__851__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__854__auto__ -1)
         (.update
          signer__850__auto__
          buf__852__auto__
          0
          r__854__auto__)
         (recur))))
      (.sign signer__850__auto__))))
   (compute-sha2561310-verify
    [data1311 sig1312 key1313]
    (clojure.core/with-open
     [data1311 (clojure.java.io/input-stream data1311)]
     (clojure.core/let
      [signer__855__auto__
       (java.security.Signature/getInstance "SHA256withRSA")
       c__856__auto__
       (clojure.core/int *buffer-size*)
       buf__857__auto__
       (clojure.core/byte-array c__856__auto__)
       s__858__auto__
       data1311
       key__859__auto__
       (pandect.utils.convert/as-public-key key1313)]
      (.initVerify signer__855__auto__ key__859__auto__)
      (clojure.core/loop
       []
       (clojure.core/let
        [r__860__auto__
         (.read s__858__auto__ buf__857__auto__ 0 c__856__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__860__auto__ -1)
         (.update
          signer__855__auto__
          buf__857__auto__
          0
          r__860__auto__)
         (recur))))
      (.verify signer__855__auto__ (clojure.core/bytes sig1312))))))
  'G__1314)
 (do
  (clojure.core/defn
   sha256-rsa*
   "[Signature] SHA256withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha2561310 x private-key))
  (clojure.core/defn
   sha256-rsa-file*
   "[Signature] SHA256withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2561310 x private-key)))
  (clojure.core/defn
   sha256-rsa-bytes
   "[Signature] SHA256withRSA (value -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha2561310 x private-key))
  (clojure.core/defn
   sha256-rsa-file-bytes
   "[Signature] SHA256withRSA (file path -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2561310 x private-key)))
  (clojure.core/defn
   sha256-rsa
   "[Signature] SHA256withRSA (value -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (pandect.utils.convert/bytes->hex
    (compute-sha2561310 x private-key)))
  (clojure.core/defn
   sha256-rsa-file
   "[Signature] SHA256withRSA (file path -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha2561310 x private-key))))
  (clojure.core/defn
   sha256-rsa-verify
   "[Signature] SHA256withRSA\n\nVerify the given message signature using the given public key (anything implementing `pandect.utils.convert/PublicKeyConvertable`)\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or anything implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (compute-sha2561310-verify
    x
    (pandect.utils.convert/convert-signature-to-byte-array signature)
    (pandect.utils.convert/convert-to-public-key public-key)))
  (clojure.core/defn
   sha256-rsa-verify-file
   "[Signature] SHA256withRSA\n\nVerify the given message signature using the given public key (anything implementing `pandect.utils.convert/PublicKeyConvertable`)\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or anything implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2561310-verify
     x
     (pandect.utils.convert/convert-signature-to-byte-array signature)
     (pandect.utils.convert/convert-to-public-key public-key))))))
