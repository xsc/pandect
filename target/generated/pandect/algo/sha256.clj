(ns
 pandect.algo.sha256
 "SHA-256 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1137 (compute-sha2561135 [data1136]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1137 #'compute-sha2561135]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1137
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha2561135
    [data1136]
    (clojure.core/let
     [md__611__auto__
      (java.security.MessageDigest/getInstance "SHA-256")]
     (.digest md__611__auto__ data1136)))
   java.lang.String
   (compute-sha2561135
    [data1136]
    (clojure.core/let
     [data1136 (.getBytes data1136 "UTF-8")]
     (clojure.core/let
      [md__611__auto__
       (java.security.MessageDigest/getInstance "SHA-256")]
      (.digest md__611__auto__ data1136)))))
  (clojure.core/extend-protocol
   G__1137
   java.io.InputStream
   (compute-sha2561135
    [data1136]
    (clojure.core/let
     [md__612__auto__
      (java.security.MessageDigest/getInstance "SHA-256")
      c__613__auto__
      (clojure.core/int *buffer-size*)
      buf__614__auto__
      (clojure.core/byte-array c__613__auto__)
      s__615__auto__
      data1136]
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
   (compute-sha2561135
    [data1136]
    (clojure.core/with-open
     [data1136 (clojure.java.io/input-stream data1136)]
     (clojure.core/let
      [md__612__auto__
       (java.security.MessageDigest/getInstance "SHA-256")
       c__613__auto__
       (clojure.core/int *buffer-size*)
       buf__614__auto__
       (clojure.core/byte-array c__613__auto__)
       s__615__auto__
       data1136]
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
  'G__1137)
 (do
  (clojure.core/defn
   sha256*
   "[Hash] SHA-256 (raw value)"
   [x]
   (compute-sha2561135 x))
  (clojure.core/defn
   sha256-file*
   "[Hash] SHA-256 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2561135 x)))
  (clojure.core/defn
   sha256-bytes
   "[Hash] SHA-256 (value -> byte array)"
   [x]
   (compute-sha2561135 x))
  (clojure.core/defn
   sha256-file-bytes
   "[Hash] SHA-256 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2561135 x)))
  (clojure.core/defn
   sha256
   "[Hash] SHA-256 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha2561135 x)))
  (clojure.core/defn
   sha256-file
   "[Hash] SHA-256 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-sha2561135 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1141
   (compute-sha2561138 [data1139 key1140]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1141 #'compute-sha2561138]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1141
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha2561138
    [data1139 key1140]
    (clojure.core/let
     [mac__492__auto__
      (javax.crypto.Mac/getInstance "HmacSHA256")
      msg__493__auto__
      (clojure.core/bytes data1139)
      k__494__auto__
      (javax.crypto.spec.SecretKeySpec. key1140 "HmacSHA256")]
     (clojure.core/->
      (clojure.core/doto
       mac__492__auto__
       (.init k__494__auto__)
       (.update msg__493__auto__))
      (.doFinal))))
   java.lang.String
   (compute-sha2561138
    [data1139 key1140]
    (clojure.core/let
     [data1139 (.getBytes data1139 "UTF-8")]
     (clojure.core/let
      [mac__492__auto__
       (javax.crypto.Mac/getInstance "HmacSHA256")
       msg__493__auto__
       (clojure.core/bytes data1139)
       k__494__auto__
       (javax.crypto.spec.SecretKeySpec. key1140 "HmacSHA256")]
      (clojure.core/->
       (clojure.core/doto
        mac__492__auto__
        (.init k__494__auto__)
        (.update msg__493__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1141
   java.io.InputStream
   (compute-sha2561138
    [data1139 key1140]
    (clojure.core/let
     [mac__495__auto__
      (javax.crypto.Mac/getInstance "HmacSHA256")
      k__496__auto__
      (javax.crypto.spec.SecretKeySpec. key1140 "HmacSHA256")
      c__497__auto__
      (clojure.core/int *buffer-size*)
      buf__498__auto__
      (clojure.core/byte-array c__497__auto__)
      s__499__auto__
      data1139]
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
   (compute-sha2561138
    [data1139 key1140]
    (clojure.core/with-open
     [data1139 (clojure.java.io/input-stream data1139)]
     (clojure.core/let
      [mac__495__auto__
       (javax.crypto.Mac/getInstance "HmacSHA256")
       k__496__auto__
       (javax.crypto.spec.SecretKeySpec. key1140 "HmacSHA256")
       c__497__auto__
       (clojure.core/int *buffer-size*)
       buf__498__auto__
       (clojure.core/byte-array c__497__auto__)
       s__499__auto__
       data1139]
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
  'G__1141)
 (do
  (clojure.core/defn
   sha256-hmac*
   "[HMAC] HmacSHA256 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha2561138
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha256-hmac-file*
   "[HMAC] HmacSHA256 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2561138
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha256-hmac-bytes
   "[HMAC] HmacSHA256 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-sha2561138
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   sha256-hmac-file-bytes
   "[HMAC] HmacSHA256 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2561138
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha256-hmac
   "[HMAC] HmacSHA256 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha2561138
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   sha256-hmac-file
   "[HMAC] HmacSHA256 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha2561138
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
(do
 (do
  (clojure.core/defprotocol
   G__1146
   (compute-sha2561142 [data1143 key1145])
   (compute-sha2561142-verify [data1143 sig1144 key1145]))
  (clojure.core/doseq
   [v__166__auto__
    [#'G__1146 #'compute-sha2561142 #'compute-sha2561142-verify]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1146
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha2561142
    [data1143 key1145]
    (clojure.core/let
     [signer__786__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA256withRSA")
       (.initSign key1145)
       (.update (clojure.core/bytes data1143)))]
     (.sign signer__786__auto__)))
   (compute-sha2561142-verify
    [data1143 sig1144 key1145]
    (clojure.core/let
     [signer__787__auto__
      (java.security.Signature/getInstance "SHA256withRSA")
      key__788__auto__
      key1145]
     (if
      (clojure.core/instance? java.security.PublicKey key__788__auto__)
      (.initVerify
       signer__787__auto__
       (pandect.utils.convert/as-public-key key__788__auto__))
      (.initVerify
       signer__787__auto__
       (pandect.utils.convert/as-certificate key__788__auto__)))
     (.update signer__787__auto__ (clojure.core/bytes data1143))
     (.verify signer__787__auto__ (clojure.core/bytes sig1144))))
   java.lang.String
   (compute-sha2561142
    [data1143 key1145]
    (clojure.core/let
     [data1143 (.getBytes data1143 "UTF-8")]
     (clojure.core/let
      [signer__786__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA256withRSA")
        (.initSign key1145)
        (.update (clojure.core/bytes data1143)))]
      (.sign signer__786__auto__))))
   (compute-sha2561142-verify
    [data1143 sig1144 key1145]
    (clojure.core/let
     [data1143 (.getBytes data1143 "UTF-8")]
     (clojure.core/let
      [signer__787__auto__
       (java.security.Signature/getInstance "SHA256withRSA")
       key__788__auto__
       key1145]
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
      (.update signer__787__auto__ (clojure.core/bytes data1143))
      (.verify signer__787__auto__ (clojure.core/bytes sig1144))))))
  (clojure.core/extend-protocol
   G__1146
   java.io.InputStream
   (compute-sha2561142
    [data1143 key1145]
    (clojure.core/let
     [signer__789__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "SHA256withRSA")
       (.initSign key1145))
      c__790__auto__
      (clojure.core/int *buffer-size*)
      buf__791__auto__
      (clojure.core/byte-array c__790__auto__)
      s__792__auto__
      data1143]
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
   (compute-sha2561142-verify
    [data1143 sig1144 key1145]
    (clojure.core/let
     [signer__794__auto__
      (java.security.Signature/getInstance "SHA256withRSA")
      c__795__auto__
      (clojure.core/int *buffer-size*)
      buf__796__auto__
      (clojure.core/byte-array c__795__auto__)
      s__797__auto__
      data1143
      key__798__auto__
      key1145]
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
     (.verify signer__794__auto__ (clojure.core/bytes sig1144))))
   java.io.File
   (compute-sha2561142
    [data1143 key1145]
    (clojure.core/with-open
     [data1143 (clojure.java.io/input-stream data1143)]
     (clojure.core/let
      [signer__789__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "SHA256withRSA")
        (.initSign key1145))
       c__790__auto__
       (clojure.core/int *buffer-size*)
       buf__791__auto__
       (clojure.core/byte-array c__790__auto__)
       s__792__auto__
       data1143]
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
   (compute-sha2561142-verify
    [data1143 sig1144 key1145]
    (clojure.core/with-open
     [data1143 (clojure.java.io/input-stream data1143)]
     (clojure.core/let
      [signer__794__auto__
       (java.security.Signature/getInstance "SHA256withRSA")
       c__795__auto__
       (clojure.core/int *buffer-size*)
       buf__796__auto__
       (clojure.core/byte-array c__795__auto__)
       s__797__auto__
       data1143
       key__798__auto__
       key1145]
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
      (.verify signer__794__auto__ (clojure.core/bytes sig1144))))))
  'G__1146)
 (do
  (clojure.core/defn
   sha256-rsa*
   "[Signature] SHA256withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha2561142 x private-key))
  (clojure.core/defn
   sha256-rsa-file*
   "[Signature] SHA256withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2561142 x private-key)))
  (clojure.core/defn
   sha256-rsa-bytes
   "[Signature] SHA256withRSA (value -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-sha2561142 x private-key))
  (clojure.core/defn
   sha256-rsa-file-bytes
   "[Signature] SHA256withRSA (file path -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2561142 x private-key)))
  (clojure.core/defn
   sha256-rsa
   "[Signature] SHA256withRSA (value -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (pandect.utils.convert/bytes->hex
    (compute-sha2561142 x private-key)))
  (clojure.core/defn
   sha256-rsa-file
   "[Signature] SHA256withRSA (file path -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-sha2561142 x private-key))))
  (clojure.core/defn
   sha256-rsa-verify
   "[Signature] SHA256withRSA\n\nVerify the given message signature using the given java.security.PublicKey\nor java.security.cert.Certificate.\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or any value implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (compute-sha2561142-verify
    x
    (pandect.utils.convert/convert-signature-to-byte-array signature)
    public-key))
  (clojure.core/defn
   sha256-rsa-verify-file
   "[Signature] SHA256withRSA\n\nVerify the given message signature using the given java.security.PublicKey\nor java.security.cert.Certificate.\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or any value implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha2561142-verify
     x
     (pandect.utils.convert/convert-signature-to-byte-array signature)
     public-key)))))
