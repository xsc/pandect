(ns
 pandect.algo.sha256
 "SHA-256 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__739
   (compute-sha256737 [this__228__auto__]))
  (clojure.core/doseq
   [v__229__auto__ [#'G__739 #'compute-sha256737]]
   (clojure.core/alter-meta!
    v__229__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__739
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha256737
    [data738]
    (clojure.core/let
     [md__432__auto__
      (java.security.MessageDigest/getInstance "SHA-256")]
     (.digest md__432__auto__ data738)))
   java.lang.String
   (compute-sha256737
    [data738]
    (clojure.core/let
     [md__432__auto__
      (java.security.MessageDigest/getInstance "SHA-256")]
     (.digest md__432__auto__ (.getBytes data738 "UTF-8"))))
   java.io.InputStream
   (compute-sha256737
    [data738]
    (clojure.core/let
     [md__433__auto__
      (java.security.MessageDigest/getInstance "SHA-256")
      c__434__auto__
      (clojure.core/int *buffer-size*)
      buf__435__auto__
      (clojure.core/byte-array c__434__auto__)
      s__436__auto__
      data738]
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
   (compute-sha256737
    [data738]
    (clojure.core/with-open
     [data738
      (java.io.FileInputStream. (clojure.java.io/as-file data738))]
     (clojure.core/let
      [md__433__auto__
       (java.security.MessageDigest/getInstance "SHA-256")
       c__434__auto__
       (clojure.core/int *buffer-size*)
       buf__435__auto__
       (clojure.core/byte-array c__434__auto__)
       s__436__auto__
       data738]
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
   sha256*
   "[Hash] SHA-256 (raw value)"
   [x]
   (compute-sha256737 x))
  (clojure.core/defn
   sha256-file*
   "[Hash] SHA-256 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha256737 x)))
  (clojure.core/defn
   sha256-file-bytes
   "[Hash] SHA-256 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha256737 x)))
  (clojure.core/defn
   sha256-file
   "[Hash] SHA-256 (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha256737 x))))
  (clojure.core/defn
   sha256-bytes
   "[Hash] SHA-256 (value -> byte array)"
   [x]
   (compute-sha256737 x))
  (clojure.core/defn
   sha256
   "[Hash] SHA-256 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha256737 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__743
   (compute-sha256740 [this__404__auto__ key__405__auto__]))
  (clojure.core/doseq
   [v__406__auto__ [#'G__743 #'compute-sha256740]]
   (clojure.core/alter-meta!
    v__406__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__743
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha256740
    [data741 key742]
    (clojure.core/let
     [mac__418__auto__
      (javax.crypto.Mac/getInstance "HmacSHA256")
      msg__419__auto__
      (clojure.core/bytes data741)
      k__420__auto__
      (javax.crypto.spec.SecretKeySpec. key742 "HmacSHA256")]
     (clojure.core/->
      (clojure.core/doto
       mac__418__auto__
       (.init k__420__auto__)
       (.update msg__419__auto__))
      (.doFinal))))
   java.lang.String
   (compute-sha256740
    [data741 key742]
    (clojure.core/let
     [mac__418__auto__
      (javax.crypto.Mac/getInstance "HmacSHA256")
      msg__419__auto__
      (clojure.core/bytes (.getBytes data741 "UTF-8"))
      k__420__auto__
      (javax.crypto.spec.SecretKeySpec. key742 "HmacSHA256")]
     (clojure.core/->
      (clojure.core/doto
       mac__418__auto__
       (.init k__420__auto__)
       (.update msg__419__auto__))
      (.doFinal))))
   java.io.InputStream
   (compute-sha256740
    [data741 key742]
    (clojure.core/let
     [mac__421__auto__
      (javax.crypto.Mac/getInstance "HmacSHA256")
      k__422__auto__
      (javax.crypto.spec.SecretKeySpec. key742 "HmacSHA256")
      c__423__auto__
      (clojure.core/int *buffer-size*)
      buf__424__auto__
      (clojure.core/byte-array c__423__auto__)
      s__425__auto__
      data741]
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
   (compute-sha256740
    [data741 key742]
    (clojure.core/with-open
     [data741
      (java.io.FileInputStream. (clojure.java.io/as-file data741))]
     (clojure.core/let
      [mac__421__auto__
       (javax.crypto.Mac/getInstance "HmacSHA256")
       k__422__auto__
       (javax.crypto.spec.SecretKeySpec. key742 "HmacSHA256")
       c__423__auto__
       (clojure.core/int *buffer-size*)
       buf__424__auto__
       (clojure.core/byte-array c__423__auto__)
       s__425__auto__
       data741]
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
   sha256-hmac*
   "[HMAC] HmacSHA256 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha256740
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha256-hmac-file*
   "[HMAC] HmacSHA256 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha256740
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha256-hmac-file-bytes
   "[HMAC] HmacSHA256 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha256740
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha256-hmac-file
   "[HMAC] HmacSHA256 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha256740
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   sha256-hmac-bytes
   "[HMAC] HmacSHA256 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha256740
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha256-hmac
   "[HMAC] HmacSHA256 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha256740
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
