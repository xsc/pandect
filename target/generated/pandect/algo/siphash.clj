(ns
 pandect.algo.siphash
 "SipHash-2-4 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__691
   (compute-siphash688 [this__404__auto__ key__405__auto__]))
  (clojure.core/doseq
   [v__406__auto__ [#'G__691 #'compute-siphash688]]
   (clojure.core/alter-meta!
    v__406__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__691
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-siphash688
    [data689 key690]
    (clojure.core/let
     [buf__501__auto__
      data689
      k__502__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key690)
      mac__503__auto__
      (new org.bouncycastle.crypto.macs.SipHash)
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
   (compute-siphash688
    [data689 key690]
    (clojure.core/let
     [buf__501__auto__
      (.getBytes data689 "UTF-8")
      k__502__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key690)
      mac__503__auto__
      (new org.bouncycastle.crypto.macs.SipHash)
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
   (compute-siphash688
    [data689 key690]
    (clojure.core/let
     [s__506__auto__
      data689
      c__507__auto__
      (clojure.core/int *buffer-size*)
      mac__508__auto__
      (new org.bouncycastle.crypto.macs.SipHash)
      k__509__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key690)
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
   (compute-siphash688
    [data689 key690]
    (clojure.core/with-open
     [data689
      (java.io.FileInputStream. (clojure.java.io/as-file data689))]
     (clojure.core/let
      [s__506__auto__
       data689
       c__507__auto__
       (clojure.core/int *buffer-size*)
       mac__508__auto__
       (new org.bouncycastle.crypto.macs.SipHash)
       k__509__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key690)
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
   siphash*
   "[HMAC] SipHash-2-4 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-siphash688
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   siphash-file*
   "[HMAC] SipHash-2-4 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-siphash688
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   siphash-file-bytes
   "[HMAC] SipHash-2-4 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-siphash688
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   siphash-file
   "[HMAC] SipHash-2-4 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-siphash688
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   siphash-bytes
   "[HMAC] SipHash-2-4 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-siphash688
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   siphash
   "[HMAC] SipHash-2-4 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-siphash688
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
