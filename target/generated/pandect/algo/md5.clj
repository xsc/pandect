(ns
 pandect.algo.md5
 "MD5 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1222 (compute-md51220 [data1221]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1222 #'compute-md51220]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1222
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md51220
    [data1221]
    (clojure.core/let
     [md__668__auto__ (java.security.MessageDigest/getInstance "MD5")]
     (.digest md__668__auto__ data1221)))
   java.lang.String
   (compute-md51220
    [data1221]
    (clojure.core/let
     [data1221 (.getBytes data1221 "UTF-8")]
     (clojure.core/let
      [md__668__auto__ (java.security.MessageDigest/getInstance "MD5")]
      (.digest md__668__auto__ data1221)))))
  (clojure.core/extend-protocol
   G__1222
   java.io.InputStream
   (compute-md51220
    [data1221]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "MD5")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1221]
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
   (compute-md51220
    [data1221]
    (clojure.core/with-open
     [data1221 (clojure.java.io/input-stream data1221)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "MD5")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1221]
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
  'G__1222)
 (do
  (clojure.core/defn
   md5*
   "[Hash] MD5 (raw value)"
   [x]
   (compute-md51220 x))
  (clojure.core/defn
   md5-file*
   "[Hash] MD5 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md51220 x)))
  (clojure.core/defn
   md5-bytes
   "[Hash] MD5 (value -> byte array)"
   [x]
   (compute-md51220 x))
  (clojure.core/defn
   md5-file-bytes
   "[Hash] MD5 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md51220 x)))
  (clojure.core/defn
   md5
   "[Hash] MD5 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-md51220 x)))
  (clojure.core/defn
   md5-file
   "[Hash] MD5 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-md51220 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1251
   (compute-md51248 [data1249 key1250]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1251 #'compute-md51248]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1251
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md51248
    [data1249 key1250]
    (clojure.core/let
     [mac__543__auto__
      (javax.crypto.Mac/getInstance "HmacMD5")
      msg__544__auto__
      (clojure.core/bytes data1249)
      k__545__auto__
      (javax.crypto.spec.SecretKeySpec. key1250 "HmacMD5")]
     (clojure.core/->
      (clojure.core/doto
       mac__543__auto__
       (.init k__545__auto__)
       (.update msg__544__auto__))
      (.doFinal))))
   java.lang.String
   (compute-md51248
    [data1249 key1250]
    (clojure.core/let
     [data1249 (.getBytes data1249 "UTF-8")]
     (clojure.core/let
      [mac__543__auto__
       (javax.crypto.Mac/getInstance "HmacMD5")
       msg__544__auto__
       (clojure.core/bytes data1249)
       k__545__auto__
       (javax.crypto.spec.SecretKeySpec. key1250 "HmacMD5")]
      (clojure.core/->
       (clojure.core/doto
        mac__543__auto__
        (.init k__545__auto__)
        (.update msg__544__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1251
   java.io.InputStream
   (compute-md51248
    [data1249 key1250]
    (clojure.core/let
     [mac__546__auto__
      (javax.crypto.Mac/getInstance "HmacMD5")
      k__547__auto__
      (javax.crypto.spec.SecretKeySpec. key1250 "HmacMD5")
      c__548__auto__
      (clojure.core/int *buffer-size*)
      buf__549__auto__
      (clojure.core/byte-array c__548__auto__)
      s__550__auto__
      data1249]
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
   (compute-md51248
    [data1249 key1250]
    (clojure.core/with-open
     [data1249 (clojure.java.io/input-stream data1249)]
     (clojure.core/let
      [mac__546__auto__
       (javax.crypto.Mac/getInstance "HmacMD5")
       k__547__auto__
       (javax.crypto.spec.SecretKeySpec. key1250 "HmacMD5")
       c__548__auto__
       (clojure.core/int *buffer-size*)
       buf__549__auto__
       (clojure.core/byte-array c__548__auto__)
       s__550__auto__
       data1249]
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
  'G__1251)
 (do
  (clojure.core/defn
   md5-hmac*
   "[HMAC] HmacMD5 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-md51248
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   md5-hmac-file*
   "[HMAC] HmacMD5 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md51248
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   md5-hmac-bytes
   "[HMAC] HmacMD5 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-md51248
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   md5-hmac-file-bytes
   "[HMAC] HmacMD5 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md51248
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   md5-hmac
   "[HMAC] HmacMD5 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-md51248
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   md5-hmac-file
   "[HMAC] HmacMD5 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-md51248
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
(do
 (do
  (clojure.core/defprotocol
   G__1279
   (compute-md51275 [data1276 key1278])
   (compute-md51275-verify [data1276 sig1277 key1278]))
  (clojure.core/doseq
   [v__186__auto__
    [#'G__1279 #'compute-md51275 #'compute-md51275-verify]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1279
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md51275
    [data1276 key1278]
    (clojure.core/let
     [signer__847__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "MD5withRSA")
       (.initSign key1278)
       (.update (clojure.core/bytes data1276)))]
     (.sign signer__847__auto__)))
   (compute-md51275-verify
    [data1276 sig1277 key1278]
    (clojure.core/let
     [signer__848__auto__
      (java.security.Signature/getInstance "MD5withRSA")
      key__849__auto__
      (pandect.utils.convert/as-public-key key1278)]
     (.initVerify signer__848__auto__ key__849__auto__)
     (.update signer__848__auto__ (clojure.core/bytes data1276))
     (.verify signer__848__auto__ (clojure.core/bytes sig1277))))
   java.lang.String
   (compute-md51275
    [data1276 key1278]
    (clojure.core/let
     [data1276 (.getBytes data1276 "UTF-8")]
     (clojure.core/let
      [signer__847__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "MD5withRSA")
        (.initSign key1278)
        (.update (clojure.core/bytes data1276)))]
      (.sign signer__847__auto__))))
   (compute-md51275-verify
    [data1276 sig1277 key1278]
    (clojure.core/let
     [data1276 (.getBytes data1276 "UTF-8")]
     (clojure.core/let
      [signer__848__auto__
       (java.security.Signature/getInstance "MD5withRSA")
       key__849__auto__
       (pandect.utils.convert/as-public-key key1278)]
      (.initVerify signer__848__auto__ key__849__auto__)
      (.update signer__848__auto__ (clojure.core/bytes data1276))
      (.verify signer__848__auto__ (clojure.core/bytes sig1277))))))
  (clojure.core/extend-protocol
   G__1279
   java.io.InputStream
   (compute-md51275
    [data1276 key1278]
    (clojure.core/let
     [signer__850__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "MD5withRSA")
       (.initSign key1278))
      c__851__auto__
      (clojure.core/int *buffer-size*)
      buf__852__auto__
      (clojure.core/byte-array c__851__auto__)
      s__853__auto__
      data1276]
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
   (compute-md51275-verify
    [data1276 sig1277 key1278]
    (clojure.core/let
     [signer__855__auto__
      (java.security.Signature/getInstance "MD5withRSA")
      c__856__auto__
      (clojure.core/int *buffer-size*)
      buf__857__auto__
      (clojure.core/byte-array c__856__auto__)
      s__858__auto__
      data1276
      key__859__auto__
      (pandect.utils.convert/as-public-key key1278)]
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
     (.verify signer__855__auto__ (clojure.core/bytes sig1277))))
   java.io.File
   (compute-md51275
    [data1276 key1278]
    (clojure.core/with-open
     [data1276 (clojure.java.io/input-stream data1276)]
     (clojure.core/let
      [signer__850__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "MD5withRSA")
        (.initSign key1278))
       c__851__auto__
       (clojure.core/int *buffer-size*)
       buf__852__auto__
       (clojure.core/byte-array c__851__auto__)
       s__853__auto__
       data1276]
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
   (compute-md51275-verify
    [data1276 sig1277 key1278]
    (clojure.core/with-open
     [data1276 (clojure.java.io/input-stream data1276)]
     (clojure.core/let
      [signer__855__auto__
       (java.security.Signature/getInstance "MD5withRSA")
       c__856__auto__
       (clojure.core/int *buffer-size*)
       buf__857__auto__
       (clojure.core/byte-array c__856__auto__)
       s__858__auto__
       data1276
       key__859__auto__
       (pandect.utils.convert/as-public-key key1278)]
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
      (.verify signer__855__auto__ (clojure.core/bytes sig1277))))))
  'G__1279)
 (do
  (clojure.core/defn
   md5-rsa*
   "[Signature] MD5withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-md51275 x private-key))
  (clojure.core/defn
   md5-rsa-file*
   "[Signature] MD5withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md51275 x private-key)))
  (clojure.core/defn
   md5-rsa-bytes
   "[Signature] MD5withRSA (value -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-md51275 x private-key))
  (clojure.core/defn
   md5-rsa-file-bytes
   "[Signature] MD5withRSA (file path -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md51275 x private-key)))
  (clojure.core/defn
   md5-rsa
   "[Signature] MD5withRSA (value -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (pandect.utils.convert/bytes->hex (compute-md51275 x private-key)))
  (clojure.core/defn
   md5-rsa-file
   "[Signature] MD5withRSA (file path -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-md51275 x private-key))))
  (clojure.core/defn
   md5-rsa-verify
   "[Signature] MD5withRSA\n\nVerify the given message signature using the given public key (anything implementing `pandect.utils.convert/PublicKeyConvertable`)\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or anything implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (compute-md51275-verify
    x
    (pandect.utils.convert/convert-signature-to-byte-array signature)
    (pandect.utils.convert/convert-to-public-key public-key)))
  (clojure.core/defn
   md5-rsa-verify-file
   "[Signature] MD5withRSA\n\nVerify the given message signature using the given public key (anything implementing `pandect.utils.convert/PublicKeyConvertable`)\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or anything implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md51275-verify
     x
     (pandect.utils.convert/convert-signature-to-byte-array signature)
     (pandect.utils.convert/convert-to-public-key public-key))))))
