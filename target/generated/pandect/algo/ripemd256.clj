(clojure.core/ns
 pandect.algo.ripemd256
 (:require
  [pandect.utils [buffer :refer [*buffer-size*]] [convert]]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__606
   (compute-ripemd256604 [this__228__auto__]))
  (clojure.core/extend-protocol
   G__606
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-ripemd256604
    [data605]
    (clojure.core/let
     [buf__511__auto__
      data605
      digest__512__auto__
      (new org.bouncycastle.crypto.digests.RIPEMD256Digest)
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
   (compute-ripemd256604
    [data605]
    (clojure.core/let
     [buf__511__auto__
      (.getBytes data605)
      digest__512__auto__
      (new org.bouncycastle.crypto.digests.RIPEMD256Digest)
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
   (compute-ripemd256604
    [data605]
    (clojure.core/let
     [s__515__auto__
      data605
      c__516__auto__
      (clojure.core/int *buffer-size*)
      digest__517__auto__
      (new org.bouncycastle.crypto.digests.RIPEMD256Digest)
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
   (compute-ripemd256604
    [data605]
    (clojure.core/with-open
     [data605
      (java.io.FileInputStream. (clojure.java.io/as-file data605))]
     (clojure.core/let
      [s__515__auto__
       data605
       c__516__auto__
       (clojure.core/int *buffer-size*)
       digest__517__auto__
       (new org.bouncycastle.crypto.digests.RIPEMD256Digest)
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
   ripemd256*
   "[Hash] RIPEMD-256 (raw value)"
   [x]
   (compute-ripemd256604 x))
  (clojure.core/defn
   ripemd256-file*
   "[Hash] RIPEMD-256 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-ripemd256604 x)))
  (clojure.core/defn
   ripemd256-file-bytes
   "[Hash] RIPEMD-256 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-ripemd256604 x)))
  (clojure.core/defn
   ripemd256-file
   "[Hash] RIPEMD-256 (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-ripemd256604 x))))
  (clojure.core/defn
   ripemd256-bytes
   "[Hash] RIPEMD-256 (value -> byte array)"
   [x]
   (compute-ripemd256604 x))
  (clojure.core/defn
   ripemd256
   "[Hash] RIPEMD-256 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-ripemd256604 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__610
   (compute-ripemd256607 [this__382__auto__ key__383__auto__]))
  (clojure.core/extend-protocol
   G__610
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-ripemd256607
    [data608 key609]
    (clojure.core/let
     [buf__498__auto__
      data608
      k__499__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key609)
      mac__500__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.RIPEMD256Digest))
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
   (compute-ripemd256607
    [data608 key609]
    (clojure.core/let
     [buf__498__auto__
      (.getBytes data608)
      k__499__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key609)
      mac__500__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.RIPEMD256Digest))
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
   (compute-ripemd256607
    [data608 key609]
    (clojure.core/let
     [s__503__auto__
      data608
      c__504__auto__
      (clojure.core/int *buffer-size*)
      mac__505__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.RIPEMD256Digest))
      k__506__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key609)
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
   (compute-ripemd256607
    [data608 key609]
    (clojure.core/with-open
     [data608
      (java.io.FileInputStream. (clojure.java.io/as-file data608))]
     (clojure.core/let
      [s__503__auto__
       data608
       c__504__auto__
       (clojure.core/int *buffer-size*)
       mac__505__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.RIPEMD256Digest))
       k__506__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key609)
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
   ripemd256-hmac*
   "[HMAC] RIPEMD-256 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-ripemd256607
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   ripemd256-hmac-file*
   "[HMAC] RIPEMD-256 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-ripemd256607
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd256-hmac-file-bytes
   "[HMAC] RIPEMD-256 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-ripemd256607
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd256-hmac-file
   "[HMAC] RIPEMD-256 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-ripemd256607
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   ripemd256-hmac-bytes
   "[HMAC] RIPEMD-256 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-ripemd256607
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   ripemd256-hmac
   "[HMAC] RIPEMD-256 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-ripemd256607
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
