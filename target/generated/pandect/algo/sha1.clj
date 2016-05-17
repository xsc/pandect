(ns
 pandect.algo.sha1
 "SHA-1 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1262 (compute-sha11260 [data1261]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1262 #'compute-sha11260]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1262
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha11260
    [data1261]
    (clojure.core/let
     [md__668__auto__
      (java.security.MessageDigest/getInstance "SHA-1")]
     (.digest md__668__auto__ data1261)))
   java.lang.String
   (compute-sha11260
    [data1261]
    (clojure.core/let
     [data1261 (.getBytes data1261 "UTF-8")]
     (clojure.core/let
      [md__668__auto__
       (java.security.MessageDigest/getInstance "SHA-1")]
      (.digest md__668__auto__ data1261)))))
  (clojure.core/extend-protocol
   G__1262
   java.io.InputStream
   (compute-sha11260
    [data1261]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "SHA-1")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1261]
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
   (compute-sha11260
    [data1261]
    (clojure.core/with-open
     [data1261 (clojure.java.io/input-stream data1261)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "SHA-1")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1261]
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
  'G__1262)
 (do
  (clojure.core/defn
   sha1*
   "[Hash] SHA-1 (raw value)"
   [x]
   (compute-sha11260 x))
  (clojure.core/defn
   sha1-file*
   "[Hash] SHA-1 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11260 x)))
  (clojure.core/defn
   sha1-bytes
   "[Hash] SHA-1 (value -> byte array)"
   [x]
   (compute-sha11260 x))
  (clojure.core/defn
   sha1-file-bytes
   "[Hash] SHA-1 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11260 x)))
  (clojure.core/defn
   sha1
   "[Hash] SHA-1 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha11260 x)))
  (clojure.core/defn
   sha1-file
   "[Hash] SHA-1 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-sha11260 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1295
   (compute-sha11292 [data1293 key1294]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1295 #'compute-sha11292]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1295
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha11292
    [data1293 key1294]
    (clojure.core/let
     [mac__543__auto__
      (javax.crypto.Mac/getInstance "HmacSHA1")
      msg__544__auto__
      (clojure.core/bytes data1293)
      k__545__auto__
      (javax.crypto.spec.SecretKeySpec. key1294 "HmacSHA1")]
     (clojure.core/->
      (clojure.core/doto
       mac__543__auto__
       (.init k__545__auto__)
       (.update msg__544__auto__))
      (.doFinal))))
   java.lang.String
   (compute-sha11292
    [data1293 key1294]
    (clojure.core/let
     [data1293 (.getBytes data1293 "UTF-8")]
     (clojure.core/let
      [mac__543__auto__
       (javax.crypto.Mac/getInstance "HmacSHA1")
       msg__544__auto__
       (clojure.core/bytes data1293)
       k__545__auto__
       (javax.crypto.spec.SecretKeySpec. key1294 "HmacSHA1")]
      (clojure.core/->
       (clojure.core/doto
        mac__543__auto__
        (.init k__545__auto__)
        (.update msg__544__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1295
   java.io.InputStream
   (compute-sha11292
    [data1293 key1294]
    (clojure.core/let
     [mac__546__auto__
      (javax.crypto.Mac/getInstance "HmacSHA1")
      k__547__auto__
      (javax.crypto.spec.SecretKeySpec. key1294 "HmacSHA1")
      c__548__auto__
      (clojure.core/int *buffer-size*)
      buf__549__auto__
      (clojure.core/byte-array c__548__auto__)
      s__550__auto__
      data1293]
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
   (compute-sha11292
    [data1293 key1294]
    (clojure.core/with-open
     [data1293 (clojure.java.io/input-stream data1293)]
     (clojure.core/let
      [mac__546__auto__
       (javax.crypto.Mac/getInstance "HmacSHA1")
       k__547__auto__
       (javax.crypto.spec.SecretKeySpec. key1294 "HmacSHA1")
       c__548__auto__
       (clojure.core/int *buffer-size*)
       buf__549__auto__
       (clojure.core/byte-array c__548__auto__)
       s__550__auto__
       data1293]
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
  'G__1295)
 (do
  (clojure.core/defn
   sha1-hmac*
   "[HMAC] HmacSHA1 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha11292
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha1-hmac-file*
   "[HMAC] HmacSHA1 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11292
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha1-hmac-bytes
   "[HMAC] HmacSHA1 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha11292
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha1-hmac-file-bytes
   "[HMAC] HmacSHA1 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11292
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha1-hmac
   "[HMAC] HmacSHA1 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha11292
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha1-hmac-file
   "[HMAC] HmacSHA1 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha11292
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
(do
 (do
  (clojure.core/defprotocol
   G__1319
   (compute-sha11315 [data1316 key1318])
   (compute-sha11315-verify [data1316 sig1317 key1318]))
  (clojure.core/doseq
   [v__186__auto__
    [#'G__1319 #'compute-sha11315 #'compute-sha11315-verify]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1319
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha11315
    [data1316 key1318]
    (clojure.core/let
     [signer__847__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA1withRSA")
       (.initSign key1318)
       (.update (clojure.core/bytes data1316)))]
     (.sign signer__847__auto__)))
   (compute-sha11315-verify
    [data1316 sig1317 key1318]
    (clojure.core/let
     [signer__848__auto__
      (java.security.Signature/getInstance "SHA1withRSA")
      key__849__auto__
      (pandect.utils.convert/as-public-key key1318)]
     (.initVerify signer__848__auto__ key__849__auto__)
     (.update signer__848__auto__ (clojure.core/bytes data1316))
     (.verify signer__848__auto__ (clojure.core/bytes sig1317))))
   java.lang.String
   (compute-sha11315
    [data1316 key1318]
    (clojure.core/let
     [data1316 (.getBytes data1316 "UTF-8")]
     (clojure.core/let
      [signer__847__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA1withRSA")
        (.initSign key1318)
        (.update (clojure.core/bytes data1316)))]
      (.sign signer__847__auto__))))
   (compute-sha11315-verify
    [data1316 sig1317 key1318]
    (clojure.core/let
     [data1316 (.getBytes data1316 "UTF-8")]
     (clojure.core/let
      [signer__848__auto__
       (java.security.Signature/getInstance "SHA1withRSA")
       key__849__auto__
       (pandect.utils.convert/as-public-key key1318)]
      (.initVerify signer__848__auto__ key__849__auto__)
      (.update signer__848__auto__ (clojure.core/bytes data1316))
      (.verify signer__848__auto__ (clojure.core/bytes sig1317))))))
  (clojure.core/extend-protocol
   G__1319
   java.io.InputStream
   (compute-sha11315
    [data1316 key1318]
    (clojure.core/let
     [signer__850__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA1withRSA")
       (.initSign key1318))
      c__851__auto__
      (clojure.core/int *buffer-size*)
      buf__852__auto__
      (clojure.core/byte-array c__851__auto__)
      s__853__auto__
      data1316]
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
   (compute-sha11315-verify
    [data1316 sig1317 key1318]
    (clojure.core/let
     [signer__855__auto__
      (java.security.Signature/getInstance "SHA1withRSA")
      c__856__auto__
      (clojure.core/int *buffer-size*)
      buf__857__auto__
      (clojure.core/byte-array c__856__auto__)
      s__858__auto__
      data1316
      key__859__auto__
      (pandect.utils.convert/as-public-key key1318)]
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
     (.verify signer__855__auto__ (clojure.core/bytes sig1317))))
   java.io.File
   (compute-sha11315
    [data1316 key1318]
    (clojure.core/with-open
     [data1316 (clojure.java.io/input-stream data1316)]
     (clojure.core/let
      [signer__850__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA1withRSA")
        (.initSign key1318))
       c__851__auto__
       (clojure.core/int *buffer-size*)
       buf__852__auto__
       (clojure.core/byte-array c__851__auto__)
       s__853__auto__
       data1316]
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
   (compute-sha11315-verify
    [data1316 sig1317 key1318]
    (clojure.core/with-open
     [data1316 (clojure.java.io/input-stream data1316)]
     (clojure.core/let
      [signer__855__auto__
       (java.security.Signature/getInstance "SHA1withRSA")
       c__856__auto__
       (clojure.core/int *buffer-size*)
       buf__857__auto__
       (clojure.core/byte-array c__856__auto__)
       s__858__auto__
       data1316
       key__859__auto__
       (pandect.utils.convert/as-public-key key1318)]
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
      (.verify signer__855__auto__ (clojure.core/bytes sig1317))))))
  'G__1319)
 (do
  (clojure.core/defn
   sha1-rsa*
   "[Signature] SHA1withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha11315 x private-key))
  (clojure.core/defn
   sha1-rsa-file*
   "[Signature] SHA1withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11315 x private-key)))
  (clojure.core/defn
   sha1-rsa-bytes
   "[Signature] SHA1withRSA (value -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha11315 x private-key))
  (clojure.core/defn
   sha1-rsa-file-bytes
   "[Signature] SHA1withRSA (file path -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11315 x private-key)))
  (clojure.core/defn
   sha1-rsa
   "[Signature] SHA1withRSA (value -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (pandect.utils.convert/bytes->hex (compute-sha11315 x private-key)))
  (clojure.core/defn
   sha1-rsa-file
   "[Signature] SHA1withRSA (file path -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha11315 x private-key))))
  (clojure.core/defn
   sha1-rsa-verify
   "[Signature] SHA1withRSA\n\nVerify the given message signature using the given public key (anything implementing `pandect.utils.convert/PublicKeyConvertable`)\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or anything implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (compute-sha11315-verify
    x
    (pandect.utils.convert/convert-signature-to-byte-array signature)
    (pandect.utils.convert/convert-to-public-key public-key)))
  (clojure.core/defn
   sha1-rsa-verify-file
   "[Signature] SHA1withRSA\n\nVerify the given message signature using the given public key (anything implementing `pandect.utils.convert/PublicKeyConvertable`)\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or anything implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11315-verify
     x
     (pandect.utils.convert/convert-signature-to-byte-array signature)
     (pandect.utils.convert/convert-to-public-key public-key))))))
(do
 (do
  (clojure.core/defprotocol
   G__1351
   (compute-sha11347 [data1348 key1350])
   (compute-sha11347-verify [data1348 sig1349 key1350]))
  (clojure.core/doseq
   [v__186__auto__
    [#'G__1351 #'compute-sha11347 #'compute-sha11347-verify]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1351
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha11347
    [data1348 key1350]
    (clojure.core/let
     [signer__847__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA1withDSA")
       (.initSign key1350)
       (.update (clojure.core/bytes data1348)))]
     (.sign signer__847__auto__)))
   (compute-sha11347-verify
    [data1348 sig1349 key1350]
    (clojure.core/let
     [signer__848__auto__
      (java.security.Signature/getInstance "SHA1withDSA")
      key__849__auto__
      (pandect.utils.convert/as-public-key key1350)]
     (.initVerify signer__848__auto__ key__849__auto__)
     (.update signer__848__auto__ (clojure.core/bytes data1348))
     (.verify signer__848__auto__ (clojure.core/bytes sig1349))))
   java.lang.String
   (compute-sha11347
    [data1348 key1350]
    (clojure.core/let
     [data1348 (.getBytes data1348 "UTF-8")]
     (clojure.core/let
      [signer__847__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA1withDSA")
        (.initSign key1350)
        (.update (clojure.core/bytes data1348)))]
      (.sign signer__847__auto__))))
   (compute-sha11347-verify
    [data1348 sig1349 key1350]
    (clojure.core/let
     [data1348 (.getBytes data1348 "UTF-8")]
     (clojure.core/let
      [signer__848__auto__
       (java.security.Signature/getInstance "SHA1withDSA")
       key__849__auto__
       (pandect.utils.convert/as-public-key key1350)]
      (.initVerify signer__848__auto__ key__849__auto__)
      (.update signer__848__auto__ (clojure.core/bytes data1348))
      (.verify signer__848__auto__ (clojure.core/bytes sig1349))))))
  (clojure.core/extend-protocol
   G__1351
   java.io.InputStream
   (compute-sha11347
    [data1348 key1350]
    (clojure.core/let
     [signer__850__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA1withDSA")
       (.initSign key1350))
      c__851__auto__
      (clojure.core/int *buffer-size*)
      buf__852__auto__
      (clojure.core/byte-array c__851__auto__)
      s__853__auto__
      data1348]
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
   (compute-sha11347-verify
    [data1348 sig1349 key1350]
    (clojure.core/let
     [signer__855__auto__
      (java.security.Signature/getInstance "SHA1withDSA")
      c__856__auto__
      (clojure.core/int *buffer-size*)
      buf__857__auto__
      (clojure.core/byte-array c__856__auto__)
      s__858__auto__
      data1348
      key__859__auto__
      (pandect.utils.convert/as-public-key key1350)]
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
     (.verify signer__855__auto__ (clojure.core/bytes sig1349))))
   java.io.File
   (compute-sha11347
    [data1348 key1350]
    (clojure.core/with-open
     [data1348 (clojure.java.io/input-stream data1348)]
     (clojure.core/let
      [signer__850__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA1withDSA")
        (.initSign key1350))
       c__851__auto__
       (clojure.core/int *buffer-size*)
       buf__852__auto__
       (clojure.core/byte-array c__851__auto__)
       s__853__auto__
       data1348]
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
   (compute-sha11347-verify
    [data1348 sig1349 key1350]
    (clojure.core/with-open
     [data1348 (clojure.java.io/input-stream data1348)]
     (clojure.core/let
      [signer__855__auto__
       (java.security.Signature/getInstance "SHA1withDSA")
       c__856__auto__
       (clojure.core/int *buffer-size*)
       buf__857__auto__
       (clojure.core/byte-array c__856__auto__)
       s__858__auto__
       data1348
       key__859__auto__
       (pandect.utils.convert/as-public-key key1350)]
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
      (.verify signer__855__auto__ (clojure.core/bytes sig1349))))))
  'G__1351)
 (do
  (clojure.core/defn
   sha1-dsa*
   "[Signature] SHA1withDSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha11347 x private-key))
  (clojure.core/defn
   sha1-dsa-file*
   "[Signature] SHA1withDSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11347 x private-key)))
  (clojure.core/defn
   sha1-dsa-bytes
   "[Signature] SHA1withDSA (value -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha11347 x private-key))
  (clojure.core/defn
   sha1-dsa-file-bytes
   "[Signature] SHA1withDSA (file path -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11347 x private-key)))
  (clojure.core/defn
   sha1-dsa
   "[Signature] SHA1withDSA (value -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (pandect.utils.convert/bytes->hex (compute-sha11347 x private-key)))
  (clojure.core/defn
   sha1-dsa-file
   "[Signature] SHA1withDSA (file path -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha11347 x private-key))))
  (clojure.core/defn
   sha1-dsa-verify
   "[Signature] SHA1withDSA\n\nVerify the given message signature using the given public key (anything implementing `pandect.utils.convert/PublicKeyConvertable`)\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or anything implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (compute-sha11347-verify
    x
    (pandect.utils.convert/convert-signature-to-byte-array signature)
    (pandect.utils.convert/convert-to-public-key public-key)))
  (clojure.core/defn
   sha1-dsa-verify-file
   "[Signature] SHA1withDSA\n\nVerify the given message signature using the given public key (anything implementing `pandect.utils.convert/PublicKeyConvertable`)\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or anything implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11347-verify
     x
     (pandect.utils.convert/convert-signature-to-byte-array signature)
     (pandect.utils.convert/convert-to-public-key public-key))))))
