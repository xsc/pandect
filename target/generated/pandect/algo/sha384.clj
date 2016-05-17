(ns
 pandect.algo.sha384
 "SHA-384 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1271 (compute-sha3841269 [data1270]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1271 #'compute-sha3841269]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1271
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha3841269
    [data1270]
    (clojure.core/let
     [md__668__auto__
      (java.security.MessageDigest/getInstance "SHA-384")]
     (.digest md__668__auto__ data1270)))
   java.lang.String
   (compute-sha3841269
    [data1270]
    (clojure.core/let
     [data1270 (.getBytes data1270 "UTF-8")]
     (clojure.core/let
      [md__668__auto__
       (java.security.MessageDigest/getInstance "SHA-384")]
      (.digest md__668__auto__ data1270)))))
  (clojure.core/extend-protocol
   G__1271
   java.io.InputStream
   (compute-sha3841269
    [data1270]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "SHA-384")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1270]
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
   (compute-sha3841269
    [data1270]
    (clojure.core/with-open
     [data1270 (clojure.java.io/input-stream data1270)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "SHA-384")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1270]
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
  'G__1271)
 (do
  (clojure.core/defn
   sha384*
   "[Hash] SHA-384 (raw value)"
   [x]
   (compute-sha3841269 x))
  (clojure.core/defn
   sha384-file*
   "[Hash] SHA-384 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3841269 x)))
  (clojure.core/defn
   sha384-bytes
   "[Hash] SHA-384 (value -> byte array)"
   [x]
   (compute-sha3841269 x))
  (clojure.core/defn
   sha384-file-bytes
   "[Hash] SHA-384 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3841269 x)))
  (clojure.core/defn
   sha384
   "[Hash] SHA-384 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha3841269 x)))
  (clojure.core/defn
   sha384-file
   "[Hash] SHA-384 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-sha3841269 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1283
   (compute-sha3841280 [data1281 key1282]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1283 #'compute-sha3841280]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1283
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha3841280
    [data1281 key1282]
    (clojure.core/let
     [mac__543__auto__
      (javax.crypto.Mac/getInstance "HmacSHA384")
      msg__544__auto__
      (clojure.core/bytes data1281)
      k__545__auto__
      (javax.crypto.spec.SecretKeySpec. key1282 "HmacSHA384")]
     (clojure.core/->
      (clojure.core/doto
       mac__543__auto__
       (.init k__545__auto__)
       (.update msg__544__auto__))
      (.doFinal))))
   java.lang.String
   (compute-sha3841280
    [data1281 key1282]
    (clojure.core/let
     [data1281 (.getBytes data1281 "UTF-8")]
     (clojure.core/let
      [mac__543__auto__
       (javax.crypto.Mac/getInstance "HmacSHA384")
       msg__544__auto__
       (clojure.core/bytes data1281)
       k__545__auto__
       (javax.crypto.spec.SecretKeySpec. key1282 "HmacSHA384")]
      (clojure.core/->
       (clojure.core/doto
        mac__543__auto__
        (.init k__545__auto__)
        (.update msg__544__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1283
   java.io.InputStream
   (compute-sha3841280
    [data1281 key1282]
    (clojure.core/let
     [mac__546__auto__
      (javax.crypto.Mac/getInstance "HmacSHA384")
      k__547__auto__
      (javax.crypto.spec.SecretKeySpec. key1282 "HmacSHA384")
      c__548__auto__
      (clojure.core/int *buffer-size*)
      buf__549__auto__
      (clojure.core/byte-array c__548__auto__)
      s__550__auto__
      data1281]
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
   (compute-sha3841280
    [data1281 key1282]
    (clojure.core/with-open
     [data1281 (clojure.java.io/input-stream data1281)]
     (clojure.core/let
      [mac__546__auto__
       (javax.crypto.Mac/getInstance "HmacSHA384")
       k__547__auto__
       (javax.crypto.spec.SecretKeySpec. key1282 "HmacSHA384")
       c__548__auto__
       (clojure.core/int *buffer-size*)
       buf__549__auto__
       (clojure.core/byte-array c__548__auto__)
       s__550__auto__
       data1281]
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
  'G__1283)
 (do
  (clojure.core/defn
   sha384-hmac*
   "[HMAC] HmacSHA384 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha3841280
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha384-hmac-file*
   "[HMAC] HmacSHA384 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3841280
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha384-hmac-bytes
   "[HMAC] HmacSHA384 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha3841280
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha384-hmac-file-bytes
   "[HMAC] HmacSHA384 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3841280
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha384-hmac
   "[HMAC] HmacSHA384 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha3841280
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha384-hmac-file
   "[HMAC] HmacSHA384 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha3841280
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
(do
 (do
  (clojure.core/defprotocol
   G__1324
   (compute-sha3841320 [data1321 key1323])
   (compute-sha3841320-verify [data1321 sig1322 key1323]))
  (clojure.core/doseq
   [v__186__auto__
    [#'G__1324 #'compute-sha3841320 #'compute-sha3841320-verify]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1324
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha3841320
    [data1321 key1323]
    (clojure.core/let
     [signer__847__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA384withRSA")
       (.initSign key1323)
       (.update (clojure.core/bytes data1321)))]
     (.sign signer__847__auto__)))
   (compute-sha3841320-verify
    [data1321 sig1322 key1323]
    (clojure.core/let
     [signer__848__auto__
      (java.security.Signature/getInstance "SHA384withRSA")
      key__849__auto__
      (pandect.utils.convert/as-public-key key1323)]
     (.initVerify signer__848__auto__ key__849__auto__)
     (.update signer__848__auto__ (clojure.core/bytes data1321))
     (.verify signer__848__auto__ (clojure.core/bytes sig1322))))
   java.lang.String
   (compute-sha3841320
    [data1321 key1323]
    (clojure.core/let
     [data1321 (.getBytes data1321 "UTF-8")]
     (clojure.core/let
      [signer__847__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA384withRSA")
        (.initSign key1323)
        (.update (clojure.core/bytes data1321)))]
      (.sign signer__847__auto__))))
   (compute-sha3841320-verify
    [data1321 sig1322 key1323]
    (clojure.core/let
     [data1321 (.getBytes data1321 "UTF-8")]
     (clojure.core/let
      [signer__848__auto__
       (java.security.Signature/getInstance "SHA384withRSA")
       key__849__auto__
       (pandect.utils.convert/as-public-key key1323)]
      (.initVerify signer__848__auto__ key__849__auto__)
      (.update signer__848__auto__ (clojure.core/bytes data1321))
      (.verify signer__848__auto__ (clojure.core/bytes sig1322))))))
  (clojure.core/extend-protocol
   G__1324
   java.io.InputStream
   (compute-sha3841320
    [data1321 key1323]
    (clojure.core/let
     [signer__850__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA384withRSA")
       (.initSign key1323))
      c__851__auto__
      (clojure.core/int *buffer-size*)
      buf__852__auto__
      (clojure.core/byte-array c__851__auto__)
      s__853__auto__
      data1321]
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
   (compute-sha3841320-verify
    [data1321 sig1322 key1323]
    (clojure.core/let
     [signer__855__auto__
      (java.security.Signature/getInstance "SHA384withRSA")
      c__856__auto__
      (clojure.core/int *buffer-size*)
      buf__857__auto__
      (clojure.core/byte-array c__856__auto__)
      s__858__auto__
      data1321
      key__859__auto__
      (pandect.utils.convert/as-public-key key1323)]
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
     (.verify signer__855__auto__ (clojure.core/bytes sig1322))))
   java.io.File
   (compute-sha3841320
    [data1321 key1323]
    (clojure.core/with-open
     [data1321 (clojure.java.io/input-stream data1321)]
     (clojure.core/let
      [signer__850__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA384withRSA")
        (.initSign key1323))
       c__851__auto__
       (clojure.core/int *buffer-size*)
       buf__852__auto__
       (clojure.core/byte-array c__851__auto__)
       s__853__auto__
       data1321]
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
   (compute-sha3841320-verify
    [data1321 sig1322 key1323]
    (clojure.core/with-open
     [data1321 (clojure.java.io/input-stream data1321)]
     (clojure.core/let
      [signer__855__auto__
       (java.security.Signature/getInstance "SHA384withRSA")
       c__856__auto__
       (clojure.core/int *buffer-size*)
       buf__857__auto__
       (clojure.core/byte-array c__856__auto__)
       s__858__auto__
       data1321
       key__859__auto__
       (pandect.utils.convert/as-public-key key1323)]
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
      (.verify signer__855__auto__ (clojure.core/bytes sig1322))))))
  'G__1324)
 (do
  (clojure.core/defn
   sha384-rsa*
   "[Signature] SHA384withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha3841320 x private-key))
  (clojure.core/defn
   sha384-rsa-file*
   "[Signature] SHA384withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3841320 x private-key)))
  (clojure.core/defn
   sha384-rsa-bytes
   "[Signature] SHA384withRSA (value -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha3841320 x private-key))
  (clojure.core/defn
   sha384-rsa-file-bytes
   "[Signature] SHA384withRSA (file path -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3841320 x private-key)))
  (clojure.core/defn
   sha384-rsa
   "[Signature] SHA384withRSA (value -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (pandect.utils.convert/bytes->hex
    (compute-sha3841320 x private-key)))
  (clojure.core/defn
   sha384-rsa-file
   "[Signature] SHA384withRSA (file path -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha3841320 x private-key))))
  (clojure.core/defn
   sha384-rsa-verify
   "[Signature] SHA384withRSA\n\nVerify the given message signature using the given public key (anything implementing `pandect.utils.convert/PublicKeyConvertable`)\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or anything implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (compute-sha3841320-verify
    x
    (pandect.utils.convert/convert-signature-to-byte-array signature)
    (pandect.utils.convert/convert-to-public-key public-key)))
  (clojure.core/defn
   sha384-rsa-verify-file
   "[Signature] SHA384withRSA\n\nVerify the given message signature using the given public key (anything implementing `pandect.utils.convert/PublicKeyConvertable`)\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or anything implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3841320-verify
     x
     (pandect.utils.convert/convert-signature-to-byte-array signature)
     (pandect.utils.convert/convert-to-public-key public-key))))))
