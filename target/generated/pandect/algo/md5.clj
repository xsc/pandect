(clojure.core/ns
 pandect.algo.md5
 (:require
  [pandect.utils [buffer :refer [*buffer-size*]] [convert]]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__627
   (compute-md5625 [this__228__auto__]))
  (clojure.core/extend-protocol
   G__627
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md5625
    [data626]
    (clojure.core/let
     [md__429__auto__ (java.security.MessageDigest/getInstance "MD5")]
     (.digest md__429__auto__ data626)))
   java.lang.String
   (compute-md5625
    [data626]
    (clojure.core/let
     [md__429__auto__ (java.security.MessageDigest/getInstance "MD5")]
     (.digest md__429__auto__ (.getBytes data626))))
   java.io.InputStream
   (compute-md5625
    [data626]
    (clojure.core/let
     [md__430__auto__
      (java.security.MessageDigest/getInstance "MD5")
      c__431__auto__
      (clojure.core/int *buffer-size*)
      buf__432__auto__
      (clojure.core/byte-array c__431__auto__)
      s__433__auto__
      data626]
     (clojure.core/loop
      []
      (clojure.core/let
       [r__434__auto__
        (.read s__433__auto__ buf__432__auto__ 0 c__431__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__434__auto__ -1)
        (.update md__430__auto__ buf__432__auto__ 0 r__434__auto__)
        (recur))))
     (.digest md__430__auto__)))
   java.io.File
   (compute-md5625
    [data626]
    (clojure.core/with-open
     [data626
      (java.io.FileInputStream. (clojure.java.io/as-file data626))]
     (clojure.core/let
      [md__430__auto__
       (java.security.MessageDigest/getInstance "MD5")
       c__431__auto__
       (clojure.core/int *buffer-size*)
       buf__432__auto__
       (clojure.core/byte-array c__431__auto__)
       s__433__auto__
       data626]
      (clojure.core/loop
       []
       (clojure.core/let
        [r__434__auto__
         (.read s__433__auto__ buf__432__auto__ 0 c__431__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__434__auto__ -1)
         (.update md__430__auto__ buf__432__auto__ 0 r__434__auto__)
         (recur))))
      (.digest md__430__auto__))))))
 [(clojure.core/defn
   md5*
   "[Hash] MD5 (raw value)"
   [x]
   (compute-md5625 x))
  (clojure.core/defn
   md5-file*
   "[Hash] MD5 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-md5625 x)))
  (clojure.core/defn
   md5-file-bytes
   "[Hash] MD5 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-md5625 x)))
  (clojure.core/defn
   md5-file
   "[Hash] MD5 (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-md5625 x))))
  (clojure.core/defn
   md5-bytes
   "[Hash] MD5 (value -> byte array)"
   [x]
   (compute-md5625 x))
  (clojure.core/defn
   md5
   "[Hash] MD5 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-md5625 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__631
   (compute-md5628 [this__382__auto__ key__383__auto__]))
  (clojure.core/extend-protocol
   G__631
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md5628
    [data629 key630]
    (clojure.core/let
     [mac__415__auto__
      (javax.crypto.Mac/getInstance "HmacMD5")
      msg__416__auto__
      (clojure.core/bytes data629)
      k__417__auto__
      (javax.crypto.spec.SecretKeySpec. key630 "HmacMD5")]
     (clojure.core/->
      (clojure.core/doto
       mac__415__auto__
       (.init k__417__auto__)
       (.update msg__416__auto__))
      (.doFinal))))
   java.lang.String
   (compute-md5628
    [data629 key630]
    (clojure.core/let
     [mac__415__auto__
      (javax.crypto.Mac/getInstance "HmacMD5")
      msg__416__auto__
      (clojure.core/bytes (.getBytes data629))
      k__417__auto__
      (javax.crypto.spec.SecretKeySpec. key630 "HmacMD5")]
     (clojure.core/->
      (clojure.core/doto
       mac__415__auto__
       (.init k__417__auto__)
       (.update msg__416__auto__))
      (.doFinal))))
   java.io.InputStream
   (compute-md5628
    [data629 key630]
    (clojure.core/let
     [mac__418__auto__
      (javax.crypto.Mac/getInstance "HmacMD5")
      k__419__auto__
      (javax.crypto.spec.SecretKeySpec. key630 "HmacMD5")
      c__420__auto__
      (clojure.core/int *buffer-size*)
      buf__421__auto__
      (clojure.core/byte-array c__420__auto__)
      s__422__auto__
      data629]
     (.init mac__418__auto__ k__419__auto__)
     (clojure.core/loop
      []
      (clojure.core/let
       [r__423__auto__
        (.read s__422__auto__ buf__421__auto__ 0 c__420__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__423__auto__ -1)
        (.update mac__418__auto__ buf__421__auto__ 0 r__423__auto__)
        (recur))))
     (.doFinal mac__418__auto__)))
   java.io.File
   (compute-md5628
    [data629 key630]
    (clojure.core/with-open
     [data629
      (java.io.FileInputStream. (clojure.java.io/as-file data629))]
     (clojure.core/let
      [mac__418__auto__
       (javax.crypto.Mac/getInstance "HmacMD5")
       k__419__auto__
       (javax.crypto.spec.SecretKeySpec. key630 "HmacMD5")
       c__420__auto__
       (clojure.core/int *buffer-size*)
       buf__421__auto__
       (clojure.core/byte-array c__420__auto__)
       s__422__auto__
       data629]
      (.init mac__418__auto__ k__419__auto__)
      (clojure.core/loop
       []
       (clojure.core/let
        [r__423__auto__
         (.read s__422__auto__ buf__421__auto__ 0 c__420__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__423__auto__ -1)
         (.update mac__418__auto__ buf__421__auto__ 0 r__423__auto__)
         (recur))))
      (.doFinal mac__418__auto__))))))
 [(clojure.core/defn
   md5-hmac*
   "[HMAC] HmacMD5 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-md5628
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   md5-hmac-file*
   "[HMAC] HmacMD5 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-md5628
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   md5-hmac-file-bytes
   "[HMAC] HmacMD5 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-md5628
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   md5-hmac-file
   "[HMAC] HmacMD5 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-md5628
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   md5-hmac-bytes
   "[HMAC] HmacMD5 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-md5628
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   md5-hmac
   "[HMAC] HmacMD5 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-md5628
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
