(clojure.core/ns
 pandect.algo.whirlpool
 (:require
  [pandect.utils [buffer :refer [*buffer-size*]] [convert]]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__669
   (compute-whirlpool667 [this__228__auto__]))
  (clojure.core/extend-protocol
   G__669
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-whirlpool667
    [data668]
    (clojure.core/let
     [buf__511__auto__
      data668
      digest__512__auto__
      (new org.bouncycastle.crypto.digests.WhirlpoolDigest)
      result__513__auto__
      (clojure.core/byte-array (.getDigestSize digest__512__auto__))]
     (clojure.core/doto
      digest__512__auto__
      (.update
       buf__511__auto__
       0
       (clojure.core/alength (clojure.core/bytes buf__511__auto__)))
      (.doFinal result__513__auto__ 0))
     result__513__auto__))
   java.lang.String
   (compute-whirlpool667
    [data668]
    (clojure.core/let
     [buf__511__auto__
      (.getBytes data668)
      digest__512__auto__
      (new org.bouncycastle.crypto.digests.WhirlpoolDigest)
      result__513__auto__
      (clojure.core/byte-array (.getDigestSize digest__512__auto__))]
     (clojure.core/doto
      digest__512__auto__
      (.update
       buf__511__auto__
       0
       (clojure.core/alength (clojure.core/bytes buf__511__auto__)))
      (.doFinal result__513__auto__ 0))
     result__513__auto__))
   java.io.InputStream
   (compute-whirlpool667
    [data668]
    (clojure.core/let
     [s__515__auto__
      data668
      c__516__auto__
      (clojure.core/int *buffer-size*)
      digest__517__auto__
      (new org.bouncycastle.crypto.digests.WhirlpoolDigest)
      buf__518__auto__
      (clojure.core/byte-array c__516__auto__)]
     (clojure.core/loop
      []
      (clojure.core/let
       [r__519__auto__
        (.read s__515__auto__ buf__518__auto__ 0 c__516__auto__)]
       (clojure.core/when
        (clojure.core/not= r__519__auto__ -1)
        (.update digest__517__auto__ buf__518__auto__ 0 r__519__auto__)
        (recur))))
     (clojure.core/let
      [result__520__auto__
       (clojure.core/byte-array (.getDigestSize digest__517__auto__))]
      (.doFinal digest__517__auto__ result__520__auto__ 0)
      result__520__auto__)))
   java.io.File
   (compute-whirlpool667
    [data668]
    (clojure.core/with-open
     [data668
      (java.io.FileInputStream. (clojure.java.io/as-file data668))]
     (clojure.core/let
      [s__515__auto__
       data668
       c__516__auto__
       (clojure.core/int *buffer-size*)
       digest__517__auto__
       (new org.bouncycastle.crypto.digests.WhirlpoolDigest)
       buf__518__auto__
       (clojure.core/byte-array c__516__auto__)]
      (clojure.core/loop
       []
       (clojure.core/let
        [r__519__auto__
         (.read s__515__auto__ buf__518__auto__ 0 c__516__auto__)]
        (clojure.core/when
         (clojure.core/not= r__519__auto__ -1)
         (.update
          digest__517__auto__
          buf__518__auto__
          0
          r__519__auto__)
         (recur))))
      (clojure.core/let
       [result__520__auto__
        (clojure.core/byte-array (.getDigestSize digest__517__auto__))]
       (.doFinal digest__517__auto__ result__520__auto__ 0)
       result__520__auto__))))))
 [(clojure.core/defn
   whirlpool*
   "[Hash] Whirlpool (raw value)"
   [x]
   (compute-whirlpool667 x))
  (clojure.core/defn
   whirlpool-file*
   "[Hash] Whirlpool (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-whirlpool667 x)))
  (clojure.core/defn
   whirlpool-file-bytes
   "[Hash] Whirlpool (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-whirlpool667 x)))
  (clojure.core/defn
   whirlpool-file
   "[Hash] Whirlpool (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-whirlpool667 x))))
  (clojure.core/defn
   whirlpool-bytes
   "[Hash] Whirlpool (value -> byte array)"
   [x]
   (compute-whirlpool667 x))
  (clojure.core/defn
   whirlpool
   "[Hash] Whirlpool (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-whirlpool667 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__673
   (compute-whirlpool670 [this__382__auto__ key__383__auto__]))
  (clojure.core/extend-protocol
   G__673
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-whirlpool670
    [data671 key672]
    (clojure.core/let
     [buf__498__auto__
      data671
      k__499__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key672)
      mac__500__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.WhirlpoolDigest))
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
   (compute-whirlpool670
    [data671 key672]
    (clojure.core/let
     [buf__498__auto__
      (.getBytes data671)
      k__499__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key672)
      mac__500__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.WhirlpoolDigest))
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
   (compute-whirlpool670
    [data671 key672]
    (clojure.core/let
     [s__503__auto__
      data671
      c__504__auto__
      (clojure.core/int *buffer-size*)
      mac__505__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.WhirlpoolDigest))
      k__506__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key672)
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
   (compute-whirlpool670
    [data671 key672]
    (clojure.core/with-open
     [data671
      (java.io.FileInputStream. (clojure.java.io/as-file data671))]
     (clojure.core/let
      [s__503__auto__
       data671
       c__504__auto__
       (clojure.core/int *buffer-size*)
       mac__505__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.WhirlpoolDigest))
       k__506__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key672)
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
   whirlpool-hmac*
   "[HMAC] Whirlpool (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-whirlpool670
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   whirlpool-hmac-file*
   "[HMAC] Whirlpool (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-whirlpool670
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   whirlpool-hmac-file-bytes
   "[HMAC] Whirlpool (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-whirlpool670
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   whirlpool-hmac-file
   "[HMAC] Whirlpool (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-whirlpool670
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   whirlpool-hmac-bytes
   "[HMAC] Whirlpool (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-whirlpool670
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   whirlpool-hmac
   "[HMAC] Whirlpool (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-whirlpool670
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
