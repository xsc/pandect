(ns
 pandect.algo.md5
 "MD5 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1002 (compute-md51000 [data1001]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1002 #'compute-md51000]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1002
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md51000
    [data1001]
    (clojure.core/let
     [md__611__auto__ (java.security.MessageDigest/getInstance "MD5")]
     (.digest md__611__auto__ data1001)))
   java.lang.String
   (compute-md51000
    [data1001]
    (clojure.core/let
     [data1001 (.getBytes data1001 "UTF-8")]
     (clojure.core/let
      [md__611__auto__ (java.security.MessageDigest/getInstance "MD5")]
      (.digest md__611__auto__ data1001)))))
  (clojure.core/extend-protocol
   G__1002
   java.io.InputStream
   (compute-md51000
    [data1001]
    (clojure.core/let
     [md__612__auto__
      (java.security.MessageDigest/getInstance "MD5")
      c__613__auto__
      (clojure.core/int *buffer-size*)
      buf__614__auto__
      (clojure.core/byte-array c__613__auto__)
      s__615__auto__
      data1001]
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
   (compute-md51000
    [data1001]
    (clojure.core/with-open
     [data1001 (clojure.java.io/input-stream data1001)]
     (clojure.core/let
      [md__612__auto__
       (java.security.MessageDigest/getInstance "MD5")
       c__613__auto__
       (clojure.core/int *buffer-size*)
       buf__614__auto__
       (clojure.core/byte-array c__613__auto__)
       s__615__auto__
       data1001]
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
  'G__1002)
 (do
  (clojure.core/defn
   md5*
   "[Hash] MD5 (raw value)"
   [x]
   (compute-md51000 x))
  (clojure.core/defn
   md5-file*
   "[Hash] MD5 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md51000 x)))
  (clojure.core/defn
   md5-bytes
   "[Hash] MD5 (value -> byte array)"
   [x]
   (compute-md51000 x))
  (clojure.core/defn
   md5-file-bytes
   "[Hash] MD5 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md51000 x)))
  (clojure.core/defn
   md5
   "[Hash] MD5 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-md51000 x)))
  (clojure.core/defn
   md5-file
   "[Hash] MD5 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-md51000 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1006
   (compute-md51003 [data1004 key1005]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1006 #'compute-md51003]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1006
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md51003
    [data1004 key1005]
    (clojure.core/let
     [mac__492__auto__
      (javax.crypto.Mac/getInstance "HmacMD5")
      msg__493__auto__
      (clojure.core/bytes data1004)
      k__494__auto__
      (javax.crypto.spec.SecretKeySpec. key1005 "HmacMD5")]
     (clojure.core/->
      (clojure.core/doto
       mac__492__auto__
       (.init k__494__auto__)
       (.update msg__493__auto__))
      (.doFinal))))
   java.lang.String
   (compute-md51003
    [data1004 key1005]
    (clojure.core/let
     [data1004 (.getBytes data1004 "UTF-8")]
     (clojure.core/let
      [mac__492__auto__
       (javax.crypto.Mac/getInstance "HmacMD5")
       msg__493__auto__
       (clojure.core/bytes data1004)
       k__494__auto__
       (javax.crypto.spec.SecretKeySpec. key1005 "HmacMD5")]
      (clojure.core/->
       (clojure.core/doto
        mac__492__auto__
        (.init k__494__auto__)
        (.update msg__493__auto__))
       (.doFinal))))))
  (clojure.core/extend-protocol
   G__1006
   java.io.InputStream
   (compute-md51003
    [data1004 key1005]
    (clojure.core/let
     [mac__495__auto__
      (javax.crypto.Mac/getInstance "HmacMD5")
      k__496__auto__
      (javax.crypto.spec.SecretKeySpec. key1005 "HmacMD5")
      c__497__auto__
      (clojure.core/int *buffer-size*)
      buf__498__auto__
      (clojure.core/byte-array c__497__auto__)
      s__499__auto__
      data1004]
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
   (compute-md51003
    [data1004 key1005]
    (clojure.core/with-open
     [data1004 (clojure.java.io/input-stream data1004)]
     (clojure.core/let
      [mac__495__auto__
       (javax.crypto.Mac/getInstance "HmacMD5")
       k__496__auto__
       (javax.crypto.spec.SecretKeySpec. key1005 "HmacMD5")
       c__497__auto__
       (clojure.core/int *buffer-size*)
       buf__498__auto__
       (clojure.core/byte-array c__497__auto__)
       s__499__auto__
       data1004]
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
  'G__1006)
 (do
  (clojure.core/defn
   md5-hmac*
   "[HMAC] HmacMD5 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-md51003
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   md5-hmac-file*
   "[HMAC] HmacMD5 (raw value)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md51003
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   md5-hmac-bytes
   "[HMAC] HmacMD5 (value -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (compute-md51003
    x
    (pandect.utils.convert/convert-to-byte-array secret)))
  (clojure.core/defn
   md5-hmac-file-bytes
   "[HMAC] HmacMD5 (file path -> byte array)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md51003
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   md5-hmac
   "[HMAC] HmacMD5 (value -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-md51003
     x
     (pandect.utils.convert/convert-to-byte-array secret))))
  (clojure.core/defn
   md5-hmac-file
   "[HMAC] HmacMD5 (file path -> string)\n\n'secret' can be given as a byte array, string, java.io.File, java.io.InputStream\nor any value implementing `pandect.utils.convert/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-md51003
      x
      (pandect.utils.convert/convert-to-byte-array secret)))))))
(do
 (do
  (clojure.core/defprotocol
   G__1011
   (compute-md51007 [data1008 key1010])
   (compute-md51007-verify [data1008 sig1009 key1010]))
  (clojure.core/doseq
   [v__166__auto__
    [#'G__1011 #'compute-md51007 #'compute-md51007-verify]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1011
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md51007
    [data1008 key1010]
    (clojure.core/let
     [signer__786__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "MD5withRSA")
       (.initSign key1010)
       (.update (clojure.core/bytes data1008)))]
     (.sign signer__786__auto__)))
   (compute-md51007-verify
    [data1008 sig1009 key1010]
    (clojure.core/let
     [signer__787__auto__
      (java.security.Signature/getInstance "MD5withRSA")
      key__788__auto__
      key1010]
     (if
      (clojure.core/instance? java.security.PublicKey key__788__auto__)
      (.initVerify
       signer__787__auto__
       (pandect.utils.convert/as-public-key key__788__auto__))
      (.initVerify
       signer__787__auto__
       (pandect.utils.convert/as-certificate key__788__auto__)))
     (.update signer__787__auto__ (clojure.core/bytes data1008))
     (.verify signer__787__auto__ (clojure.core/bytes sig1009))))
   java.lang.String
   (compute-md51007
    [data1008 key1010]
    (clojure.core/let
     [data1008 (.getBytes data1008 "UTF-8")]
     (clojure.core/let
      [signer__786__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "MD5withRSA")
        (.initSign key1010)
        (.update (clojure.core/bytes data1008)))]
      (.sign signer__786__auto__))))
   (compute-md51007-verify
    [data1008 sig1009 key1010]
    (clojure.core/let
     [data1008 (.getBytes data1008 "UTF-8")]
     (clojure.core/let
      [signer__787__auto__
       (java.security.Signature/getInstance "MD5withRSA")
       key__788__auto__
       key1010]
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
      (.update signer__787__auto__ (clojure.core/bytes data1008))
      (.verify signer__787__auto__ (clojure.core/bytes sig1009))))))
  (clojure.core/extend-protocol
   G__1011
   java.io.InputStream
   (compute-md51007
    [data1008 key1010]
    (clojure.core/let
     [signer__789__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "MD5withRSA")
       (.initSign key1010))
      c__790__auto__
      (clojure.core/int *buffer-size*)
      buf__791__auto__
      (clojure.core/byte-array c__790__auto__)
      s__792__auto__
      data1008]
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
   (compute-md51007-verify
    [data1008 sig1009 key1010]
    (clojure.core/let
     [signer__794__auto__
      (java.security.Signature/getInstance "MD5withRSA")
      c__795__auto__
      (clojure.core/int *buffer-size*)
      buf__796__auto__
      (clojure.core/byte-array c__795__auto__)
      s__797__auto__
      data1008
      key__798__auto__
      key1010]
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
     (.verify signer__794__auto__ (clojure.core/bytes sig1009))))
   java.io.File
   (compute-md51007
    [data1008 key1010]
    (clojure.core/with-open
     [data1008 (clojure.java.io/input-stream data1008)]
     (clojure.core/let
      [signer__789__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "MD5withRSA")
        (.initSign key1010))
       c__790__auto__
       (clojure.core/int *buffer-size*)
       buf__791__auto__
       (clojure.core/byte-array c__790__auto__)
       s__792__auto__
       data1008]
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
   (compute-md51007-verify
    [data1008 sig1009 key1010]
    (clojure.core/with-open
     [data1008 (clojure.java.io/input-stream data1008)]
     (clojure.core/let
      [signer__794__auto__
       (java.security.Signature/getInstance "MD5withRSA")
       c__795__auto__
       (clojure.core/int *buffer-size*)
       buf__796__auto__
       (clojure.core/byte-array c__795__auto__)
       s__797__auto__
       data1008
       key__798__auto__
       key1010]
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
      (.verify signer__794__auto__ (clojure.core/bytes sig1009))))))
  'G__1011)
 (do
  (clojure.core/defn
   md5-rsa*
   "[Signature] MD5withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-md51007 x private-key))
  (clojure.core/defn
   md5-rsa-file*
   "[Signature] MD5withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md51007 x private-key)))
  (clojure.core/defn
   md5-rsa-bytes
   "[Signature] MD5withRSA (value -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-md51007 x private-key))
  (clojure.core/defn
   md5-rsa-file-bytes
   "[Signature] MD5withRSA (file path -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md51007 x private-key)))
  (clojure.core/defn
   md5-rsa
   "[Signature] MD5withRSA (value -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (pandect.utils.convert/bytes->hex (compute-md51007 x private-key)))
  (clojure.core/defn
   md5-rsa-file
   "[Signature] MD5withRSA (file path -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-md51007 x private-key))))
  (clojure.core/defn
   md5-rsa-verify
   "[Signature] MD5withRSA\n\nVerify the given message signature using the given java.security.PublicKey\nor java.security.cert.Certificate.\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or any value implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (compute-md51007-verify
    x
    (pandect.utils.convert/convert-signature-to-byte-array signature)
    public-key))
  (clojure.core/defn
   md5-rsa-verify-file
   "[Signature] MD5withRSA\n\nVerify the given message signature using the given java.security.PublicKey\nor java.security.cert.Certificate.\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or any value implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md51007-verify
     x
     (pandect.utils.convert/convert-signature-to-byte-array signature)
     public-key)))))
