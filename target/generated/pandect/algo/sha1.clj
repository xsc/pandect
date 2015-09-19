(ns
 pandect.algo.sha1
 "SHA-1 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1072 (compute-sha11070 [data1071]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1072 #'compute-sha11070]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1072
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha11070
    [data1071]
    (clojure.core/let
     [md__611__auto__
      (java.security.MessageDigest/getInstance "SHA-1")]
     (.digest md__611__auto__ data1071)))
   java.lang.String
   (compute-sha11070
    [data1071]
    (clojure.core/let
     [data1071 (.getBytes data1071 "UTF-8")]
     (clojure.core/let
      [md__611__auto__
       (java.security.MessageDigest/getInstance "SHA-1")]
      (.digest md__611__auto__ data1071)))))
  (clojure.core/extend-protocol
   G__1072
   java.io.InputStream
   (compute-sha11070
    [data1071]
    (clojure.core/let
     [md__612__auto__
      (java.security.MessageDigest/getInstance "SHA-1")
      c__613__auto__
      (clojure.core/int *buffer-size*)
      buf__614__auto__
      (clojure.core/byte-array c__613__auto__)
      s__615__auto__
      data1071]
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
   (compute-sha11070
    [data1071]
    (clojure.core/with-open
     [data1071 (clojure.java.io/input-stream data1071)]
     (clojure.core/let
      [md__612__auto__
       (java.security.MessageDigest/getInstance "SHA-1")
       c__613__auto__
       (clojure.core/int *buffer-size*)
       buf__614__auto__
       (clojure.core/byte-array c__613__auto__)
       s__615__auto__
       data1071]
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
  'G__1072)
 (do
  (clojure.core/defn
   sha1*
   "[Hash] SHA-1 (raw value)"
   [x]
   (compute-sha11070 x))
  (clojure.core/defn
   sha1-file*
   "[Hash] SHA-1 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11070 x)))
  (clojure.core/defn
   sha1-bytes
   "[Hash] SHA-1 (value -> byte array)"
   [x]
   (compute-sha11070 x))
  (clojure.core/defn
   sha1-file-bytes
   "[Hash] SHA-1 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11070 x)))
  (clojure.core/defn
   sha1
   "[Hash] SHA-1 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha11070 x)))
  (clojure.core/defn
   sha1-file
   "[Hash] SHA-1 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-sha11070 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1076
   (compute-sha11073 [data1074 key1075]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1076 #'compute-sha11073]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1076
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha11073
    [data1074 key1075]
    (clojure.core/let
     [mac__492__auto__
      (javax.crypto.Mac/getInstance "HmacSHA1")
      msg__493__auto__
      (clojure.core/bytes data1074)
      k__494__auto__
      (javax.crypto.spec.SecretKeySpec. key1075 "HmacSHA1")]
     (clojure.core/->
      (clojure.core/doto
       mac__492__auto__
       (.init k__494__auto__)
       (.update msg__493__auto__))
      (.doFinal))))
   java.lang.String
   (compute-sha11073
    [data1074 key1075]
    (clojure.core/let
     [data1074 (.getBytes data1074 "UTF-8")]
     (clojure.core/let
      [mac__492__auto__
       (javax.crypto.Mac/getInstance "HmacSHA1")
       msg__493__auto__
       (clojure.core/bytes data1074)
       k__494__auto__
       (javax.crypto.spec.SecretKeySpec. key1075 "HmacSHA1")]
      (clojure.core/->
       (clojure.core/doto
        mac__492__auto__
        (.init k__494__auto__)
        (.update msg__493__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1076
   java.io.InputStream
   (compute-sha11073
    [data1074 key1075]
    (clojure.core/let
     [mac__495__auto__
      (javax.crypto.Mac/getInstance "HmacSHA1")
      k__496__auto__
      (javax.crypto.spec.SecretKeySpec. key1075 "HmacSHA1")
      c__497__auto__
      (clojure.core/int *buffer-size*)
      buf__498__auto__
      (clojure.core/byte-array c__497__auto__)
      s__499__auto__
      data1074]
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
   (compute-sha11073
    [data1074 key1075]
    (clojure.core/with-open
     [data1074 (clojure.java.io/input-stream data1074)]
     (clojure.core/let
      [mac__495__auto__
       (javax.crypto.Mac/getInstance "HmacSHA1")
       k__496__auto__
       (javax.crypto.spec.SecretKeySpec. key1075 "HmacSHA1")
       c__497__auto__
       (clojure.core/int *buffer-size*)
       buf__498__auto__
       (clojure.core/byte-array c__497__auto__)
       s__499__auto__
       data1074]
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
  'G__1076)
 (do
  (clojure.core/defn
   sha1-hmac*
   "[HMAC] HmacSHA1 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha11073
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha1-hmac-file*
   "[HMAC] HmacSHA1 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11073
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha1-hmac-bytes
   "[HMAC] HmacSHA1 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha11073
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha1-hmac-file-bytes
   "[HMAC] HmacSHA1 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11073
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha1-hmac
   "[HMAC] HmacSHA1 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha11073
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha1-hmac-file
   "[HMAC] HmacSHA1 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha11073
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
(do
 (do
  (clojure.core/defprotocol
   G__1081
   (compute-sha11077 [data1078 key1080])
   (compute-sha11077-verify [data1078 sig1079 key1080]))
  (clojure.core/doseq
   [v__166__auto__
    [#'G__1081 #'compute-sha11077 #'compute-sha11077-verify]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1081
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha11077
    [data1078 key1080]
    (clojure.core/let
     [signer__786__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA1withRSA")
       (.initSign key1080)
       (.update (clojure.core/bytes data1078)))]
     (.sign signer__786__auto__)))
   (compute-sha11077-verify
    [data1078 sig1079 key1080]
    (clojure.core/let
     [signer__787__auto__
      (java.security.Signature/getInstance "SHA1withRSA")
      key__788__auto__
      key1080]
     (if
      (clojure.core/instance? java.security.PublicKey key__788__auto__)
      (.initVerify
       signer__787__auto__
       (pandect.utils.convert/as-public-key key__788__auto__))
      (.initVerify
       signer__787__auto__
       (pandect.utils.convert/as-certificate key__788__auto__)))
     (.update signer__787__auto__ (clojure.core/bytes data1078))
     (.verify signer__787__auto__ (clojure.core/bytes sig1079))))
   java.lang.String
   (compute-sha11077
    [data1078 key1080]
    (clojure.core/let
     [data1078 (.getBytes data1078 "UTF-8")]
     (clojure.core/let
      [signer__786__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA1withRSA")
        (.initSign key1080)
        (.update (clojure.core/bytes data1078)))]
      (.sign signer__786__auto__))))
   (compute-sha11077-verify
    [data1078 sig1079 key1080]
    (clojure.core/let
     [data1078 (.getBytes data1078 "UTF-8")]
     (clojure.core/let
      [signer__787__auto__
       (java.security.Signature/getInstance "SHA1withRSA")
       key__788__auto__
       key1080]
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
      (.update signer__787__auto__ (clojure.core/bytes data1078))
      (.verify signer__787__auto__ (clojure.core/bytes sig1079))))))
  (clojure.core/extend-protocol
   G__1081
   java.io.InputStream
   (compute-sha11077
    [data1078 key1080]
    (clojure.core/let
     [signer__789__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA1withRSA")
       (.initSign key1080))
      c__790__auto__
      (clojure.core/int *buffer-size*)
      buf__791__auto__
      (clojure.core/byte-array c__790__auto__)
      s__792__auto__
      data1078]
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
   (compute-sha11077-verify
    [data1078 sig1079 key1080]
    (clojure.core/let
     [signer__794__auto__
      (java.security.Signature/getInstance "SHA1withRSA")
      c__795__auto__
      (clojure.core/int *buffer-size*)
      buf__796__auto__
      (clojure.core/byte-array c__795__auto__)
      s__797__auto__
      data1078
      key__798__auto__
      key1080]
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
     (.verify signer__794__auto__ (clojure.core/bytes sig1079))))
   java.io.File
   (compute-sha11077
    [data1078 key1080]
    (clojure.core/with-open
     [data1078 (clojure.java.io/input-stream data1078)]
     (clojure.core/let
      [signer__789__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA1withRSA")
        (.initSign key1080))
       c__790__auto__
       (clojure.core/int *buffer-size*)
       buf__791__auto__
       (clojure.core/byte-array c__790__auto__)
       s__792__auto__
       data1078]
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
   (compute-sha11077-verify
    [data1078 sig1079 key1080]
    (clojure.core/with-open
     [data1078 (clojure.java.io/input-stream data1078)]
     (clojure.core/let
      [signer__794__auto__
       (java.security.Signature/getInstance "SHA1withRSA")
       c__795__auto__
       (clojure.core/int *buffer-size*)
       buf__796__auto__
       (clojure.core/byte-array c__795__auto__)
       s__797__auto__
       data1078
       key__798__auto__
       key1080]
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
      (.verify signer__794__auto__ (clojure.core/bytes sig1079))))))
  'G__1081)
 (do
  (clojure.core/defn
   sha1-rsa*
   "[Signature] SHA1withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha11077 x private-key))
  (clojure.core/defn
   sha1-rsa-file*
   "[Signature] SHA1withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11077 x private-key)))
  (clojure.core/defn
   sha1-rsa-bytes
   "[Signature] SHA1withRSA (value -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha11077 x private-key))
  (clojure.core/defn
   sha1-rsa-file-bytes
   "[Signature] SHA1withRSA (file path -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11077 x private-key)))
  (clojure.core/defn
   sha1-rsa
   "[Signature] SHA1withRSA (value -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (pandect.utils.convert/bytes->hex (compute-sha11077 x private-key)))
  (clojure.core/defn
   sha1-rsa-file
   "[Signature] SHA1withRSA (file path -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha11077 x private-key))))
  (clojure.core/defn
   sha1-rsa-verify
   "[Signature] SHA1withRSA\n\nVerify the given message signature using the given java.security.PublicKey\nor java.security.cert.Certificate.\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or any value implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (compute-sha11077-verify
    x
    (pandect.utils.convert/convert-signature-to-byte-array signature)
    public-key))
  (clojure.core/defn
   sha1-rsa-verify-file
   "[Signature] SHA1withRSA\n\nVerify the given message signature using the given java.security.PublicKey\nor java.security.cert.Certificate.\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or any value implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11077-verify
     x
     (pandect.utils.convert/convert-signature-to-byte-array signature)
     public-key)))))
(do
 (do
  (clojure.core/defprotocol
   G__1086
   (compute-sha11082 [data1083 key1085])
   (compute-sha11082-verify [data1083 sig1084 key1085]))
  (clojure.core/doseq
   [v__166__auto__
    [#'G__1086 #'compute-sha11082 #'compute-sha11082-verify]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1086
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha11082
    [data1083 key1085]
    (clojure.core/let
     [signer__786__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA1withDSA")
       (.initSign key1085)
       (.update (clojure.core/bytes data1083)))]
     (.sign signer__786__auto__)))
   (compute-sha11082-verify
    [data1083 sig1084 key1085]
    (clojure.core/let
     [signer__787__auto__
      (java.security.Signature/getInstance "SHA1withDSA")
      key__788__auto__
      key1085]
     (if
      (clojure.core/instance? java.security.PublicKey key__788__auto__)
      (.initVerify
       signer__787__auto__
       (pandect.utils.convert/as-public-key key__788__auto__))
      (.initVerify
       signer__787__auto__
       (pandect.utils.convert/as-certificate key__788__auto__)))
     (.update signer__787__auto__ (clojure.core/bytes data1083))
     (.verify signer__787__auto__ (clojure.core/bytes sig1084))))
   java.lang.String
   (compute-sha11082
    [data1083 key1085]
    (clojure.core/let
     [data1083 (.getBytes data1083 "UTF-8")]
     (clojure.core/let
      [signer__786__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA1withDSA")
        (.initSign key1085)
        (.update (clojure.core/bytes data1083)))]
      (.sign signer__786__auto__))))
   (compute-sha11082-verify
    [data1083 sig1084 key1085]
    (clojure.core/let
     [data1083 (.getBytes data1083 "UTF-8")]
     (clojure.core/let
      [signer__787__auto__
       (java.security.Signature/getInstance "SHA1withDSA")
       key__788__auto__
       key1085]
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
      (.update signer__787__auto__ (clojure.core/bytes data1083))
      (.verify signer__787__auto__ (clojure.core/bytes sig1084))))))
  (clojure.core/extend-protocol
   G__1086
   java.io.InputStream
   (compute-sha11082
    [data1083 key1085]
    (clojure.core/let
     [signer__789__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA1withDSA")
       (.initSign key1085))
      c__790__auto__
      (clojure.core/int *buffer-size*)
      buf__791__auto__
      (clojure.core/byte-array c__790__auto__)
      s__792__auto__
      data1083]
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
   (compute-sha11082-verify
    [data1083 sig1084 key1085]
    (clojure.core/let
     [signer__794__auto__
      (java.security.Signature/getInstance "SHA1withDSA")
      c__795__auto__
      (clojure.core/int *buffer-size*)
      buf__796__auto__
      (clojure.core/byte-array c__795__auto__)
      s__797__auto__
      data1083
      key__798__auto__
      key1085]
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
     (.verify signer__794__auto__ (clojure.core/bytes sig1084))))
   java.io.File
   (compute-sha11082
    [data1083 key1085]
    (clojure.core/with-open
     [data1083 (clojure.java.io/input-stream data1083)]
     (clojure.core/let
      [signer__789__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA1withDSA")
        (.initSign key1085))
       c__790__auto__
       (clojure.core/int *buffer-size*)
       buf__791__auto__
       (clojure.core/byte-array c__790__auto__)
       s__792__auto__
       data1083]
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
   (compute-sha11082-verify
    [data1083 sig1084 key1085]
    (clojure.core/with-open
     [data1083 (clojure.java.io/input-stream data1083)]
     (clojure.core/let
      [signer__794__auto__
       (java.security.Signature/getInstance "SHA1withDSA")
       c__795__auto__
       (clojure.core/int *buffer-size*)
       buf__796__auto__
       (clojure.core/byte-array c__795__auto__)
       s__797__auto__
       data1083
       key__798__auto__
       key1085]
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
      (.verify signer__794__auto__ (clojure.core/bytes sig1084))))))
  'G__1086)
 (do
  (clojure.core/defn
   sha1-dsa*
   "[Signature] SHA1withDSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha11082 x private-key))
  (clojure.core/defn
   sha1-dsa-file*
   "[Signature] SHA1withDSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11082 x private-key)))
  (clojure.core/defn
   sha1-dsa-bytes
   "[Signature] SHA1withDSA (value -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha11082 x private-key))
  (clojure.core/defn
   sha1-dsa-file-bytes
   "[Signature] SHA1withDSA (file path -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11082 x private-key)))
  (clojure.core/defn
   sha1-dsa
   "[Signature] SHA1withDSA (value -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (pandect.utils.convert/bytes->hex (compute-sha11082 x private-key)))
  (clojure.core/defn
   sha1-dsa-file
   "[Signature] SHA1withDSA (file path -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha11082 x private-key))))
  (clojure.core/defn
   sha1-dsa-verify
   "[Signature] SHA1withDSA\n\nVerify the given message signature using the given java.security.PublicKey\nor java.security.cert.Certificate.\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or any value implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (compute-sha11082-verify
    x
    (pandect.utils.convert/convert-signature-to-byte-array signature)
    public-key))
  (clojure.core/defn
   sha1-dsa-verify-file
   "[Signature] SHA1withDSA\n\nVerify the given message signature using the given java.security.PublicKey\nor java.security.cert.Certificate.\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or any value implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha11082-verify
     x
     (pandect.utils.convert/convert-signature-to-byte-array signature)
     public-key)))))
