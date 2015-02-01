(ns
 pandect.algo.sha384
 "SHA-384 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__708
   (compute-sha384706 [this__228__auto__]))
  (clojure.core/doseq
   [v__229__auto__ [#'G__708 #'compute-sha384706]]
   (clojure.core/alter-meta!
    v__229__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__708
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha384706
    [data707]
    (clojure.core/let
     [md__432__auto__
      (java.security.MessageDigest/getInstance "SHA-384")]
     (.digest md__432__auto__ data707)))
   java.lang.String
   (compute-sha384706
    [data707]
    (clojure.core/let
     [md__432__auto__
      (java.security.MessageDigest/getInstance "SHA-384")]
     (.digest md__432__auto__ (.getBytes data707 "UTF-8"))))
   java.io.InputStream
   (compute-sha384706
    [data707]
    (clojure.core/let
     [md__433__auto__
      (java.security.MessageDigest/getInstance "SHA-384")
      c__434__auto__
      (clojure.core/int *buffer-size*)
      buf__435__auto__
      (clojure.core/byte-array c__434__auto__)
      s__436__auto__
      data707]
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
   (compute-sha384706
    [data707]
    (clojure.core/with-open
     [data707
      (java.io.FileInputStream. (clojure.java.io/as-file data707))]
     (clojure.core/let
      [md__433__auto__
       (java.security.MessageDigest/getInstance "SHA-384")
       c__434__auto__
       (clojure.core/int *buffer-size*)
       buf__435__auto__
       (clojure.core/byte-array c__434__auto__)
       s__436__auto__
       data707]
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
   sha384*
   "[Hash] SHA-384 (raw value)"
   [x]
   (compute-sha384706 x))
  (clojure.core/defn
   sha384-file*
   "[Hash] SHA-384 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha384706 x)))
  (clojure.core/defn
   sha384-file-bytes
   "[Hash] SHA-384 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha384706 x)))
  (clojure.core/defn
   sha384-file
   "[Hash] SHA-384 (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha384706 x))))
  (clojure.core/defn
   sha384-bytes
   "[Hash] SHA-384 (value -> byte array)"
   [x]
   (compute-sha384706 x))
  (clojure.core/defn
   sha384
   "[Hash] SHA-384 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha384706 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__712
   (compute-sha384709 [this__404__auto__ key__405__auto__]))
  (clojure.core/doseq
   [v__406__auto__ [#'G__712 #'compute-sha384709]]
   (clojure.core/alter-meta!
    v__406__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__712
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha384709
    [data710 key711]
    (clojure.core/let
     [mac__418__auto__
      (javax.crypto.Mac/getInstance "HmacSHA384")
      msg__419__auto__
      (clojure.core/bytes data710)
      k__420__auto__
      (javax.crypto.spec.SecretKeySpec. key711 "HmacSHA384")]
     (clojure.core/->
      (clojure.core/doto
       mac__418__auto__
       (.init k__420__auto__)
       (.update msg__419__auto__))
      (.doFinal))))
   java.lang.String
   (compute-sha384709
    [data710 key711]
    (clojure.core/let
     [mac__418__auto__
      (javax.crypto.Mac/getInstance "HmacSHA384")
      msg__419__auto__
      (clojure.core/bytes (.getBytes data710 "UTF-8"))
      k__420__auto__
      (javax.crypto.spec.SecretKeySpec. key711 "HmacSHA384")]
     (clojure.core/->
      (clojure.core/doto
       mac__418__auto__
       (.init k__420__auto__)
       (.update msg__419__auto__))
      (.doFinal))))
   java.io.InputStream
   (compute-sha384709
    [data710 key711]
    (clojure.core/let
     [mac__421__auto__
      (javax.crypto.Mac/getInstance "HmacSHA384")
      k__422__auto__
      (javax.crypto.spec.SecretKeySpec. key711 "HmacSHA384")
      c__423__auto__
      (clojure.core/int *buffer-size*)
      buf__424__auto__
      (clojure.core/byte-array c__423__auto__)
      s__425__auto__
      data710]
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
   (compute-sha384709
    [data710 key711]
    (clojure.core/with-open
     [data710
      (java.io.FileInputStream. (clojure.java.io/as-file data710))]
     (clojure.core/let
      [mac__421__auto__
       (javax.crypto.Mac/getInstance "HmacSHA384")
       k__422__auto__
       (javax.crypto.spec.SecretKeySpec. key711 "HmacSHA384")
       c__423__auto__
       (clojure.core/int *buffer-size*)
       buf__424__auto__
       (clojure.core/byte-array c__423__auto__)
       s__425__auto__
       data710]
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
   sha384-hmac*
   "[HMAC] HmacSHA384 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha384709
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha384-hmac-file*
   "[HMAC] HmacSHA384 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha384709
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha384-hmac-file-bytes
   "[HMAC] HmacSHA384 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha384709
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha384-hmac-file
   "[HMAC] HmacSHA384 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha384709
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   sha384-hmac-bytes
   "[HMAC] HmacSHA384 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha384709
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha384-hmac
   "[HMAC] HmacSHA384 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha384709
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
