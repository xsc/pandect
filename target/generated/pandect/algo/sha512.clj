(ns
 pandect.algo.sha512
 "SHA-512 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1302 (compute-sha5121300 [data1301]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1302 #'compute-sha5121300]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1302
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha5121300
    [data1301]
    (clojure.core/let
     [md__668__auto__
      (java.security.MessageDigest/getInstance "SHA-512")]
     (.digest md__668__auto__ data1301)))
   java.lang.String
   (compute-sha5121300
    [data1301]
    (clojure.core/let
     [data1301 (.getBytes data1301 "UTF-8")]
     (clojure.core/let
      [md__668__auto__
       (java.security.MessageDigest/getInstance "SHA-512")]
      (.digest md__668__auto__ data1301)))))
  (clojure.core/extend-protocol
   G__1302
   java.io.InputStream
   (compute-sha5121300
    [data1301]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "SHA-512")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1301]
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
   (compute-sha5121300
    [data1301]
    (clojure.core/with-open
     [data1301 (clojure.java.io/input-stream data1301)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "SHA-512")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1301]
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
  'G__1302)
 (do
  (clojure.core/defn
   sha512*
   "[Hash] SHA-512 (raw value)"
   [x]
   (compute-sha5121300 x))
  (clojure.core/defn
   sha512-file*
   "[Hash] SHA-512 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha5121300 x)))
  (clojure.core/defn
   sha512-bytes
   "[Hash] SHA-512 (value -> byte array)"
   [x]
   (compute-sha5121300 x))
  (clojure.core/defn
   sha512-file-bytes
   "[Hash] SHA-512 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha5121300 x)))
  (clojure.core/defn
   sha512
   "[Hash] SHA-512 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha5121300 x)))
  (clojure.core/defn
   sha512-file
   "[Hash] SHA-512 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-sha5121300 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1309
   (compute-sha5121306 [data1307 key1308]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1309 #'compute-sha5121306]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1309
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha5121306
    [data1307 key1308]
    (clojure.core/let
     [mac__543__auto__
      (javax.crypto.Mac/getInstance "HmacSHA512")
      msg__544__auto__
      (clojure.core/bytes data1307)
      k__545__auto__
      (javax.crypto.spec.SecretKeySpec. key1308 "HmacSHA512")]
     (clojure.core/->
      (clojure.core/doto
       mac__543__auto__
       (.init k__545__auto__)
       (.update msg__544__auto__))
      (.doFinal))))
   java.lang.String
   (compute-sha5121306
    [data1307 key1308]
    (clojure.core/let
     [data1307 (.getBytes data1307 "UTF-8")]
     (clojure.core/let
      [mac__543__auto__
       (javax.crypto.Mac/getInstance "HmacSHA512")
       msg__544__auto__
       (clojure.core/bytes data1307)
       k__545__auto__
       (javax.crypto.spec.SecretKeySpec. key1308 "HmacSHA512")]
      (clojure.core/->
       (clojure.core/doto
        mac__543__auto__
        (.init k__545__auto__)
        (.update msg__544__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1309
   java.io.InputStream
   (compute-sha5121306
    [data1307 key1308]
    (clojure.core/let
     [mac__546__auto__
      (javax.crypto.Mac/getInstance "HmacSHA512")
      k__547__auto__
      (javax.crypto.spec.SecretKeySpec. key1308 "HmacSHA512")
      c__548__auto__
      (clojure.core/int *buffer-size*)
      buf__549__auto__
      (clojure.core/byte-array c__548__auto__)
      s__550__auto__
      data1307]
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
   (compute-sha5121306
    [data1307 key1308]
    (clojure.core/with-open
     [data1307 (clojure.java.io/input-stream data1307)]
     (clojure.core/let
      [mac__546__auto__
       (javax.crypto.Mac/getInstance "HmacSHA512")
       k__547__auto__
       (javax.crypto.spec.SecretKeySpec. key1308 "HmacSHA512")
       c__548__auto__
       (clojure.core/int *buffer-size*)
       buf__549__auto__
       (clojure.core/byte-array c__548__auto__)
       s__550__auto__
       data1307]
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
  'G__1309)
 (do
  (clojure.core/defn
   sha512-hmac*
   "[HMAC] HmacSHA512 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha5121306
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha512-hmac-file*
   "[HMAC] HmacSHA512 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha5121306
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha512-hmac-bytes
   "[HMAC] HmacSHA512 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha5121306
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha512-hmac-file-bytes
   "[HMAC] HmacSHA512 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha5121306
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha512-hmac
   "[HMAC] HmacSHA512 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha5121306
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha512-hmac-file
   "[HMAC] HmacSHA512 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha5121306
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
(do
 (do
  (clojure.core/defprotocol
   G__1346
   (compute-sha5121342 [data1343 key1345])
   (compute-sha5121342-verify [data1343 sig1344 key1345]))
  (clojure.core/doseq
   [v__186__auto__
    [#'G__1346 #'compute-sha5121342 #'compute-sha5121342-verify]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1346
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha5121342
    [data1343 key1345]
    (clojure.core/let
     [signer__847__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA512withRSA")
       (.initSign key1345)
       (.update (clojure.core/bytes data1343)))]
     (.sign signer__847__auto__)))
   (compute-sha5121342-verify
    [data1343 sig1344 key1345]
    (clojure.core/let
     [signer__848__auto__
      (java.security.Signature/getInstance "SHA512withRSA")
      key__849__auto__
      (pandect.utils.convert/as-public-key key1345)]
     (.initVerify signer__848__auto__ key__849__auto__)
     (.update signer__848__auto__ (clojure.core/bytes data1343))
     (.verify signer__848__auto__ (clojure.core/bytes sig1344))))
   java.lang.String
   (compute-sha5121342
    [data1343 key1345]
    (clojure.core/let
     [data1343 (.getBytes data1343 "UTF-8")]
     (clojure.core/let
      [signer__847__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA512withRSA")
        (.initSign key1345)
        (.update (clojure.core/bytes data1343)))]
      (.sign signer__847__auto__))))
   (compute-sha5121342-verify
    [data1343 sig1344 key1345]
    (clojure.core/let
     [data1343 (.getBytes data1343 "UTF-8")]
     (clojure.core/let
      [signer__848__auto__
       (java.security.Signature/getInstance "SHA512withRSA")
       key__849__auto__
       (pandect.utils.convert/as-public-key key1345)]
      (.initVerify signer__848__auto__ key__849__auto__)
      (.update signer__848__auto__ (clojure.core/bytes data1343))
      (.verify signer__848__auto__ (clojure.core/bytes sig1344))))))
  (clojure.core/extend-protocol
   G__1346
   java.io.InputStream
   (compute-sha5121342
    [data1343 key1345]
    (clojure.core/let
     [signer__850__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA512withRSA")
       (.initSign key1345))
      c__851__auto__
      (clojure.core/int *buffer-size*)
      buf__852__auto__
      (clojure.core/byte-array c__851__auto__)
      s__853__auto__
      data1343]
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
   (compute-sha5121342-verify
    [data1343 sig1344 key1345]
    (clojure.core/let
     [signer__855__auto__
      (java.security.Signature/getInstance "SHA512withRSA")
      c__856__auto__
      (clojure.core/int *buffer-size*)
      buf__857__auto__
      (clojure.core/byte-array c__856__auto__)
      s__858__auto__
      data1343
      key__859__auto__
      (pandect.utils.convert/as-public-key key1345)]
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
     (.verify signer__855__auto__ (clojure.core/bytes sig1344))))
   java.io.File
   (compute-sha5121342
    [data1343 key1345]
    (clojure.core/with-open
     [data1343 (clojure.java.io/input-stream data1343)]
     (clojure.core/let
      [signer__850__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA512withRSA")
        (.initSign key1345))
       c__851__auto__
       (clojure.core/int *buffer-size*)
       buf__852__auto__
       (clojure.core/byte-array c__851__auto__)
       s__853__auto__
       data1343]
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
   (compute-sha5121342-verify
    [data1343 sig1344 key1345]
    (clojure.core/with-open
     [data1343 (clojure.java.io/input-stream data1343)]
     (clojure.core/let
      [signer__855__auto__
       (java.security.Signature/getInstance "SHA512withRSA")
       c__856__auto__
       (clojure.core/int *buffer-size*)
       buf__857__auto__
       (clojure.core/byte-array c__856__auto__)
       s__858__auto__
       data1343
       key__859__auto__
       (pandect.utils.convert/as-public-key key1345)]
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
      (.verify signer__855__auto__ (clojure.core/bytes sig1344))))))
  'G__1346)
 (do
  (clojure.core/defn
   sha512-rsa*
   "[Signature] SHA512withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha5121342 x private-key))
  (clojure.core/defn
   sha512-rsa-file*
   "[Signature] SHA512withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha5121342 x private-key)))
  (clojure.core/defn
   sha512-rsa-bytes
   "[Signature] SHA512withRSA (value -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha5121342 x private-key))
  (clojure.core/defn
   sha512-rsa-file-bytes
   "[Signature] SHA512withRSA (file path -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha5121342 x private-key)))
  (clojure.core/defn
   sha512-rsa
   "[Signature] SHA512withRSA (value -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (pandect.utils.convert/bytes->hex
    (compute-sha5121342 x private-key)))
  (clojure.core/defn
   sha512-rsa-file
   "[Signature] SHA512withRSA (file path -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha5121342 x private-key))))
  (clojure.core/defn
   sha512-rsa-verify
   "[Signature] SHA512withRSA\n\nVerify the given message signature using the given public key (anything implementing `pandect.utils.convert/PublicKeyConvertable`)\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or anything implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (compute-sha5121342-verify
    x
    (pandect.utils.convert/convert-signature-to-byte-array signature)
    (pandect.utils.convert/convert-to-public-key public-key)))
  (clojure.core/defn
   sha512-rsa-verify-file
   "[Signature] SHA512withRSA\n\nVerify the given message signature using the given public key (anything implementing `pandect.utils.convert/PublicKeyConvertable`)\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or anything implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha5121342-verify
     x
     (pandect.utils.convert/convert-signature-to-byte-array signature)
     (pandect.utils.convert/convert-to-public-key public-key))))))
