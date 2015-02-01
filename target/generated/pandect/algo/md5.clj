(ns
 pandect.algo.md5
 "MD5 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__634
   (compute-md5632 [this__228__auto__]))
  (clojure.core/doseq
   [v__229__auto__ [#'G__634 #'compute-md5632]]
   (clojure.core/alter-meta!
    v__229__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__634
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md5632
    [data633]
    (clojure.core/let
     [md__432__auto__ (java.security.MessageDigest/getInstance "MD5")]
     (.digest md__432__auto__ data633)))
   java.lang.String
   (compute-md5632
    [data633]
    (clojure.core/let
     [md__432__auto__ (java.security.MessageDigest/getInstance "MD5")]
     (.digest md__432__auto__ (.getBytes data633 "UTF-8"))))
   java.io.InputStream
   (compute-md5632
    [data633]
    (clojure.core/let
     [md__433__auto__
      (java.security.MessageDigest/getInstance "MD5")
      c__434__auto__
      (clojure.core/int *buffer-size*)
      buf__435__auto__
      (clojure.core/byte-array c__434__auto__)
      s__436__auto__
      data633]
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
   (compute-md5632
    [data633]
    (clojure.core/with-open
     [data633
      (java.io.FileInputStream. (clojure.java.io/as-file data633))]
     (clojure.core/let
      [md__433__auto__
       (java.security.MessageDigest/getInstance "MD5")
       c__434__auto__
       (clojure.core/int *buffer-size*)
       buf__435__auto__
       (clojure.core/byte-array c__434__auto__)
       s__436__auto__
       data633]
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
   md5*
   "[Hash] MD5 (raw value)"
   [x]
   (compute-md5632 x))
  (clojure.core/defn
   md5-file*
   "[Hash] MD5 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-md5632 x)))
  (clojure.core/defn
   md5-file-bytes
   "[Hash] MD5 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-md5632 x)))
  (clojure.core/defn
   md5-file
   "[Hash] MD5 (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-md5632 x))))
  (clojure.core/defn
   md5-bytes
   "[Hash] MD5 (value -> byte array)"
   [x]
   (compute-md5632 x))
  (clojure.core/defn
   md5
   "[Hash] MD5 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-md5632 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__638
   (compute-md5635 [this__404__auto__ key__405__auto__]))
  (clojure.core/doseq
   [v__406__auto__ [#'G__638 #'compute-md5635]]
   (clojure.core/alter-meta!
    v__406__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__638
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md5635
    [data636 key637]
    (clojure.core/let
     [mac__418__auto__
      (javax.crypto.Mac/getInstance "HmacMD5")
      msg__419__auto__
      (clojure.core/bytes data636)
      k__420__auto__
      (javax.crypto.spec.SecretKeySpec. key637 "HmacMD5")]
     (clojure.core/->
      (clojure.core/doto
       mac__418__auto__
       (.init k__420__auto__)
       (.update msg__419__auto__))
      (.doFinal))))
   java.lang.String
   (compute-md5635
    [data636 key637]
    (clojure.core/let
     [mac__418__auto__
      (javax.crypto.Mac/getInstance "HmacMD5")
      msg__419__auto__
      (clojure.core/bytes (.getBytes data636 "UTF-8"))
      k__420__auto__
      (javax.crypto.spec.SecretKeySpec. key637 "HmacMD5")]
     (clojure.core/->
      (clojure.core/doto
       mac__418__auto__
       (.init k__420__auto__)
       (.update msg__419__auto__))
      (.doFinal))))
   java.io.InputStream
   (compute-md5635
    [data636 key637]
    (clojure.core/let
     [mac__421__auto__
      (javax.crypto.Mac/getInstance "HmacMD5")
      k__422__auto__
      (javax.crypto.spec.SecretKeySpec. key637 "HmacMD5")
      c__423__auto__
      (clojure.core/int *buffer-size*)
      buf__424__auto__
      (clojure.core/byte-array c__423__auto__)
      s__425__auto__
      data636]
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
   (compute-md5635
    [data636 key637]
    (clojure.core/with-open
     [data636
      (java.io.FileInputStream. (clojure.java.io/as-file data636))]
     (clojure.core/let
      [mac__421__auto__
       (javax.crypto.Mac/getInstance "HmacMD5")
       k__422__auto__
       (javax.crypto.spec.SecretKeySpec. key637 "HmacMD5")
       c__423__auto__
       (clojure.core/int *buffer-size*)
       buf__424__auto__
       (clojure.core/byte-array c__423__auto__)
       s__425__auto__
       data636]
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
   md5-hmac*
   "[HMAC] HmacMD5 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-md5635
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   md5-hmac-file*
   "[HMAC] HmacMD5 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-md5635
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   md5-hmac-file-bytes
   "[HMAC] HmacMD5 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-md5635
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   md5-hmac-file
   "[HMAC] HmacMD5 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-md5635
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   md5-hmac-bytes
   "[HMAC] HmacMD5 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-md5635
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   md5-hmac
   "[HMAC] HmacMD5 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-md5635
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
