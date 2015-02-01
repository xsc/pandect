(ns
 pandect.algo.siphash48
 "SipHash-4-8 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__656
   (compute-siphash48653 [this__404__auto__ key__405__auto__]))
  (clojure.core/doseq
   [v__406__auto__ [#'G__656 #'compute-siphash48653]]
   (clojure.core/alter-meta!
    v__406__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__656
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-siphash48653
    [data654 key655]
    (clojure.core/let
     [buf__501__auto__
      data654
      k__502__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key655)
      mac__503__auto__
      (new org.bouncycastle.crypto.macs.SipHash 4 8)
      result__504__auto__
      (clojure.core/byte-array (.getMacSize mac__503__auto__))]
     (clojure.core/doto
      mac__503__auto__
      (.init k__502__auto__)
      (.update
       buf__501__auto__
       0
       (clojure.core/alength (clojure.core/bytes buf__501__auto__)))
      (.doFinal result__504__auto__ 0))
     result__504__auto__))
   java.lang.String
   (compute-siphash48653
    [data654 key655]
    (clojure.core/let
     [buf__501__auto__
      (.getBytes data654 "UTF-8")
      k__502__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key655)
      mac__503__auto__
      (new org.bouncycastle.crypto.macs.SipHash 4 8)
      result__504__auto__
      (clojure.core/byte-array (.getMacSize mac__503__auto__))]
     (clojure.core/doto
      mac__503__auto__
      (.init k__502__auto__)
      (.update
       buf__501__auto__
       0
       (clojure.core/alength (clojure.core/bytes buf__501__auto__)))
      (.doFinal result__504__auto__ 0))
     result__504__auto__))
   java.io.InputStream
   (compute-siphash48653
    [data654 key655]
    (clojure.core/let
     [s__506__auto__
      data654
      c__507__auto__
      (clojure.core/int *buffer-size*)
      mac__508__auto__
      (new org.bouncycastle.crypto.macs.SipHash 4 8)
      k__509__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key655)
      buf__510__auto__
      (clojure.core/byte-array c__507__auto__)]
     (.init mac__508__auto__ k__509__auto__)
     (clojure.core/loop
      []
      (clojure.core/let
       [r__511__auto__
        (.read s__506__auto__ buf__510__auto__ 0 c__507__auto__)]
       (clojure.core/when
        (clojure.core/not= r__511__auto__ -1)
        (.update mac__508__auto__ buf__510__auto__ 0 r__511__auto__)
        (recur))))
     (clojure.core/let
      [result__512__auto__
       (clojure.core/byte-array (.getMacSize mac__508__auto__))]
      (.doFinal mac__508__auto__ result__512__auto__ 0)
      result__512__auto__)))
   java.io.File
   (compute-siphash48653
    [data654 key655]
    (clojure.core/with-open
     [data654
      (java.io.FileInputStream. (clojure.java.io/as-file data654))]
     (clojure.core/let
      [s__506__auto__
       data654
       c__507__auto__
       (clojure.core/int *buffer-size*)
       mac__508__auto__
       (new org.bouncycastle.crypto.macs.SipHash 4 8)
       k__509__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key655)
       buf__510__auto__
       (clojure.core/byte-array c__507__auto__)]
      (.init mac__508__auto__ k__509__auto__)
      (clojure.core/loop
       []
       (clojure.core/let
        [r__511__auto__
         (.read s__506__auto__ buf__510__auto__ 0 c__507__auto__)]
        (clojure.core/when
         (clojure.core/not= r__511__auto__ -1)
         (.update mac__508__auto__ buf__510__auto__ 0 r__511__auto__)
         (recur))))
      (clojure.core/let
       [result__512__auto__
        (clojure.core/byte-array (.getMacSize mac__508__auto__))]
       (.doFinal mac__508__auto__ result__512__auto__ 0)
       result__512__auto__))))))
 [(clojure.core/defn
   siphash48*
   "[HMAC] SipHash-4-8 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-siphash48653
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   siphash48-file*
   "[HMAC] SipHash-4-8 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-siphash48653
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   siphash48-file-bytes
   "[HMAC] SipHash-4-8 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-siphash48653
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   siphash48-file
   "[HMAC] SipHash-4-8 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-siphash48653
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   siphash48-bytes
   "[HMAC] SipHash-4-8 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-siphash48653
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   siphash48
   "[HMAC] SipHash-4-8 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-siphash48653
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
