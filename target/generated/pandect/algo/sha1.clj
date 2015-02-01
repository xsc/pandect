(ns
 pandect.algo.sha1
 "SHA-1 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__694
   (compute-sha1692 [this__228__auto__]))
  (clojure.core/doseq
   [v__229__auto__ [#'G__694 #'compute-sha1692]]
   (clojure.core/alter-meta!
    v__229__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__694
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha1692
    [data693]
    (clojure.core/let
     [md__432__auto__
      (java.security.MessageDigest/getInstance "SHA-1")]
     (.digest md__432__auto__ data693)))
   java.lang.String
   (compute-sha1692
    [data693]
    (clojure.core/let
     [md__432__auto__
      (java.security.MessageDigest/getInstance "SHA-1")]
     (.digest md__432__auto__ (.getBytes data693 "UTF-8"))))
   java.io.InputStream
   (compute-sha1692
    [data693]
    (clojure.core/let
     [md__433__auto__
      (java.security.MessageDigest/getInstance "SHA-1")
      c__434__auto__
      (clojure.core/int *buffer-size*)
      buf__435__auto__
      (clojure.core/byte-array c__434__auto__)
      s__436__auto__
      data693]
     (clojure.core/loop
      []
      (clojure.core/let
       [r__437__auto__
        (.read s__436__auto__ buf__435__auto__ 0 c__434__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__437__auto__ -1)
        (.update md__433__auto__ buf__435__auto__ 0 r__437__auto__)
        (recur))))
     (.digest md__433__auto__)))
   java.io.File
   (compute-sha1692
    [data693]
    (clojure.core/with-open
     [data693
      (java.io.FileInputStream. (clojure.java.io/as-file data693))]
     (clojure.core/let
      [md__433__auto__
       (java.security.MessageDigest/getInstance "SHA-1")
       c__434__auto__
       (clojure.core/int *buffer-size*)
       buf__435__auto__
       (clojure.core/byte-array c__434__auto__)
       s__436__auto__
       data693]
      (clojure.core/loop
       []
       (clojure.core/let
        [r__437__auto__
         (.read s__436__auto__ buf__435__auto__ 0 c__434__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__437__auto__ -1)
         (.update md__433__auto__ buf__435__auto__ 0 r__437__auto__)
         (recur))))
      (.digest md__433__auto__))))))
 [(clojure.core/defn
   sha1*
   "[Hash] SHA-1 (raw value)"
   [x]
   (compute-sha1692 x))
  (clojure.core/defn
   sha1-file*
   "[Hash] SHA-1 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha1692 x)))
  (clojure.core/defn
   sha1-file-bytes
   "[Hash] SHA-1 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha1692 x)))
  (clojure.core/defn
   sha1-file
   "[Hash] SHA-1 (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha1692 x))))
  (clojure.core/defn
   sha1-bytes
   "[Hash] SHA-1 (value -> byte array)"
   [x]
   (compute-sha1692 x))
  (clojure.core/defn
   sha1
   "[Hash] SHA-1 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha1692 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__698
   (compute-sha1695 [this__404__auto__ key__405__auto__]))
  (clojure.core/doseq
   [v__406__auto__ [#'G__698 #'compute-sha1695]]
   (clojure.core/alter-meta!
    v__406__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__698
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha1695
    [data696 key697]
    (clojure.core/let
     [mac__418__auto__
      (javax.crypto.Mac/getInstance "HmacSHA1")
      msg__419__auto__
      (clojure.core/bytes data696)
      k__420__auto__
      (javax.crypto.spec.SecretKeySpec. key697 "HmacSHA1")]
     (clojure.core/->
      (clojure.core/doto
       mac__418__auto__
       (.init k__420__auto__)
       (.update msg__419__auto__))
      (.doFinal))))
   java.lang.String
   (compute-sha1695
    [data696 key697]
    (clojure.core/let
     [mac__418__auto__
      (javax.crypto.Mac/getInstance "HmacSHA1")
      msg__419__auto__
      (clojure.core/bytes (.getBytes data696 "UTF-8"))
      k__420__auto__
      (javax.crypto.spec.SecretKeySpec. key697 "HmacSHA1")]
     (clojure.core/->
      (clojure.core/doto
       mac__418__auto__
       (.init k__420__auto__)
       (.update msg__419__auto__))
      (.doFinal))))
   java.io.InputStream
   (compute-sha1695
    [data696 key697]
    (clojure.core/let
     [mac__421__auto__
      (javax.crypto.Mac/getInstance "HmacSHA1")
      k__422__auto__
      (javax.crypto.spec.SecretKeySpec. key697 "HmacSHA1")
      c__423__auto__
      (clojure.core/int *buffer-size*)
      buf__424__auto__
      (clojure.core/byte-array c__423__auto__)
      s__425__auto__
      data696]
     (.init mac__421__auto__ k__422__auto__)
     (clojure.core/loop
      []
      (clojure.core/let
       [r__426__auto__
        (.read s__425__auto__ buf__424__auto__ 0 c__423__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__426__auto__ -1)
        (.update mac__421__auto__ buf__424__auto__ 0 r__426__auto__)
        (recur))))
     (.doFinal mac__421__auto__)))
   java.io.File
   (compute-sha1695
    [data696 key697]
    (clojure.core/with-open
     [data696
      (java.io.FileInputStream. (clojure.java.io/as-file data696))]
     (clojure.core/let
      [mac__421__auto__
       (javax.crypto.Mac/getInstance "HmacSHA1")
       k__422__auto__
       (javax.crypto.spec.SecretKeySpec. key697 "HmacSHA1")
       c__423__auto__
       (clojure.core/int *buffer-size*)
       buf__424__auto__
       (clojure.core/byte-array c__423__auto__)
       s__425__auto__
       data696]
      (.init mac__421__auto__ k__422__auto__)
      (clojure.core/loop
       []
       (clojure.core/let
        [r__426__auto__
         (.read s__425__auto__ buf__424__auto__ 0 c__423__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__426__auto__ -1)
         (.update mac__421__auto__ buf__424__auto__ 0 r__426__auto__)
         (recur))))
      (.doFinal mac__421__auto__))))))
 [(clojure.core/defn
   sha1-hmac*
   "[HMAC] HmacSHA1 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha1695
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha1-hmac-file*
   "[HMAC] HmacSHA1 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha1695
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha1-hmac-file-bytes
   "[HMAC] HmacSHA1 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha1695
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha1-hmac-file
   "[HMAC] HmacSHA1 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha1695
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   sha1-hmac-bytes
   "[HMAC] HmacSHA1 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha1695
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha1-hmac
   "[HMAC] HmacSHA1 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha1695
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
