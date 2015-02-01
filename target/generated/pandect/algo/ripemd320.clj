(ns
 pandect.algo.ripemd320
 "RIPEMD-320 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__648
   (compute-ripemd320646 [this__228__auto__]))
  (clojure.core/doseq
   [v__229__auto__ [#'G__648 #'compute-ripemd320646]]
   (clojure.core/alter-meta!
    v__229__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__648
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-ripemd320646
    [data647]
    (clojure.core/let
     [buf__514__auto__
      data647
      digest__515__auto__
      (new org.bouncycastle.crypto.digests.RIPEMD320Digest)
      result__516__auto__
      (clojure.core/byte-array (.getDigestSize digest__515__auto__))]
     (clojure.core/doto
      digest__515__auto__
      (.update
       buf__514__auto__
       0
       (clojure.core/alength (clojure.core/bytes buf__514__auto__)))
      (.doFinal result__516__auto__ 0))
     result__516__auto__))
   java.lang.String
   (compute-ripemd320646
    [data647]
    (clojure.core/let
     [buf__514__auto__
      (.getBytes data647 "UTF-8")
      digest__515__auto__
      (new org.bouncycastle.crypto.digests.RIPEMD320Digest)
      result__516__auto__
      (clojure.core/byte-array (.getDigestSize digest__515__auto__))]
     (clojure.core/doto
      digest__515__auto__
      (.update
       buf__514__auto__
       0
       (clojure.core/alength (clojure.core/bytes buf__514__auto__)))
      (.doFinal result__516__auto__ 0))
     result__516__auto__))
   java.io.InputStream
   (compute-ripemd320646
    [data647]
    (clojure.core/let
     [s__518__auto__
      data647
      c__519__auto__
      (clojure.core/int *buffer-size*)
      digest__520__auto__
      (new org.bouncycastle.crypto.digests.RIPEMD320Digest)
      buf__521__auto__
      (clojure.core/byte-array c__519__auto__)]
     (clojure.core/loop
      []
      (clojure.core/let
       [r__522__auto__
        (.read s__518__auto__ buf__521__auto__ 0 c__519__auto__)]
       (clojure.core/when
        (clojure.core/not= r__522__auto__ -1)
        (.update digest__520__auto__ buf__521__auto__ 0 r__522__auto__)
        (recur))))
     (clojure.core/let
      [result__523__auto__
       (clojure.core/byte-array (.getDigestSize digest__520__auto__))]
      (.doFinal digest__520__auto__ result__523__auto__ 0)
      result__523__auto__)))
   java.io.File
   (compute-ripemd320646
    [data647]
    (clojure.core/with-open
     [data647
      (java.io.FileInputStream. (clojure.java.io/as-file data647))]
     (clojure.core/let
      [s__518__auto__
       data647
       c__519__auto__
       (clojure.core/int *buffer-size*)
       digest__520__auto__
       (new org.bouncycastle.crypto.digests.RIPEMD320Digest)
       buf__521__auto__
       (clojure.core/byte-array c__519__auto__)]
      (clojure.core/loop
       []
       (clojure.core/let
        [r__522__auto__
         (.read s__518__auto__ buf__521__auto__ 0 c__519__auto__)]
        (clojure.core/when
         (clojure.core/not= r__522__auto__ -1)
         (.update
          digest__520__auto__
          buf__521__auto__
          0
          r__522__auto__)
         (recur))))
      (clojure.core/let
       [result__523__auto__
        (clojure.core/byte-array (.getDigestSize digest__520__auto__))]
       (.doFinal digest__520__auto__ result__523__auto__ 0)
       result__523__auto__))))))
 [(clojure.core/defn
   ripemd320*
   "[Hash] RIPEMD-320 (raw value)"
   [x]
   (compute-ripemd320646 x))
  (clojure.core/defn
   ripemd320-file*
   "[Hash] RIPEMD-320 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-ripemd320646 x)))
  (clojure.core/defn
   ripemd320-file-bytes
   "[Hash] RIPEMD-320 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-ripemd320646 x)))
  (clojure.core/defn
   ripemd320-file
   "[Hash] RIPEMD-320 (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-ripemd320646 x))))
  (clojure.core/defn
   ripemd320-bytes
   "[Hash] RIPEMD-320 (value -> byte array)"
   [x]
   (compute-ripemd320646 x))
  (clojure.core/defn
   ripemd320
   "[Hash] RIPEMD-320 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-ripemd320646 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__652
   (compute-ripemd320649 [this__404__auto__ key__405__auto__]))
  (clojure.core/doseq
   [v__406__auto__ [#'G__652 #'compute-ripemd320649]]
   (clojure.core/alter-meta!
    v__406__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__652
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-ripemd320649
    [data650 key651]
    (clojure.core/let
     [buf__501__auto__
      data650
      k__502__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key651)
      mac__503__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.RIPEMD320Digest))
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
   (compute-ripemd320649
    [data650 key651]
    (clojure.core/let
     [buf__501__auto__
      (.getBytes data650 "UTF-8")
      k__502__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key651)
      mac__503__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.RIPEMD320Digest))
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
   (compute-ripemd320649
    [data650 key651]
    (clojure.core/let
     [s__506__auto__
      data650
      c__507__auto__
      (clojure.core/int *buffer-size*)
      mac__508__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.RIPEMD320Digest))
      k__509__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key651)
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
   (compute-ripemd320649
    [data650 key651]
    (clojure.core/with-open
     [data650
      (java.io.FileInputStream. (clojure.java.io/as-file data650))]
     (clojure.core/let
      [s__506__auto__
       data650
       c__507__auto__
       (clojure.core/int *buffer-size*)
       mac__508__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.RIPEMD320Digest))
       k__509__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key651)
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
   ripemd320-hmac*
   "[HMAC] RIPEMD-320 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-ripemd320649
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   ripemd320-hmac-file*
   "[HMAC] RIPEMD-320 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-ripemd320649
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd320-hmac-file-bytes
   "[HMAC] RIPEMD-320 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-ripemd320649
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd320-hmac-file
   "[HMAC] RIPEMD-320 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-ripemd320649
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   ripemd320-hmac-bytes
   "[HMAC] RIPEMD-320 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-ripemd320649
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   ripemd320-hmac
   "[HMAC] RIPEMD-320 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-ripemd320649
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
