(ns
 pandect.algo.sha384
 "SHA-384 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1096 (compute-sha3841094 [data1095]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1096 #'compute-sha3841094]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1096
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha3841094
    [data1095]
    (clojure.core/let
     [md__611__auto__
      (java.security.MessageDigest/getInstance "SHA-384")]
     (.digest md__611__auto__ data1095)))
   java.lang.String
   (compute-sha3841094
    [data1095]
    (clojure.core/let
     [data1095 (.getBytes data1095 "UTF-8")]
     (clojure.core/let
      [md__611__auto__
       (java.security.MessageDigest/getInstance "SHA-384")]
      (.digest md__611__auto__ data1095)))))
  (clojure.core/extend-protocol
   G__1096
   java.io.InputStream
   (compute-sha3841094
    [data1095]
    (clojure.core/let
     [md__612__auto__
      (java.security.MessageDigest/getInstance "SHA-384")
      c__613__auto__
      (clojure.core/int *buffer-size*)
      buf__614__auto__
      (clojure.core/byte-array c__613__auto__)
      s__615__auto__
      data1095]
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
   (compute-sha3841094
    [data1095]
    (clojure.core/with-open
     [data1095 (clojure.java.io/input-stream data1095)]
     (clojure.core/let
      [md__612__auto__
       (java.security.MessageDigest/getInstance "SHA-384")
       c__613__auto__
       (clojure.core/int *buffer-size*)
       buf__614__auto__
       (clojure.core/byte-array c__613__auto__)
       s__615__auto__
       data1095]
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
  'G__1096)
 (do
  (clojure.core/defn
   sha384*
   "[Hash] SHA-384 (raw value)"
   [x]
   (compute-sha3841094 x))
  (clojure.core/defn
   sha384-file*
   "[Hash] SHA-384 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3841094 x)))
  (clojure.core/defn
   sha384-bytes
   "[Hash] SHA-384 (value -> byte array)"
   [x]
   (compute-sha3841094 x))
  (clojure.core/defn
   sha384-file-bytes
   "[Hash] SHA-384 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3841094 x)))
  (clojure.core/defn
   sha384
   "[Hash] SHA-384 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha3841094 x)))
  (clojure.core/defn
   sha384-file
   "[Hash] SHA-384 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-sha3841094 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1100
   (compute-sha3841097 [data1098 key1099]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1100 #'compute-sha3841097]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1100
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha3841097
    [data1098 key1099]
    (clojure.core/let
     [mac__492__auto__
      (javax.crypto.Mac/getInstance "HmacSHA384")
      msg__493__auto__
      (clojure.core/bytes data1098)
      k__494__auto__
      (javax.crypto.spec.SecretKeySpec. key1099 "HmacSHA384")]
     (clojure.core/->
      (clojure.core/doto
       mac__492__auto__
       (.init k__494__auto__)
       (.update msg__493__auto__))
      (.doFinal))))
   java.lang.String
   (compute-sha3841097
    [data1098 key1099]
    (clojure.core/let
     [data1098 (.getBytes data1098 "UTF-8")]
     (clojure.core/let
      [mac__492__auto__
       (javax.crypto.Mac/getInstance "HmacSHA384")
       msg__493__auto__
       (clojure.core/bytes data1098)
       k__494__auto__
       (javax.crypto.spec.SecretKeySpec. key1099 "HmacSHA384")]
      (clojure.core/->
       (clojure.core/doto
        mac__492__auto__
        (.init k__494__auto__)
        (.update msg__493__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1100
   java.io.InputStream
   (compute-sha3841097
    [data1098 key1099]
    (clojure.core/let
     [mac__495__auto__
      (javax.crypto.Mac/getInstance "HmacSHA384")
      k__496__auto__
      (javax.crypto.spec.SecretKeySpec. key1099 "HmacSHA384")
      c__497__auto__
      (clojure.core/int *buffer-size*)
      buf__498__auto__
      (clojure.core/byte-array c__497__auto__)
      s__499__auto__
      data1098]
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
   (compute-sha3841097
    [data1098 key1099]
    (clojure.core/with-open
     [data1098 (clojure.java.io/input-stream data1098)]
     (clojure.core/let
      [mac__495__auto__
       (javax.crypto.Mac/getInstance "HmacSHA384")
       k__496__auto__
       (javax.crypto.spec.SecretKeySpec. key1099 "HmacSHA384")
       c__497__auto__
       (clojure.core/int *buffer-size*)
       buf__498__auto__
       (clojure.core/byte-array c__497__auto__)
       s__499__auto__
       data1098]
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
  'G__1100)
 (do
  (clojure.core/defn
   sha384-hmac*
   "[HMAC] HmacSHA384 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha3841097
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha384-hmac-file*
   "[HMAC] HmacSHA384 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3841097
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha384-hmac-bytes
   "[HMAC] HmacSHA384 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha3841097
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha384-hmac-file-bytes
   "[HMAC] HmacSHA384 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3841097
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha384-hmac
   "[HMAC] HmacSHA384 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha3841097
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha384-hmac-file
   "[HMAC] HmacSHA384 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha3841097
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
(do
 (do
  (clojure.core/defprotocol
   G__1105
   (compute-sha3841101 [data1102 key1104])
   (compute-sha3841101-verify [data1102 sig1103 key1104]))
  (clojure.core/doseq
   [v__166__auto__
    [#'G__1105 #'compute-sha3841101 #'compute-sha3841101-verify]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1105
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha3841101
    [data1102 key1104]
    (clojure.core/let
     [signer__786__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA384withRSA")
       (.initSign key1104)
       (.update (clojure.core/bytes data1102)))]
     (.sign signer__786__auto__)))
   (compute-sha3841101-verify
    [data1102 sig1103 key1104]
    (clojure.core/let
     [signer__787__auto__
      (java.security.Signature/getInstance "SHA384withRSA")
      key__788__auto__
      key1104]
     (if
      (clojure.core/instance? java.security.PublicKey key__788__auto__)
      (.initVerify
       signer__787__auto__
       (pandect.utils.convert/as-public-key key__788__auto__))
      (.initVerify
       signer__787__auto__
       (pandect.utils.convert/as-certificate key__788__auto__)))
     (.update signer__787__auto__ (clojure.core/bytes data1102))
     (.verify signer__787__auto__ (clojure.core/bytes sig1103))))
   java.lang.String
   (compute-sha3841101
    [data1102 key1104]
    (clojure.core/let
     [data1102 (.getBytes data1102 "UTF-8")]
     (clojure.core/let
      [signer__786__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA384withRSA")
        (.initSign key1104)
        (.update (clojure.core/bytes data1102)))]
      (.sign signer__786__auto__))))
   (compute-sha3841101-verify
    [data1102 sig1103 key1104]
    (clojure.core/let
     [data1102 (.getBytes data1102 "UTF-8")]
     (clojure.core/let
      [signer__787__auto__
       (java.security.Signature/getInstance "SHA384withRSA")
       key__788__auto__
       key1104]
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
      (.update signer__787__auto__ (clojure.core/bytes data1102))
      (.verify signer__787__auto__ (clojure.core/bytes sig1103))))))
  (clojure.core/extend-protocol
   G__1105
   java.io.InputStream
   (compute-sha3841101
    [data1102 key1104]
    (clojure.core/let
     [signer__789__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA384withRSA")
       (.initSign key1104))
      c__790__auto__
      (clojure.core/int *buffer-size*)
      buf__791__auto__
      (clojure.core/byte-array c__790__auto__)
      s__792__auto__
      data1102]
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
   (compute-sha3841101-verify
    [data1102 sig1103 key1104]
    (clojure.core/let
     [signer__794__auto__
      (java.security.Signature/getInstance "SHA384withRSA")
      c__795__auto__
      (clojure.core/int *buffer-size*)
      buf__796__auto__
      (clojure.core/byte-array c__795__auto__)
      s__797__auto__
      data1102
      key__798__auto__
      key1104]
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
     (.verify signer__794__auto__ (clojure.core/bytes sig1103))))
   java.io.File
   (compute-sha3841101
    [data1102 key1104]
    (clojure.core/with-open
     [data1102 (clojure.java.io/input-stream data1102)]
     (clojure.core/let
      [signer__789__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA384withRSA")
        (.initSign key1104))
       c__790__auto__
       (clojure.core/int *buffer-size*)
       buf__791__auto__
       (clojure.core/byte-array c__790__auto__)
       s__792__auto__
       data1102]
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
   (compute-sha3841101-verify
    [data1102 sig1103 key1104]
    (clojure.core/with-open
     [data1102 (clojure.java.io/input-stream data1102)]
     (clojure.core/let
      [signer__794__auto__
       (java.security.Signature/getInstance "SHA384withRSA")
       c__795__auto__
       (clojure.core/int *buffer-size*)
       buf__796__auto__
       (clojure.core/byte-array c__795__auto__)
       s__797__auto__
       data1102
       key__798__auto__
       key1104]
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
      (.verify signer__794__auto__ (clojure.core/bytes sig1103))))))
  'G__1105)
 (do
  (clojure.core/defn
   sha384-rsa*
   "[Signature] SHA384withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha3841101 x private-key))
  (clojure.core/defn
   sha384-rsa-file*
   "[Signature] SHA384withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3841101 x private-key)))
  (clojure.core/defn
   sha384-rsa-bytes
   "[Signature] SHA384withRSA (value -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha3841101 x private-key))
  (clojure.core/defn
   sha384-rsa-file-bytes
   "[Signature] SHA384withRSA (file path -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3841101 x private-key)))
  (clojure.core/defn
   sha384-rsa
   "[Signature] SHA384withRSA (value -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (pandect.utils.convert/bytes->hex
    (compute-sha3841101 x private-key)))
  (clojure.core/defn
   sha384-rsa-file
   "[Signature] SHA384withRSA (file path -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha3841101 x private-key))))
  (clojure.core/defn
   sha384-rsa-verify
   "[Signature] SHA384withRSA\n\nVerify the given message signature using the given java.security.PublicKey\nor java.security.cert.Certificate.\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or any value implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (compute-sha3841101-verify
    x
    (pandect.utils.convert/convert-signature-to-byte-array signature)
    public-key))
  (clojure.core/defn
   sha384-rsa-verify-file
   "[Signature] SHA384withRSA\n\nVerify the given message signature using the given java.security.PublicKey\nor java.security.cert.Certificate.\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or any value implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3841101-verify
     x
     (pandect.utils.convert/convert-signature-to-byte-array signature)
     public-key)))))
