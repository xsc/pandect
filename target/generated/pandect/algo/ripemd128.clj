(clojure.core/ns
 pandect.algo.ripemd128
 (:require
  [pandect.utils [buffer :refer [*buffer-size*]] [convert]]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__634
   (compute-ripemd128632 [this__228__auto__]))
  (clojure.core/extend-protocol
   G__634
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-ripemd128632
    [data633]
    (clojure.core/let
     [buf__511__auto__
      data633
      digest__512__auto__
      (new org.bouncycastle.crypto.digests.RIPEMD128Digest)
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
   (compute-ripemd128632
    [data633]
    (clojure.core/let
     [buf__511__auto__
      (.getBytes data633)
      digest__512__auto__
      (new org.bouncycastle.crypto.digests.RIPEMD128Digest)
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
   (compute-ripemd128632
    [data633]
    (clojure.core/let
     [s__515__auto__
      data633
      c__516__auto__
      (clojure.core/int *buffer-size*)
      digest__517__auto__
      (new org.bouncycastle.crypto.digests.RIPEMD128Digest)
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
   (compute-ripemd128632
    [data633]
    (clojure.core/with-open
     [data633
      (java.io.FileInputStream. (clojure.java.io/as-file data633))]
     (clojure.core/let
      [s__515__auto__
       data633
       c__516__auto__
       (clojure.core/int *buffer-size*)
       digest__517__auto__
       (new org.bouncycastle.crypto.digests.RIPEMD128Digest)
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
   ripemd128*
   "[Hash] RIPEMD-128 (raw value)"
   [x]
   (compute-ripemd128632 x))
  (clojure.core/defn
   ripemd128-file*
   "[Hash] RIPEMD-128 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-ripemd128632 x)))
  (clojure.core/defn
   ripemd128-file-bytes
   "[Hash] RIPEMD-128 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-ripemd128632 x)))
  (clojure.core/defn
   ripemd128-file
   "[Hash] RIPEMD-128 (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-ripemd128632 x))))
  (clojure.core/defn
   ripemd128-bytes
   "[Hash] RIPEMD-128 (value -> byte array)"
   [x]
   (compute-ripemd128632 x))
  (clojure.core/defn
   ripemd128
   "[Hash] RIPEMD-128 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-ripemd128632 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__638
   (compute-ripemd128635 [this__382__auto__ key__383__auto__]))
  (clojure.core/extend-protocol
   G__638
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-ripemd128635
    [data636 key637]
    (clojure.core/let
     [buf__498__auto__
      data636
      k__499__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key637)
      mac__500__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.RIPEMD128Digest))
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
   (compute-ripemd128635
    [data636 key637]
    (clojure.core/let
     [buf__498__auto__
      (.getBytes data636)
      k__499__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key637)
      mac__500__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.RIPEMD128Digest))
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
   (compute-ripemd128635
    [data636 key637]
    (clojure.core/let
     [s__503__auto__
      data636
      c__504__auto__
      (clojure.core/int *buffer-size*)
      mac__505__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.RIPEMD128Digest))
      k__506__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key637)
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
   (compute-ripemd128635
    [data636 key637]
    (clojure.core/with-open
     [data636
      (java.io.FileInputStream. (clojure.java.io/as-file data636))]
     (clojure.core/let
      [s__503__auto__
       data636
       c__504__auto__
       (clojure.core/int *buffer-size*)
       mac__505__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.RIPEMD128Digest))
       k__506__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key637)
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
   ripemd128-hmac*
   "[HMAC] RIPEMD-128 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-ripemd128635
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   ripemd128-hmac-file*
   "[HMAC] RIPEMD-128 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-ripemd128635
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd128-hmac-file-bytes
   "[HMAC] RIPEMD-128 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-ripemd128635
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd128-hmac-file
   "[HMAC] RIPEMD-128 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-ripemd128635
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   ripemd128-hmac-bytes
   "[HMAC] RIPEMD-128 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-ripemd128635
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   ripemd128-hmac
   "[HMAC] RIPEMD-128 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-ripemd128635
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
