(ns
 pandect.algo.sha512
 "SHA-512 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__722
   (compute-sha512720 [this__228__auto__]))
  (clojure.core/doseq
   [v__229__auto__ [#'G__722 #'compute-sha512720]]
   (clojure.core/alter-meta!
    v__229__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__722
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha512720
    [data721]
    (clojure.core/let
     [md__432__auto__
      (java.security.MessageDigest/getInstance "SHA-512")]
     (.digest md__432__auto__ data721)))
   java.lang.String
   (compute-sha512720
    [data721]
    (clojure.core/let
     [md__432__auto__
      (java.security.MessageDigest/getInstance "SHA-512")]
     (.digest md__432__auto__ (.getBytes data721 "UTF-8"))))
   java.io.InputStream
   (compute-sha512720
    [data721]
    (clojure.core/let
     [md__433__auto__
      (java.security.MessageDigest/getInstance "SHA-512")
      c__434__auto__
      (clojure.core/int *buffer-size*)
      buf__435__auto__
      (clojure.core/byte-array c__434__auto__)
      s__436__auto__
      data721]
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
   (compute-sha512720
    [data721]
    (clojure.core/with-open
     [data721
      (java.io.FileInputStream. (clojure.java.io/as-file data721))]
     (clojure.core/let
      [md__433__auto__
       (java.security.MessageDigest/getInstance "SHA-512")
       c__434__auto__
       (clojure.core/int *buffer-size*)
       buf__435__auto__
       (clojure.core/byte-array c__434__auto__)
       s__436__auto__
       data721]
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
   sha512*
   "[Hash] SHA-512 (raw value)"
   [x]
   (compute-sha512720 x))
  (clojure.core/defn
   sha512-file*
   "[Hash] SHA-512 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha512720 x)))
  (clojure.core/defn
   sha512-file-bytes
   "[Hash] SHA-512 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha512720 x)))
  (clojure.core/defn
   sha512-file
   "[Hash] SHA-512 (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha512720 x))))
  (clojure.core/defn
   sha512-bytes
   "[Hash] SHA-512 (value -> byte array)"
   [x]
   (compute-sha512720 x))
  (clojure.core/defn
   sha512
   "[Hash] SHA-512 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha512720 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__726
   (compute-sha512723 [this__404__auto__ key__405__auto__]))
  (clojure.core/doseq
   [v__406__auto__ [#'G__726 #'compute-sha512723]]
   (clojure.core/alter-meta!
    v__406__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__726
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha512723
    [data724 key725]
    (clojure.core/let
     [mac__418__auto__
      (javax.crypto.Mac/getInstance "HmacSHA512")
      msg__419__auto__
      (clojure.core/bytes data724)
      k__420__auto__
      (javax.crypto.spec.SecretKeySpec. key725 "HmacSHA512")]
     (clojure.core/->
      (clojure.core/doto
       mac__418__auto__
       (.init k__420__auto__)
       (.update msg__419__auto__))
      (.doFinal))))
   java.lang.String
   (compute-sha512723
    [data724 key725]
    (clojure.core/let
     [mac__418__auto__
      (javax.crypto.Mac/getInstance "HmacSHA512")
      msg__419__auto__
      (clojure.core/bytes (.getBytes data724 "UTF-8"))
      k__420__auto__
      (javax.crypto.spec.SecretKeySpec. key725 "HmacSHA512")]
     (clojure.core/->
      (clojure.core/doto
       mac__418__auto__
       (.init k__420__auto__)
       (.update msg__419__auto__))
      (.doFinal))))
   java.io.InputStream
   (compute-sha512723
    [data724 key725]
    (clojure.core/let
     [mac__421__auto__
      (javax.crypto.Mac/getInstance "HmacSHA512")
      k__422__auto__
      (javax.crypto.spec.SecretKeySpec. key725 "HmacSHA512")
      c__423__auto__
      (clojure.core/int *buffer-size*)
      buf__424__auto__
      (clojure.core/byte-array c__423__auto__)
      s__425__auto__
      data724]
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
   (compute-sha512723
    [data724 key725]
    (clojure.core/with-open
     [data724
      (java.io.FileInputStream. (clojure.java.io/as-file data724))]
     (clojure.core/let
      [mac__421__auto__
       (javax.crypto.Mac/getInstance "HmacSHA512")
       k__422__auto__
       (javax.crypto.spec.SecretKeySpec. key725 "HmacSHA512")
       c__423__auto__
       (clojure.core/int *buffer-size*)
       buf__424__auto__
       (clojure.core/byte-array c__423__auto__)
       s__425__auto__
       data724]
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
   sha512-hmac*
   "[HMAC] HmacSHA512 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha512723
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha512-hmac-file*
   "[HMAC] HmacSHA512 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha512723
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha512-hmac-file-bytes
   "[HMAC] HmacSHA512 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha512723
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha512-hmac-file
   "[HMAC] HmacSHA512 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha512723
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   sha512-hmac-bytes
   "[HMAC] HmacSHA512 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha512723
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha512-hmac
   "[HMAC] HmacSHA512 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha512723
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
