(clojure.core/ns
 pandect.algo.sha512
 (:require
  [pandect.utils [buffer :refer [*buffer-size*]] [convert]]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__715
   (compute-sha512713 [this__228__auto__]))
  (clojure.core/extend-protocol
   G__715
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha512713
    [data714]
    (clojure.core/let
     [md__429__auto__
      (java.security.MessageDigest/getInstance "SHA-512")]
     (.digest md__429__auto__ data714)))
   java.lang.String
   (compute-sha512713
    [data714]
    (clojure.core/let
     [md__429__auto__
      (java.security.MessageDigest/getInstance "SHA-512")]
     (.digest md__429__auto__ (.getBytes data714))))
   java.io.InputStream
   (compute-sha512713
    [data714]
    (clojure.core/let
     [md__430__auto__
      (java.security.MessageDigest/getInstance "SHA-512")
      c__431__auto__
      (clojure.core/int *buffer-size*)
      buf__432__auto__
      (clojure.core/byte-array c__431__auto__)
      s__433__auto__
      data714]
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
   (compute-sha512713
    [data714]
    (clojure.core/with-open
     [data714
      (java.io.FileInputStream. (clojure.java.io/as-file data714))]
     (clojure.core/let
      [md__430__auto__
       (java.security.MessageDigest/getInstance "SHA-512")
       c__431__auto__
       (clojure.core/int *buffer-size*)
       buf__432__auto__
       (clojure.core/byte-array c__431__auto__)
       s__433__auto__
       data714]
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
   sha512*
   "[Hash] SHA-512 (raw value)"
   [x]
   (compute-sha512713 x))
  (clojure.core/defn
   sha512-file*
   "[Hash] SHA-512 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha512713 x)))
  (clojure.core/defn
   sha512-file-bytes
   "[Hash] SHA-512 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha512713 x)))
  (clojure.core/defn
   sha512-file
   "[Hash] SHA-512 (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha512713 x))))
  (clojure.core/defn
   sha512-bytes
   "[Hash] SHA-512 (value -> byte array)"
   [x]
   (compute-sha512713 x))
  (clojure.core/defn
   sha512
   "[Hash] SHA-512 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha512713 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__719
   (compute-sha512716 [this__382__auto__ key__383__auto__]))
  (clojure.core/extend-protocol
   G__719
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha512716
    [data717 key718]
    (clojure.core/let
     [mac__415__auto__
      (javax.crypto.Mac/getInstance "HmacSHA512")
      msg__416__auto__
      (clojure.core/bytes data717)
      k__417__auto__
      (javax.crypto.spec.SecretKeySpec. key718 "HmacSHA512")]
     (clojure.core/->
      (clojure.core/doto
       mac__415__auto__
       (.init k__417__auto__)
       (.update msg__416__auto__))
      (.doFinal))))
   java.lang.String
   (compute-sha512716
    [data717 key718]
    (clojure.core/let
     [mac__415__auto__
      (javax.crypto.Mac/getInstance "HmacSHA512")
      msg__416__auto__
      (clojure.core/bytes (.getBytes data717))
      k__417__auto__
      (javax.crypto.spec.SecretKeySpec. key718 "HmacSHA512")]
     (clojure.core/->
      (clojure.core/doto
       mac__415__auto__
       (.init k__417__auto__)
       (.update msg__416__auto__))
      (.doFinal))))
   java.io.InputStream
   (compute-sha512716
    [data717 key718]
    (clojure.core/let
     [mac__418__auto__
      (javax.crypto.Mac/getInstance "HmacSHA512")
      k__419__auto__
      (javax.crypto.spec.SecretKeySpec. key718 "HmacSHA512")
      c__420__auto__
      (clojure.core/int *buffer-size*)
      buf__421__auto__
      (clojure.core/byte-array c__420__auto__)
      s__422__auto__
      data717]
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
   (compute-sha512716
    [data717 key718]
    (clojure.core/with-open
     [data717
      (java.io.FileInputStream. (clojure.java.io/as-file data717))]
     (clojure.core/let
      [mac__418__auto__
       (javax.crypto.Mac/getInstance "HmacSHA512")
       k__419__auto__
       (javax.crypto.spec.SecretKeySpec. key718 "HmacSHA512")
       c__420__auto__
       (clojure.core/int *buffer-size*)
       buf__421__auto__
       (clojure.core/byte-array c__420__auto__)
       s__422__auto__
       data717]
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
   sha512-hmac*
   "[HMAC] HmacSHA512 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha512716
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha512-hmac-file*
   "[HMAC] HmacSHA512 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha512716
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha512-hmac-file-bytes
   "[HMAC] HmacSHA512 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha512716
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha512-hmac-file
   "[HMAC] HmacSHA512 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha512716
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   sha512-hmac-bytes
   "[HMAC] HmacSHA512 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha512716
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha512-hmac
   "[HMAC] HmacSHA512 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha512716
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
