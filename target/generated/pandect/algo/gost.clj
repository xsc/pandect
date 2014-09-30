(clojure.core/ns
 pandect.algo.gost
 (:require
  [pandect.utils [buffer :refer [*buffer-size*]] [convert]]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__708
   (compute-gost706 [this__228__auto__]))
  (clojure.core/extend-protocol
   G__708
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-gost706
    [data707]
    (clojure.core/let
     [buf__511__auto__
      data707
      digest__512__auto__
      (new org.bouncycastle.crypto.digests.GOST3411Digest)
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
   (compute-gost706
    [data707]
    (clojure.core/let
     [buf__511__auto__
      (.getBytes data707)
      digest__512__auto__
      (new org.bouncycastle.crypto.digests.GOST3411Digest)
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
   (compute-gost706
    [data707]
    (clojure.core/let
     [s__515__auto__
      data707
      c__516__auto__
      (clojure.core/int *buffer-size*)
      digest__517__auto__
      (new org.bouncycastle.crypto.digests.GOST3411Digest)
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
   (compute-gost706
    [data707]
    (clojure.core/with-open
     [data707
      (java.io.FileInputStream. (clojure.java.io/as-file data707))]
     (clojure.core/let
      [s__515__auto__
       data707
       c__516__auto__
       (clojure.core/int *buffer-size*)
       digest__517__auto__
       (new org.bouncycastle.crypto.digests.GOST3411Digest)
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
   gost*
   "[Hash] GOST 34.11-94 (raw value)"
   [x]
   (compute-gost706 x))
  (clojure.core/defn
   gost-file*
   "[Hash] GOST 34.11-94 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-gost706 x)))
  (clojure.core/defn
   gost-file-bytes
   "[Hash] GOST 34.11-94 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-gost706 x)))
  (clojure.core/defn
   gost-file
   "[Hash] GOST 34.11-94 (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-gost706 x))))
  (clojure.core/defn
   gost-bytes
   "[Hash] GOST 34.11-94 (value -> byte array)"
   [x]
   (compute-gost706 x))
  (clojure.core/defn
   gost
   "[Hash] GOST 34.11-94 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-gost706 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__712
   (compute-gost709 [this__382__auto__ key__383__auto__]))
  (clojure.core/extend-protocol
   G__712
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-gost709
    [data710 key711]
    (clojure.core/let
     [buf__498__auto__
      data710
      k__499__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key711)
      mac__500__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.GOST3411Digest))
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
   (compute-gost709
    [data710 key711]
    (clojure.core/let
     [buf__498__auto__
      (.getBytes data710)
      k__499__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key711)
      mac__500__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.GOST3411Digest))
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
   (compute-gost709
    [data710 key711]
    (clojure.core/let
     [s__503__auto__
      data710
      c__504__auto__
      (clojure.core/int *buffer-size*)
      mac__505__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.GOST3411Digest))
      k__506__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key711)
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
   (compute-gost709
    [data710 key711]
    (clojure.core/with-open
     [data710
      (java.io.FileInputStream. (clojure.java.io/as-file data710))]
     (clojure.core/let
      [s__503__auto__
       data710
       c__504__auto__
       (clojure.core/int *buffer-size*)
       mac__505__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.GOST3411Digest))
       k__506__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key711)
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
   gost-hmac*
   "[HMAC] GOST 34.11-94 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-gost709
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   gost-hmac-file*
   "[HMAC] GOST 34.11-94 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-gost709
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   gost-hmac-file-bytes
   "[HMAC] GOST 34.11-94 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-gost709
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   gost-hmac-file
   "[HMAC] GOST 34.11-94 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-gost709
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   gost-hmac-bytes
   "[HMAC] GOST 34.11-94 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-gost709
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   gost-hmac
   "[HMAC] GOST 34.11-94 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-gost709
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
