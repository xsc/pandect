(ns
 pandect.algo.sha512
 "SHA-512 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1115 (compute-sha5121113 [data1114]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1115 #'compute-sha5121113]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1115
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha5121113
    [data1114]
    (clojure.core/let
     [md__611__auto__
      (java.security.MessageDigest/getInstance "SHA-512")]
     (.digest md__611__auto__ data1114)))
   java.lang.String
   (compute-sha5121113
    [data1114]
    (clojure.core/let
     [data1114 (.getBytes data1114 "UTF-8")]
     (clojure.core/let
      [md__611__auto__
       (java.security.MessageDigest/getInstance "SHA-512")]
      (.digest md__611__auto__ data1114)))))
  (clojure.core/extend-protocol
   G__1115
   java.io.InputStream
   (compute-sha5121113
    [data1114]
    (clojure.core/let
     [md__612__auto__
      (java.security.MessageDigest/getInstance "SHA-512")
      c__613__auto__
      (clojure.core/int *buffer-size*)
      buf__614__auto__
      (clojure.core/byte-array c__613__auto__)
      s__615__auto__
      data1114]
     (clojure.core/loop
      []
      (clojure.core/let
       [r__616__auto__
        (.read s__615__auto__ buf__614__auto__ 0 c__613__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__616__auto__ -1)
        (.update md__612__auto__ buf__614__auto__ 0 r__616__auto__)
        (recur))))
     (.digest md__612__auto__)))
   java.io.File
   (compute-sha5121113
    [data1114]
    (clojure.core/with-open
     [data1114 (clojure.java.io/input-stream data1114)]
     (clojure.core/let
      [md__612__auto__
       (java.security.MessageDigest/getInstance "SHA-512")
       c__613__auto__
       (clojure.core/int *buffer-size*)
       buf__614__auto__
       (clojure.core/byte-array c__613__auto__)
       s__615__auto__
       data1114]
      (clojure.core/loop
       []
       (clojure.core/let
        [r__616__auto__
         (.read s__615__auto__ buf__614__auto__ 0 c__613__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__616__auto__ -1)
         (.update md__612__auto__ buf__614__auto__ 0 r__616__auto__)
         (recur))))
      (.digest md__612__auto__)))))
  'G__1115)
 (do
  (clojure.core/defn
   sha512*
   "[Hash] SHA-512 (raw value)"
   [x]
   (compute-sha5121113 x))
  (clojure.core/defn
   sha512-file*
   "[Hash] SHA-512 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha5121113 x)))
  (clojure.core/defn
   sha512-bytes
   "[Hash] SHA-512 (value -> byte array)"
   [x]
   (compute-sha5121113 x))
  (clojure.core/defn
   sha512-file-bytes
   "[Hash] SHA-512 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha5121113 x)))
  (clojure.core/defn
   sha512
   "[Hash] SHA-512 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha5121113 x)))
  (clojure.core/defn
   sha512-file
   "[Hash] SHA-512 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-sha5121113 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1119
   (compute-sha5121116 [data1117 key1118]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1119 #'compute-sha5121116]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1119
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha5121116
    [data1117 key1118]
    (clojure.core/let
     [mac__492__auto__
      (javax.crypto.Mac/getInstance "HmacSHA512")
      msg__493__auto__
      (clojure.core/bytes data1117)
      k__494__auto__
      (javax.crypto.spec.SecretKeySpec. key1118 "HmacSHA512")]
     (clojure.core/->
      (clojure.core/doto
       mac__492__auto__
       (.init k__494__auto__)
       (.update msg__493__auto__))
      (.doFinal))))
   java.lang.String
   (compute-sha5121116
    [data1117 key1118]
    (clojure.core/let
     [data1117 (.getBytes data1117 "UTF-8")]
     (clojure.core/let
      [mac__492__auto__
       (javax.crypto.Mac/getInstance "HmacSHA512")
       msg__493__auto__
       (clojure.core/bytes data1117)
       k__494__auto__
       (javax.crypto.spec.SecretKeySpec. key1118 "HmacSHA512")]
      (clojure.core/->
       (clojure.core/doto
        mac__492__auto__
        (.init k__494__auto__)
        (.update msg__493__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1119
   java.io.InputStream
   (compute-sha5121116
    [data1117 key1118]
    (clojure.core/let
     [mac__495__auto__
      (javax.crypto.Mac/getInstance "HmacSHA512")
      k__496__auto__
      (javax.crypto.spec.SecretKeySpec. key1118 "HmacSHA512")
      c__497__auto__
      (clojure.core/int *buffer-size*)
      buf__498__auto__
      (clojure.core/byte-array c__497__auto__)
      s__499__auto__
      data1117]
     (.init mac__495__auto__ k__496__auto__)
     (clojure.core/loop
      []
      (clojure.core/let
       [r__500__auto__
        (.read s__499__auto__ buf__498__auto__ 0 c__497__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__500__auto__ -1)
        (.update mac__495__auto__ buf__498__auto__ 0 r__500__auto__)
        (recur))))
     (.doFinal mac__495__auto__)))
   java.io.File
   (compute-sha5121116
    [data1117 key1118]
    (clojure.core/with-open
     [data1117 (clojure.java.io/input-stream data1117)]
     (clojure.core/let
      [mac__495__auto__
       (javax.crypto.Mac/getInstance "HmacSHA512")
       k__496__auto__
       (javax.crypto.spec.SecretKeySpec. key1118 "HmacSHA512")
       c__497__auto__
       (clojure.core/int *buffer-size*)
       buf__498__auto__
       (clojure.core/byte-array c__497__auto__)
       s__499__auto__
       data1117]
      (.init mac__495__auto__ k__496__auto__)
      (clojure.core/loop
       []
       (clojure.core/let
        [r__500__auto__
         (.read s__499__auto__ buf__498__auto__ 0 c__497__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__500__auto__ -1)
         (.update mac__495__auto__ buf__498__auto__ 0 r__500__auto__)
         (recur))))
      (.doFinal mac__495__auto__)))))
  'G__1119)
 (do
  (clojure.core/defn
   sha512-hmac*
   "[HMAC] HmacSHA512 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha5121116
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha512-hmac-file*
   "[HMAC] HmacSHA512 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha5121116
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha512-hmac-bytes
   "[HMAC] HmacSHA512 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha5121116
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha512-hmac-file-bytes
   "[HMAC] HmacSHA512 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha5121116
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha512-hmac
   "[HMAC] HmacSHA512 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha5121116
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha512-hmac-file
   "[HMAC] HmacSHA512 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha5121116
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
(do
 (do
  (clojure.core/defprotocol
   G__1124
   (compute-sha5121120 [data1121 key1123])
   (compute-sha5121120-verify [data1121 sig1122 key1123]))
  (clojure.core/doseq
   [v__166__auto__
    [#'G__1124 #'compute-sha5121120 #'compute-sha5121120-verify]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1124
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha5121120
    [data1121 key1123]
    (clojure.core/let
     [signer__786__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA512withRSA")
       (.initSign key1123)
       (.update (clojure.core/bytes data1121)))]
     (.sign signer__786__auto__)))
   (compute-sha5121120-verify
    [data1121 sig1122 key1123]
    (clojure.core/let
     [signer__787__auto__
      (java.security.Signature/getInstance "SHA512withRSA")
      key__788__auto__
      key1123]
     (if
      (clojure.core/instance? java.security.PublicKey key__788__auto__)
      (.initVerify
       signer__787__auto__
       (pandect.utils.convert/as-public-key key__788__auto__))
      (.initVerify
       signer__787__auto__
       (pandect.utils.convert/as-certificate key__788__auto__)))
     (.update signer__787__auto__ (clojure.core/bytes data1121))
     (.verify signer__787__auto__ (clojure.core/bytes sig1122))))
   java.lang.String
   (compute-sha5121120
    [data1121 key1123]
    (clojure.core/let
     [data1121 (.getBytes data1121 "UTF-8")]
     (clojure.core/let
      [signer__786__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA512withRSA")
        (.initSign key1123)
        (.update (clojure.core/bytes data1121)))]
      (.sign signer__786__auto__))))
   (compute-sha5121120-verify
    [data1121 sig1122 key1123]
    (clojure.core/let
     [data1121 (.getBytes data1121 "UTF-8")]
     (clojure.core/let
      [signer__787__auto__
       (java.security.Signature/getInstance "SHA512withRSA")
       key__788__auto__
       key1123]
      (if
       (clojure.core/instance?
        java.security.PublicKey
        key__788__auto__)
       (.initVerify
        signer__787__auto__
        (pandect.utils.convert/as-public-key key__788__auto__))
       (.initVerify
        signer__787__auto__
        (pandect.utils.convert/as-certificate key__788__auto__)))
      (.update signer__787__auto__ (clojure.core/bytes data1121))
      (.verify signer__787__auto__ (clojure.core/bytes sig1122))))))
  (clojure.core/extend-protocol
   G__1124
   java.io.InputStream
   (compute-sha5121120
    [data1121 key1123]
    (clojure.core/let
     [signer__789__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA512withRSA")
       (.initSign key1123))
      c__790__auto__
      (clojure.core/int *buffer-size*)
      buf__791__auto__
      (clojure.core/byte-array c__790__auto__)
      s__792__auto__
      data1121]
     (clojure.core/loop
      []
      (clojure.core/let
       [r__793__auto__
        (.read s__792__auto__ buf__791__auto__ 0 c__790__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__793__auto__ -1)
        (.update signer__789__auto__ buf__791__auto__ 0 r__793__auto__)
        (recur))))
     (.sign signer__789__auto__)))
   (compute-sha5121120-verify
    [data1121 sig1122 key1123]
    (clojure.core/let
     [signer__794__auto__
      (java.security.Signature/getInstance "SHA512withRSA")
      c__795__auto__
      (clojure.core/int *buffer-size*)
      buf__796__auto__
      (clojure.core/byte-array c__795__auto__)
      s__797__auto__
      data1121
      key__798__auto__
      key1123]
     (if
      (clojure.core/instance? java.security.PublicKey key__798__auto__)
      (.initVerify
       signer__794__auto__
       (pandect.utils.convert/as-public-key key__798__auto__))
      (.initVerify
       signer__794__auto__
       (pandect.utils.convert/as-certificate key__798__auto__)))
     (clojure.core/loop
      []
      (clojure.core/let
       [r__799__auto__
        (.read s__797__auto__ buf__796__auto__ 0 c__795__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__799__auto__ -1)
        (.update signer__794__auto__ buf__796__auto__ 0 r__799__auto__)
        (recur))))
     (.verify signer__794__auto__ (clojure.core/bytes sig1122))))
   java.io.File
   (compute-sha5121120
    [data1121 key1123]
    (clojure.core/with-open
     [data1121 (clojure.java.io/input-stream data1121)]
     (clojure.core/let
      [signer__789__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA512withRSA")
        (.initSign key1123))
       c__790__auto__
       (clojure.core/int *buffer-size*)
       buf__791__auto__
       (clojure.core/byte-array c__790__auto__)
       s__792__auto__
       data1121]
      (clojure.core/loop
       []
       (clojure.core/let
        [r__793__auto__
         (.read s__792__auto__ buf__791__auto__ 0 c__790__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__793__auto__ -1)
         (.update
          signer__789__auto__
          buf__791__auto__
          0
          r__793__auto__)
         (recur))))
      (.sign signer__789__auto__))))
   (compute-sha5121120-verify
    [data1121 sig1122 key1123]
    (clojure.core/with-open
     [data1121 (clojure.java.io/input-stream data1121)]
     (clojure.core/let
      [signer__794__auto__
       (java.security.Signature/getInstance "SHA512withRSA")
       c__795__auto__
       (clojure.core/int *buffer-size*)
       buf__796__auto__
       (clojure.core/byte-array c__795__auto__)
       s__797__auto__
       data1121
       key__798__auto__
       key1123]
      (if
       (clojure.core/instance?
        java.security.PublicKey
        key__798__auto__)
       (.initVerify
        signer__794__auto__
        (pandect.utils.convert/as-public-key key__798__auto__))
       (.initVerify
        signer__794__auto__
        (pandect.utils.convert/as-certificate key__798__auto__)))
      (clojure.core/loop
       []
       (clojure.core/let
        [r__799__auto__
         (.read s__797__auto__ buf__796__auto__ 0 c__795__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__799__auto__ -1)
         (.update
          signer__794__auto__
          buf__796__auto__
          0
          r__799__auto__)
         (recur))))
      (.verify signer__794__auto__ (clojure.core/bytes sig1122))))))
  'G__1124)
 (do
  (clojure.core/defn
   sha512-rsa*
   "[Signature] SHA512withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha5121120 x private-key))
  (clojure.core/defn
   sha512-rsa-file*
   "[Signature] SHA512withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha5121120 x private-key)))
  (clojure.core/defn
   sha512-rsa-bytes
   "[Signature] SHA512withRSA (value -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha5121120 x private-key))
  (clojure.core/defn
   sha512-rsa-file-bytes
   "[Signature] SHA512withRSA (file path -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha5121120 x private-key)))
  (clojure.core/defn
   sha512-rsa
   "[Signature] SHA512withRSA (value -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (pandect.utils.convert/bytes->hex
    (compute-sha5121120 x private-key)))
  (clojure.core/defn
   sha512-rsa-file
   "[Signature] SHA512withRSA (file path -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha5121120 x private-key))))
  (clojure.core/defn
   sha512-rsa-verify
   "[Signature] SHA512withRSA\n\nVerify the given message signature using the given java.security.PublicKey\nor java.security.cert.Certificate.\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or any value implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (compute-sha5121120-verify
    x
    (pandect.utils.convert/convert-signature-to-byte-array signature)
    public-key))
  (clojure.core/defn
   sha512-rsa-verify-file
   "[Signature] SHA512withRSA\n\nVerify the given message signature using the given java.security.PublicKey\nor java.security.cert.Certificate.\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or any value implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha5121120-verify
     x
     (pandect.utils.convert/convert-signature-to-byte-array signature)
     public-key)))))
