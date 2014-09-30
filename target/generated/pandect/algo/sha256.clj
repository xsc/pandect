(clojure.core/ns
 pandect.algo.sha256
 (:require
  [pandect.utils [buffer :refer [*buffer-size*]] [convert]]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__732
   (compute-sha256730 [this__228__auto__]))
  (clojure.core/extend-protocol
   G__732
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha256730
    [data731]
    (clojure.core/let
     [md__429__auto__
      (java.security.MessageDigest/getInstance "SHA-256")]
     (.digest md__429__auto__ data731)))
   java.lang.String
   (compute-sha256730
    [data731]
    (clojure.core/let
     [md__429__auto__
      (java.security.MessageDigest/getInstance "SHA-256")]
     (.digest md__429__auto__ (.getBytes data731))))
   java.io.InputStream
   (compute-sha256730
    [data731]
    (clojure.core/let
     [md__430__auto__
      (java.security.MessageDigest/getInstance "SHA-256")
      c__431__auto__
      (clojure.core/int *buffer-size*)
      buf__432__auto__
      (clojure.core/byte-array c__431__auto__)
      s__433__auto__
      data731]
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
   (compute-sha256730
    [data731]
    (clojure.core/with-open
     [data731
      (java.io.FileInputStream. (clojure.java.io/as-file data731))]
     (clojure.core/let
      [md__430__auto__
       (java.security.MessageDigest/getInstance "SHA-256")
       c__431__auto__
       (clojure.core/int *buffer-size*)
       buf__432__auto__
       (clojure.core/byte-array c__431__auto__)
       s__433__auto__
       data731]
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
   sha256*
   "[Hash] SHA-256 (raw value)"
   [x]
   (compute-sha256730 x))
  (clojure.core/defn
   sha256-file*
   "[Hash] SHA-256 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha256730 x)))
  (clojure.core/defn
   sha256-file-bytes
   "[Hash] SHA-256 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha256730 x)))
  (clojure.core/defn
   sha256-file
   "[Hash] SHA-256 (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha256730 x))))
  (clojure.core/defn
   sha256-bytes
   "[Hash] SHA-256 (value -> byte array)"
   [x]
   (compute-sha256730 x))
  (clojure.core/defn
   sha256
   "[Hash] SHA-256 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha256730 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__736
   (compute-sha256733 [this__382__auto__ key__383__auto__]))
  (clojure.core/extend-protocol
   G__736
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha256733
    [data734 key735]
    (clojure.core/let
     [mac__415__auto__
      (javax.crypto.Mac/getInstance "HmacSHA256")
      msg__416__auto__
      (clojure.core/bytes data734)
      k__417__auto__
      (javax.crypto.spec.SecretKeySpec. key735 "HmacSHA256")]
     (clojure.core/->
      (clojure.core/doto
       mac__415__auto__
       (.init k__417__auto__)
       (.update msg__416__auto__))
      (.doFinal))))
   java.lang.String
   (compute-sha256733
    [data734 key735]
    (clojure.core/let
     [mac__415__auto__
      (javax.crypto.Mac/getInstance "HmacSHA256")
      msg__416__auto__
      (clojure.core/bytes (.getBytes data734))
      k__417__auto__
      (javax.crypto.spec.SecretKeySpec. key735 "HmacSHA256")]
     (clojure.core/->
      (clojure.core/doto
       mac__415__auto__
       (.init k__417__auto__)
       (.update msg__416__auto__))
      (.doFinal))))
   java.io.InputStream
   (compute-sha256733
    [data734 key735]
    (clojure.core/let
     [mac__418__auto__
      (javax.crypto.Mac/getInstance "HmacSHA256")
      k__419__auto__
      (javax.crypto.spec.SecretKeySpec. key735 "HmacSHA256")
      c__420__auto__
      (clojure.core/int *buffer-size*)
      buf__421__auto__
      (clojure.core/byte-array c__420__auto__)
      s__422__auto__
      data734]
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
   (compute-sha256733
    [data734 key735]
    (clojure.core/with-open
     [data734
      (java.io.FileInputStream. (clojure.java.io/as-file data734))]
     (clojure.core/let
      [mac__418__auto__
       (javax.crypto.Mac/getInstance "HmacSHA256")
       k__419__auto__
       (javax.crypto.spec.SecretKeySpec. key735 "HmacSHA256")
       c__420__auto__
       (clojure.core/int *buffer-size*)
       buf__421__auto__
       (clojure.core/byte-array c__420__auto__)
       s__422__auto__
       data734]
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
   sha256-hmac*
   "[HMAC] HmacSHA256 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha256733
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha256-hmac-file*
   "[HMAC] HmacSHA256 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha256733
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha256-hmac-file-bytes
   "[HMAC] HmacSHA256 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha256733
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha256-hmac-file
   "[HMAC] HmacSHA256 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha256733
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   sha256-hmac-bytes
   "[HMAC] HmacSHA256 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha256733
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha256-hmac
   "[HMAC] HmacSHA256 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha256733
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
