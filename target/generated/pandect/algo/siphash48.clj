(clojure.core/ns
 pandect.algo.siphash48
 (:require
  [pandect.utils [buffer :refer [*buffer-size*]] [convert]]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__649
   (compute-siphash48646 [this__382__auto__ key__383__auto__]))
  (clojure.core/extend-protocol
   G__649
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-siphash48646
    [data647 key648]
    (clojure.core/let
     [buf__498__auto__
      data647
      k__499__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key648)
      mac__500__auto__
      (new org.bouncycastle.crypto.macs.SipHash 4 8)
      result__501__auto__
      (clojure.core/byte-array (.getMacSize mac__500__auto__))]
     (clojure.core/doto
      mac__500__auto__
      (.init k__499__auto__)
      (.update
       buf__498__auto__
       0
       (clojure.core/alength (clojure.core/bytes buf__498__auto__)))
      (.doFinal result__501__auto__ 0))
     result__501__auto__))
   java.lang.String
   (compute-siphash48646
    [data647 key648]
    (clojure.core/let
     [buf__498__auto__
      (.getBytes data647)
      k__499__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key648)
      mac__500__auto__
      (new org.bouncycastle.crypto.macs.SipHash 4 8)
      result__501__auto__
      (clojure.core/byte-array (.getMacSize mac__500__auto__))]
     (clojure.core/doto
      mac__500__auto__
      (.init k__499__auto__)
      (.update
       buf__498__auto__
       0
       (clojure.core/alength (clojure.core/bytes buf__498__auto__)))
      (.doFinal result__501__auto__ 0))
     result__501__auto__))
   java.io.InputStream
   (compute-siphash48646
    [data647 key648]
    (clojure.core/let
     [s__503__auto__
      data647
      c__504__auto__
      (clojure.core/int *buffer-size*)
      mac__505__auto__
      (new org.bouncycastle.crypto.macs.SipHash 4 8)
      k__506__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key648)
      buf__507__auto__
      (clojure.core/byte-array c__504__auto__)]
     (.init mac__505__auto__ k__506__auto__)
     (clojure.core/loop
      []
      (clojure.core/let
       [r__508__auto__
        (.read s__503__auto__ buf__507__auto__ 0 c__504__auto__)]
       (clojure.core/when
        (clojure.core/not= r__508__auto__ -1)
        (.update mac__505__auto__ buf__507__auto__ 0 r__508__auto__)
        (recur))))
     (clojure.core/let
      [result__509__auto__
       (clojure.core/byte-array (.getMacSize mac__505__auto__))]
      (.doFinal mac__505__auto__ result__509__auto__ 0)
      result__509__auto__)))
   java.io.File
   (compute-siphash48646
    [data647 key648]
    (clojure.core/with-open
     [data647
      (java.io.FileInputStream. (clojure.java.io/as-file data647))]
     (clojure.core/let
      [s__503__auto__
       data647
       c__504__auto__
       (clojure.core/int *buffer-size*)
       mac__505__auto__
       (new org.bouncycastle.crypto.macs.SipHash 4 8)
       k__506__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key648)
       buf__507__auto__
       (clojure.core/byte-array c__504__auto__)]
      (.init mac__505__auto__ k__506__auto__)
      (clojure.core/loop
       []
       (clojure.core/let
        [r__508__auto__
         (.read s__503__auto__ buf__507__auto__ 0 c__504__auto__)]
        (clojure.core/when
         (clojure.core/not= r__508__auto__ -1)
         (.update mac__505__auto__ buf__507__auto__ 0 r__508__auto__)
         (recur))))
      (clojure.core/let
       [result__509__auto__
        (clojure.core/byte-array (.getMacSize mac__505__auto__))]
       (.doFinal mac__505__auto__ result__509__auto__ 0)
       result__509__auto__))))))
 [(clojure.core/defn
   siphash48*
   "[HMAC] SipHash-4-8 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-siphash48646
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   siphash48-file*
   "[HMAC] SipHash-4-8 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-siphash48646
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   siphash48-file-bytes
   "[HMAC] SipHash-4-8 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-siphash48646
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   siphash48-file
   "[HMAC] SipHash-4-8 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-siphash48646
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   siphash48-bytes
   "[HMAC] SipHash-4-8 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-siphash48646
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   siphash48
   "[HMAC] SipHash-4-8 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-siphash48646
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
