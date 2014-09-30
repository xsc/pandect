(clojure.core/ns
 pandect.algo.siphash
 (:require
  [pandect.utils [buffer :refer [*buffer-size*]] [convert]]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__684
   (compute-siphash681 [this__382__auto__ key__383__auto__]))
  (clojure.core/extend-protocol
   G__684
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-siphash681
    [data682 key683]
    (clojure.core/let
     [buf__498__auto__
      data682
      k__499__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key683)
      mac__500__auto__
      (new org.bouncycastle.crypto.macs.SipHash)
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
   (compute-siphash681
    [data682 key683]
    (clojure.core/let
     [buf__498__auto__
      (.getBytes data682)
      k__499__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key683)
      mac__500__auto__
      (new org.bouncycastle.crypto.macs.SipHash)
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
   (compute-siphash681
    [data682 key683]
    (clojure.core/let
     [s__503__auto__
      data682
      c__504__auto__
      (clojure.core/int *buffer-size*)
      mac__505__auto__
      (new org.bouncycastle.crypto.macs.SipHash)
      k__506__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key683)
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
   (compute-siphash681
    [data682 key683]
    (clojure.core/with-open
     [data682
      (java.io.FileInputStream. (clojure.java.io/as-file data682))]
     (clojure.core/let
      [s__503__auto__
       data682
       c__504__auto__
       (clojure.core/int *buffer-size*)
       mac__505__auto__
       (new org.bouncycastle.crypto.macs.SipHash)
       k__506__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key683)
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
   siphash*
   "[HMAC] SipHash-2-4 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-siphash681
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   siphash-file*
   "[HMAC] SipHash-2-4 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-siphash681
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   siphash-file-bytes
   "[HMAC] SipHash-2-4 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-siphash681
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   siphash-file
   "[HMAC] SipHash-2-4 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-siphash681
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   siphash-bytes
   "[HMAC] SipHash-2-4 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-siphash681
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   siphash
   "[HMAC] SipHash-2-4 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-siphash681
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
