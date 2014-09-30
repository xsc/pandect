(clojure.core/ns
 pandect.algo.sha1
 (:require
  [pandect.utils [buffer :refer [*buffer-size*]] [convert]]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__687
   (compute-sha1685 [this__228__auto__]))
  (clojure.core/extend-protocol
   G__687
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha1685
    [data686]
    (clojure.core/let
     [md__429__auto__
      (java.security.MessageDigest/getInstance "SHA-1")]
     (.digest md__429__auto__ data686)))
   java.lang.String
   (compute-sha1685
    [data686]
    (clojure.core/let
     [md__429__auto__
      (java.security.MessageDigest/getInstance "SHA-1")]
     (.digest md__429__auto__ (.getBytes data686))))
   java.io.InputStream
   (compute-sha1685
    [data686]
    (clojure.core/let
     [md__430__auto__
      (java.security.MessageDigest/getInstance "SHA-1")
      c__431__auto__
      (clojure.core/int *buffer-size*)
      buf__432__auto__
      (clojure.core/byte-array c__431__auto__)
      s__433__auto__
      data686]
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
   (compute-sha1685
    [data686]
    (clojure.core/with-open
     [data686
      (java.io.FileInputStream. (clojure.java.io/as-file data686))]
     (clojure.core/let
      [md__430__auto__
       (java.security.MessageDigest/getInstance "SHA-1")
       c__431__auto__
       (clojure.core/int *buffer-size*)
       buf__432__auto__
       (clojure.core/byte-array c__431__auto__)
       s__433__auto__
       data686]
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
   sha1*
   "[Hash] SHA-1 (raw value)"
   [x]
   (compute-sha1685 x))
  (clojure.core/defn
   sha1-file*
   "[Hash] SHA-1 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha1685 x)))
  (clojure.core/defn
   sha1-file-bytes
   "[Hash] SHA-1 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha1685 x)))
  (clojure.core/defn
   sha1-file
   "[Hash] SHA-1 (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha1685 x))))
  (clojure.core/defn
   sha1-bytes
   "[Hash] SHA-1 (value -> byte array)"
   [x]
   (compute-sha1685 x))
  (clojure.core/defn
   sha1
   "[Hash] SHA-1 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha1685 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__691
   (compute-sha1688 [this__382__auto__ key__383__auto__]))
  (clojure.core/extend-protocol
   G__691
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha1688
    [data689 key690]
    (clojure.core/let
     [mac__415__auto__
      (javax.crypto.Mac/getInstance "HmacSHA1")
      msg__416__auto__
      (clojure.core/bytes data689)
      k__417__auto__
      (javax.crypto.spec.SecretKeySpec. key690 "HmacSHA1")]
     (clojure.core/->
      (clojure.core/doto
       mac__415__auto__
       (.init k__417__auto__)
       (.update msg__416__auto__))
      (.doFinal))))
   java.lang.String
   (compute-sha1688
    [data689 key690]
    (clojure.core/let
     [mac__415__auto__
      (javax.crypto.Mac/getInstance "HmacSHA1")
      msg__416__auto__
      (clojure.core/bytes (.getBytes data689))
      k__417__auto__
      (javax.crypto.spec.SecretKeySpec. key690 "HmacSHA1")]
     (clojure.core/->
      (clojure.core/doto
       mac__415__auto__
       (.init k__417__auto__)
       (.update msg__416__auto__))
      (.doFinal))))
   java.io.InputStream
   (compute-sha1688
    [data689 key690]
    (clojure.core/let
     [mac__418__auto__
      (javax.crypto.Mac/getInstance "HmacSHA1")
      k__419__auto__
      (javax.crypto.spec.SecretKeySpec. key690 "HmacSHA1")
      c__420__auto__
      (clojure.core/int *buffer-size*)
      buf__421__auto__
      (clojure.core/byte-array c__420__auto__)
      s__422__auto__
      data689]
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
   (compute-sha1688
    [data689 key690]
    (clojure.core/with-open
     [data689
      (java.io.FileInputStream. (clojure.java.io/as-file data689))]
     (clojure.core/let
      [mac__418__auto__
       (javax.crypto.Mac/getInstance "HmacSHA1")
       k__419__auto__
       (javax.crypto.spec.SecretKeySpec. key690 "HmacSHA1")
       c__420__auto__
       (clojure.core/int *buffer-size*)
       buf__421__auto__
       (clojure.core/byte-array c__420__auto__)
       s__422__auto__
       data689]
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
   sha1-hmac*
   "[HMAC] HmacSHA1 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha1688
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha1-hmac-file*
   "[HMAC] HmacSHA1 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha1688
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha1-hmac-file-bytes
   "[HMAC] HmacSHA1 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha1688
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha1-hmac-file
   "[HMAC] HmacSHA1 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha1688
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   sha1-hmac-bytes
   "[HMAC] HmacSHA1 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha1688
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha1-hmac
   "[HMAC] HmacSHA1 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha1688
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
