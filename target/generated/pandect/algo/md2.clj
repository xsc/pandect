(ns
 pandect.algo.md2
 "MD2 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1046 (compute-md21044 [data1045]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1046 #'compute-md21044]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1046
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md21044
    [data1045]
    (clojure.core/let
     [md__611__auto__ (java.security.MessageDigest/getInstance "MD2")]
     (.digest md__611__auto__ data1045)))
   java.lang.String
   (compute-md21044
    [data1045]
    (clojure.core/let
     [data1045 (.getBytes data1045 "UTF-8")]
     (clojure.core/let
      [md__611__auto__ (java.security.MessageDigest/getInstance "MD2")]
      (.digest md__611__auto__ data1045)))))
  (clojure.core/extend-protocol
   G__1046
   java.io.InputStream
   (compute-md21044
    [data1045]
    (clojure.core/let
     [md__612__auto__
      (java.security.MessageDigest/getInstance "MD2")
      c__613__auto__
      (clojure.core/int *buffer-size*)
      buf__614__auto__
      (clojure.core/byte-array c__613__auto__)
      s__615__auto__
      data1045]
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
   (compute-md21044
    [data1045]
    (clojure.core/with-open
     [data1045 (clojure.java.io/input-stream data1045)]
     (clojure.core/let
      [md__612__auto__
       (java.security.MessageDigest/getInstance "MD2")
       c__613__auto__
       (clojure.core/int *buffer-size*)
       buf__614__auto__
       (clojure.core/byte-array c__613__auto__)
       s__615__auto__
       data1045]
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
  'G__1046)
 (do
  (clojure.core/defn
   md2*
   "[Hash] MD2 (raw value)"
   [x]
   (compute-md21044 x))
  (clojure.core/defn
   md2-file*
   "[Hash] MD2 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md21044 x)))
  (clojure.core/defn
   md2-bytes
   "[Hash] MD2 (value -> byte array)"
   [x]
   (compute-md21044 x))
  (clojure.core/defn
   md2-file-bytes
   "[Hash] MD2 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md21044 x)))
  (clojure.core/defn
   md2
   "[Hash] MD2 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-md21044 x)))
  (clojure.core/defn
   md2-file
   "[Hash] MD2 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-md21044 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1051
   (compute-md21047 [data1048 key1050])
   (compute-md21047-verify [data1048 sig1049 key1050]))
  (clojure.core/doseq
   [v__166__auto__
    [#'G__1051 #'compute-md21047 #'compute-md21047-verify]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1051
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md21047
    [data1048 key1050]
    (clojure.core/let
     [signer__786__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "MD2withRSA")
       (.initSign key1050)
       (.update (clojure.core/bytes data1048)))]
     (.sign signer__786__auto__)))
   (compute-md21047-verify
    [data1048 sig1049 key1050]
    (clojure.core/let
     [signer__787__auto__
      (java.security.Signature/getInstance "MD2withRSA")
      key__788__auto__
      key1050]
     (if
      (clojure.core/instance? java.security.PublicKey key__788__auto__)
      (.initVerify
       signer__787__auto__
       (pandect.utils.convert/as-public-key key__788__auto__))
      (.initVerify
       signer__787__auto__
       (pandect.utils.convert/as-certificate key__788__auto__)))
     (.update signer__787__auto__ (clojure.core/bytes data1048))
     (.verify signer__787__auto__ (clojure.core/bytes sig1049))))
   java.lang.String
   (compute-md21047
    [data1048 key1050]
    (clojure.core/let
     [data1048 (.getBytes data1048 "UTF-8")]
     (clojure.core/let
      [signer__786__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "MD2withRSA")
        (.initSign key1050)
        (.update (clojure.core/bytes data1048)))]
      (.sign signer__786__auto__))))
   (compute-md21047-verify
    [data1048 sig1049 key1050]
    (clojure.core/let
     [data1048 (.getBytes data1048 "UTF-8")]
     (clojure.core/let
      [signer__787__auto__
       (java.security.Signature/getInstance "MD2withRSA")
       key__788__auto__
       key1050]
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
      (.update signer__787__auto__ (clojure.core/bytes data1048))
      (.verify signer__787__auto__ (clojure.core/bytes sig1049))))))
  (clojure.core/extend-protocol
   G__1051
   java.io.InputStream
   (compute-md21047
    [data1048 key1050]
    (clojure.core/let
     [signer__789__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "MD2withRSA")
       (.initSign key1050))
      c__790__auto__
      (clojure.core/int *buffer-size*)
      buf__791__auto__
      (clojure.core/byte-array c__790__auto__)
      s__792__auto__
      data1048]
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
   (compute-md21047-verify
    [data1048 sig1049 key1050]
    (clojure.core/let
     [signer__794__auto__
      (java.security.Signature/getInstance "MD2withRSA")
      c__795__auto__
      (clojure.core/int *buffer-size*)
      buf__796__auto__
      (clojure.core/byte-array c__795__auto__)
      s__797__auto__
      data1048
      key__798__auto__
      key1050]
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
     (.verify signer__794__auto__ (clojure.core/bytes sig1049))))
   java.io.File
   (compute-md21047
    [data1048 key1050]
    (clojure.core/with-open
     [data1048 (clojure.java.io/input-stream data1048)]
     (clojure.core/let
      [signer__789__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "MD2withRSA")
        (.initSign key1050))
       c__790__auto__
       (clojure.core/int *buffer-size*)
       buf__791__auto__
       (clojure.core/byte-array c__790__auto__)
       s__792__auto__
       data1048]
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
   (compute-md21047-verify
    [data1048 sig1049 key1050]
    (clojure.core/with-open
     [data1048 (clojure.java.io/input-stream data1048)]
     (clojure.core/let
      [signer__794__auto__
       (java.security.Signature/getInstance "MD2withRSA")
       c__795__auto__
       (clojure.core/int *buffer-size*)
       buf__796__auto__
       (clojure.core/byte-array c__795__auto__)
       s__797__auto__
       data1048
       key__798__auto__
       key1050]
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
      (.verify signer__794__auto__ (clojure.core/bytes sig1049))))))
  'G__1051)
 (do
  (clojure.core/defn
   md2-rsa*
   "[Signature] MD2withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-md21047 x private-key))
  (clojure.core/defn
   md2-rsa-file*
   "[Signature] MD2withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md21047 x private-key)))
  (clojure.core/defn
   md2-rsa-bytes
   "[Signature] MD2withRSA (value -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-md21047 x private-key))
  (clojure.core/defn
   md2-rsa-file-bytes
   "[Signature] MD2withRSA (file path -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md21047 x private-key)))
  (clojure.core/defn
   md2-rsa
   "[Signature] MD2withRSA (value -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (pandect.utils.convert/bytes->hex (compute-md21047 x private-key)))
  (clojure.core/defn
   md2-rsa-file
   "[Signature] MD2withRSA (file path -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-md21047 x private-key))))
  (clojure.core/defn
   md2-rsa-verify
   "[Signature] MD2withRSA\n\nVerify the given message signature using the given java.security.PublicKey\nor java.security.cert.Certificate.\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or any value implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (compute-md21047-verify
    x
    (pandect.utils.convert/convert-signature-to-byte-array signature)
    public-key))
  (clojure.core/defn
   md2-rsa-verify-file
   "[Signature] MD2withRSA\n\nVerify the given message signature using the given java.security.PublicKey\nor java.security.cert.Certificate.\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or any value implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md21047-verify
     x
     (pandect.utils.convert/convert-signature-to-byte-array signature)
     public-key)))))
