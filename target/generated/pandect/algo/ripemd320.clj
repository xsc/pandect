(clojure.core/ns
 pandect.algo.ripemd320
 (:require
  [pandect.utils [buffer :refer [*buffer-size*]] [convert]]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__641
   (compute-ripemd320639 [this__228__auto__]))
  (clojure.core/extend-protocol
   G__641
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-ripemd320639
    [data640]
    (clojure.core/let
     [buf__511__auto__
      data640
      digest__512__auto__
      (new org.bouncycastle.crypto.digests.RIPEMD320Digest)
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
   (compute-ripemd320639
    [data640]
    (clojure.core/let
     [buf__511__auto__
      (.getBytes data640)
      digest__512__auto__
      (new org.bouncycastle.crypto.digests.RIPEMD320Digest)
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
   (compute-ripemd320639
    [data640]
    (clojure.core/let
     [s__515__auto__
      data640
      c__516__auto__
      (clojure.core/int *buffer-size*)
      digest__517__auto__
      (new org.bouncycastle.crypto.digests.RIPEMD320Digest)
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
   (compute-ripemd320639
    [data640]
    (clojure.core/with-open
     [data640
      (java.io.FileInputStream. (clojure.java.io/as-file data640))]
     (clojure.core/let
      [s__515__auto__
       data640
       c__516__auto__
       (clojure.core/int *buffer-size*)
       digest__517__auto__
       (new org.bouncycastle.crypto.digests.RIPEMD320Digest)
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
   ripemd320*
   "[Hash] RIPEMD-320 (raw value)"
   [x]
   (compute-ripemd320639 x))
  (clojure.core/defn
   ripemd320-file*
   "[Hash] RIPEMD-320 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-ripemd320639 x)))
  (clojure.core/defn
   ripemd320-file-bytes
   "[Hash] RIPEMD-320 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-ripemd320639 x)))
  (clojure.core/defn
   ripemd320-file
   "[Hash] RIPEMD-320 (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-ripemd320639 x))))
  (clojure.core/defn
   ripemd320-bytes
   "[Hash] RIPEMD-320 (value -> byte array)"
   [x]
   (compute-ripemd320639 x))
  (clojure.core/defn
   ripemd320
   "[Hash] RIPEMD-320 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-ripemd320639 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__645
   (compute-ripemd320642 [this__382__auto__ key__383__auto__]))
  (clojure.core/extend-protocol
   G__645
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-ripemd320642
    [data643 key644]
    (clojure.core/let
     [buf__498__auto__
      data643
      k__499__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key644)
      mac__500__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.RIPEMD320Digest))
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
   (compute-ripemd320642
    [data643 key644]
    (clojure.core/let
     [buf__498__auto__
      (.getBytes data643)
      k__499__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key644)
      mac__500__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.RIPEMD320Digest))
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
   (compute-ripemd320642
    [data643 key644]
    (clojure.core/let
     [s__503__auto__
      data643
      c__504__auto__
      (clojure.core/int *buffer-size*)
      mac__505__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.RIPEMD320Digest))
      k__506__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key644)
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
   (compute-ripemd320642
    [data643 key644]
    (clojure.core/with-open
     [data643
      (java.io.FileInputStream. (clojure.java.io/as-file data643))]
     (clojure.core/let
      [s__503__auto__
       data643
       c__504__auto__
       (clojure.core/int *buffer-size*)
       mac__505__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.RIPEMD320Digest))
       k__506__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key644)
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
   ripemd320-hmac*
   "[HMAC] RIPEMD-320 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-ripemd320642
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   ripemd320-hmac-file*
   "[HMAC] RIPEMD-320 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-ripemd320642
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd320-hmac-file-bytes
   "[HMAC] RIPEMD-320 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-ripemd320642
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   ripemd320-hmac-file
   "[HMAC] RIPEMD-320 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-ripemd320642
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   ripemd320-hmac-bytes
   "[HMAC] RIPEMD-320 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-ripemd320642
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   ripemd320-hmac
   "[HMAC] RIPEMD-320 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-ripemd320642
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
