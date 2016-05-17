(ns
 pandect.algo.md2
 "MD2 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1207 (compute-md21205 [data1206]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1207 #'compute-md21205]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1207
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md21205
    [data1206]
    (clojure.core/let
     [md__668__auto__ (java.security.MessageDigest/getInstance "MD2")]
     (.digest md__668__auto__ data1206)))
   java.lang.String
   (compute-md21205
    [data1206]
    (clojure.core/let
     [data1206 (.getBytes data1206 "UTF-8")]
     (clojure.core/let
      [md__668__auto__ (java.security.MessageDigest/getInstance "MD2")]
      (.digest md__668__auto__ data1206)))))
  (clojure.core/extend-protocol
   G__1207
   java.io.InputStream
   (compute-md21205
    [data1206]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "MD2")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1206]
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
   (compute-md21205
    [data1206]
    (clojure.core/with-open
     [data1206 (clojure.java.io/input-stream data1206)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "MD2")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1206]
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
  'G__1207)
 (do
  (clojure.core/defn
   md2*
   "[Hash] MD2 (raw value)"
   [x]
   (compute-md21205 x))
  (clojure.core/defn
   md2-file*
   "[Hash] MD2 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md21205 x)))
  (clojure.core/defn
   md2-bytes
   "[Hash] MD2 (value -> byte array)"
   [x]
   (compute-md21205 x))
  (clojure.core/defn
   md2-file-bytes
   "[Hash] MD2 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md21205 x)))
  (clojure.core/defn
   md2
   "[Hash] MD2 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-md21205 x)))
  (clojure.core/defn
   md2-file
   "[Hash] MD2 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-md21205 x))))))
(do
 (do
  (clojure.core/defprotocol
   G__1239
   (compute-md21235 [data1236 key1238])
   (compute-md21235-verify [data1236 sig1237 key1238]))
  (clojure.core/doseq
   [v__186__auto__
    [#'G__1239 #'compute-md21235 #'compute-md21235-verify]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1239
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md21235
    [data1236 key1238]
    (clojure.core/let
     [signer__847__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "MD2withRSA")
       (.initSign key1238)
       (.update (clojure.core/bytes data1236)))]
     (.sign signer__847__auto__)))
   (compute-md21235-verify
    [data1236 sig1237 key1238]
    (clojure.core/let
     [signer__848__auto__
      (java.security.Signature/getInstance "MD2withRSA")
      key__849__auto__
      (pandect.utils.convert/as-public-key key1238)]
     (.initVerify signer__848__auto__ key__849__auto__)
     (.update signer__848__auto__ (clojure.core/bytes data1236))
     (.verify signer__848__auto__ (clojure.core/bytes sig1237))))
   java.lang.String
   (compute-md21235
    [data1236 key1238]
    (clojure.core/let
     [data1236 (.getBytes data1236 "UTF-8")]
     (clojure.core/let
      [signer__847__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "MD2withRSA")
        (.initSign key1238)
        (.update (clojure.core/bytes data1236)))]
      (.sign signer__847__auto__))))
   (compute-md21235-verify
    [data1236 sig1237 key1238]
    (clojure.core/let
     [data1236 (.getBytes data1236 "UTF-8")]
     (clojure.core/let
      [signer__848__auto__
       (java.security.Signature/getInstance "MD2withRSA")
       key__849__auto__
       (pandect.utils.convert/as-public-key key1238)]
      (.initVerify signer__848__auto__ key__849__auto__)
      (.update signer__848__auto__ (clojure.core/bytes data1236))
      (.verify signer__848__auto__ (clojure.core/bytes sig1237))))))
  (clojure.core/extend-protocol
   G__1239
   java.io.InputStream
   (compute-md21235
    [data1236 key1238]
    (clojure.core/let
     [signer__850__auto__
      (clojure.core/doto
       (java.security.Signature/getInstance "MD2withRSA")
       (.initSign key1238))
      c__851__auto__
      (clojure.core/int *buffer-size*)
      buf__852__auto__
      (clojure.core/byte-array c__851__auto__)
      s__853__auto__
      data1236]
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
   (compute-md21235-verify
    [data1236 sig1237 key1238]
    (clojure.core/let
     [signer__855__auto__
      (java.security.Signature/getInstance "MD2withRSA")
      c__856__auto__
      (clojure.core/int *buffer-size*)
      buf__857__auto__
      (clojure.core/byte-array c__856__auto__)
      s__858__auto__
      data1236
      key__859__auto__
      (pandect.utils.convert/as-public-key key1238)]
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
     (.verify signer__855__auto__ (clojure.core/bytes sig1237))))
   java.io.File
   (compute-md21235
    [data1236 key1238]
    (clojure.core/with-open
     [data1236 (clojure.java.io/input-stream data1236)]
     (clojure.core/let
      [signer__850__auto__
       (clojure.core/doto
        (java.security.Signature/getInstance "MD2withRSA")
        (.initSign key1238))
       c__851__auto__
       (clojure.core/int *buffer-size*)
       buf__852__auto__
       (clojure.core/byte-array c__851__auto__)
       s__853__auto__
       data1236]
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
   (compute-md21235-verify
    [data1236 sig1237 key1238]
    (clojure.core/with-open
     [data1236 (clojure.java.io/input-stream data1236)]
     (clojure.core/let
      [signer__855__auto__
       (java.security.Signature/getInstance "MD2withRSA")
       c__856__auto__
       (clojure.core/int *buffer-size*)
       buf__857__auto__
       (clojure.core/byte-array c__856__auto__)
       s__858__auto__
       data1236
       key__859__auto__
       (pandect.utils.convert/as-public-key key1238)]
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
      (.verify signer__855__auto__ (clojure.core/bytes sig1237))))))
  'G__1239)
 (do
  (clojure.core/defn
   md2-rsa*
   "[Signature] MD2withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-md21235 x private-key))
  (clojure.core/defn
   md2-rsa-file*
   "[Signature] MD2withRSA (raw value)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md21235 x private-key)))
  (clojure.core/defn
   md2-rsa-bytes
   "[Signature] MD2withRSA (value -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (compute-md21235 x private-key))
  (clojure.core/defn
   md2-rsa-file-bytes
   "[Signature] MD2withRSA (file path -> byte array)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md21235 x private-key)))
  (clojure.core/defn
   md2-rsa
   "[Signature] MD2withRSA (value -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (pandect.utils.convert/bytes->hex (compute-md21235 x private-key)))
  (clojure.core/defn
   md2-rsa-file
   "[Signature] MD2withRSA (file path -> string)\n\nSign the given message using the given java.security.PrivateKey."
   [x private-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex
     (compute-md21235 x private-key))))
  (clojure.core/defn
   md2-rsa-verify
   "[Signature] MD2withRSA\n\nVerify the given message signature using the given public key (anything implementing `pandect.utils.convert/PublicKeyConvertable`)\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or anything implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (compute-md21235-verify
    x
    (pandect.utils.convert/convert-signature-to-byte-array signature)
    (pandect.utils.convert/convert-to-public-key public-key)))
  (clojure.core/defn
   md2-rsa-verify-file
   "[Signature] MD2withRSA\n\nVerify the given message signature using the given public key (anything implementing `pandect.utils.convert/PublicKeyConvertable`)\n\nThe signature can be given as a byte array, hex (!) string, java.io.File,\njava.io.InputStream or anything implementing `pandect.utils.convert/ByteConvertable`."
   [x signature public-key]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-md21235-verify
     x
     (pandect.utils.convert/convert-signature-to-byte-array signature)
     (pandect.utils.convert/convert-to-public-key public-key))))))
