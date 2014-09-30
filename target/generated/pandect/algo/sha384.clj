(clojure.core/ns
 pandect.algo.sha384
 (:require
  [pandect.utils [buffer :refer [*buffer-size*]] [convert]]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__701
   (compute-sha384699 [this__228__auto__]))
  (clojure.core/extend-protocol
   G__701
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha384699
    [data700]
    (clojure.core/let
     [md__429__auto__
      (java.security.MessageDigest/getInstance "SHA-384")]
     (.digest md__429__auto__ data700)))
   java.lang.String
   (compute-sha384699
    [data700]
    (clojure.core/let
     [md__429__auto__
      (java.security.MessageDigest/getInstance "SHA-384")]
     (.digest md__429__auto__ (.getBytes data700))))
   java.io.InputStream
   (compute-sha384699
    [data700]
    (clojure.core/let
     [md__430__auto__
      (java.security.MessageDigest/getInstance "SHA-384")
      c__431__auto__
      (clojure.core/int *buffer-size*)
      buf__432__auto__
      (clojure.core/byte-array c__431__auto__)
      s__433__auto__
      data700]
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
   (compute-sha384699
    [data700]
    (clojure.core/with-open
     [data700
      (java.io.FileInputStream. (clojure.java.io/as-file data700))]
     (clojure.core/let
      [md__430__auto__
       (java.security.MessageDigest/getInstance "SHA-384")
       c__431__auto__
       (clojure.core/int *buffer-size*)
       buf__432__auto__
       (clojure.core/byte-array c__431__auto__)
       s__433__auto__
       data700]
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
   sha384*
   "[Hash] SHA-384 (raw value)"
   [x]
   (compute-sha384699 x))
  (clojure.core/defn
   sha384-file*
   "[Hash] SHA-384 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha384699 x)))
  (clojure.core/defn
   sha384-file-bytes
   "[Hash] SHA-384 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha384699 x)))
  (clojure.core/defn
   sha384-file
   "[Hash] SHA-384 (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha384699 x))))
  (clojure.core/defn
   sha384-bytes
   "[Hash] SHA-384 (value -> byte array)"
   [x]
   (compute-sha384699 x))
  (clojure.core/defn
   sha384
   "[Hash] SHA-384 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha384699 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__705
   (compute-sha384702 [this__382__auto__ key__383__auto__]))
  (clojure.core/extend-protocol
   G__705
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha384702
    [data703 key704]
    (clojure.core/let
     [mac__415__auto__
      (javax.crypto.Mac/getInstance "HmacSHA384")
      msg__416__auto__
      (clojure.core/bytes data703)
      k__417__auto__
      (javax.crypto.spec.SecretKeySpec. key704 "HmacSHA384")]
     (clojure.core/->
      (clojure.core/doto
       mac__415__auto__
       (.init k__417__auto__)
       (.update msg__416__auto__))
      (.doFinal))))
   java.lang.String
   (compute-sha384702
    [data703 key704]
    (clojure.core/let
     [mac__415__auto__
      (javax.crypto.Mac/getInstance "HmacSHA384")
      msg__416__auto__
      (clojure.core/bytes (.getBytes data703))
      k__417__auto__
      (javax.crypto.spec.SecretKeySpec. key704 "HmacSHA384")]
     (clojure.core/->
      (clojure.core/doto
       mac__415__auto__
       (.init k__417__auto__)
       (.update msg__416__auto__))
      (.doFinal))))
   java.io.InputStream
   (compute-sha384702
    [data703 key704]
    (clojure.core/let
     [mac__418__auto__
      (javax.crypto.Mac/getInstance "HmacSHA384")
      k__419__auto__
      (javax.crypto.spec.SecretKeySpec. key704 "HmacSHA384")
      c__420__auto__
      (clojure.core/int *buffer-size*)
      buf__421__auto__
      (clojure.core/byte-array c__420__auto__)
      s__422__auto__
      data703]
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
   (compute-sha384702
    [data703 key704]
    (clojure.core/with-open
     [data703
      (java.io.FileInputStream. (clojure.java.io/as-file data703))]
     (clojure.core/let
      [mac__418__auto__
       (javax.crypto.Mac/getInstance "HmacSHA384")
       k__419__auto__
       (javax.crypto.spec.SecretKeySpec. key704 "HmacSHA384")
       c__420__auto__
       (clojure.core/int *buffer-size*)
       buf__421__auto__
       (clojure.core/byte-array c__420__auto__)
       s__422__auto__
       data703]
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
   sha384-hmac*
   "[HMAC] HmacSHA384 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha384702
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha384-hmac-file*
   "[HMAC] HmacSHA384 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha384702
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha384-hmac-file-bytes
   "[HMAC] HmacSHA384 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha384702
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha384-hmac-file
   "[HMAC] HmacSHA384 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha384702
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   sha384-hmac-bytes
   "[HMAC] HmacSHA384 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha384702
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha384-hmac
   "[HMAC] HmacSHA384 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha384702
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
