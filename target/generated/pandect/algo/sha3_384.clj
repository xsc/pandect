(clojure.core/ns
 pandect.algo.sha3-384
 (:require
  [pandect.utils [buffer :refer [*buffer-size*]] [convert]]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__659
   (compute-sha3-384657 [this__228__auto__]))
  (clojure.core/extend-protocol
   G__659
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha3-384657
    [data658]
    (clojure.core/let
     [buf__511__auto__
      data658
      digest__512__auto__
      (new org.bouncycastle.crypto.digests.SHA3Digest 384)
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
   (compute-sha3-384657
    [data658]
    (clojure.core/let
     [buf__511__auto__
      (.getBytes data658)
      digest__512__auto__
      (new org.bouncycastle.crypto.digests.SHA3Digest 384)
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
   (compute-sha3-384657
    [data658]
    (clojure.core/let
     [s__515__auto__
      data658
      c__516__auto__
      (clojure.core/int *buffer-size*)
      digest__517__auto__
      (new org.bouncycastle.crypto.digests.SHA3Digest 384)
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
   (compute-sha3-384657
    [data658]
    (clojure.core/with-open
     [data658
      (java.io.FileInputStream. (clojure.java.io/as-file data658))]
     (clojure.core/let
      [s__515__auto__
       data658
       c__516__auto__
       (clojure.core/int *buffer-size*)
       digest__517__auto__
       (new org.bouncycastle.crypto.digests.SHA3Digest 384)
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
   sha3-384*
   "[Hash] SHA-3 (384) (raw value)"
   [x]
   (compute-sha3-384657 x))
  (clojure.core/defn
   sha3-384-file*
   "[Hash] SHA-3 (384) (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha3-384657 x)))
  (clojure.core/defn
   sha3-384-file-bytes
   "[Hash] SHA-3 (384) (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha3-384657 x)))
  (clojure.core/defn
   sha3-384-file
   "[Hash] SHA-3 (384) (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha3-384657 x))))
  (clojure.core/defn
   sha3-384-bytes
   "[Hash] SHA-3 (384) (value -> byte array)"
   [x]
   (compute-sha3-384657 x))
  (clojure.core/defn
   sha3-384
   "[Hash] SHA-3 (384) (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha3-384657 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__663
   (compute-sha3-384660 [this__382__auto__ key__383__auto__]))
  (clojure.core/extend-protocol
   G__663
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha3-384660
    [data661 key662]
    (clojure.core/let
     [buf__498__auto__
      data661
      k__499__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key662)
      mac__500__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.SHA3Digest 384))
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
   (compute-sha3-384660
    [data661 key662]
    (clojure.core/let
     [buf__498__auto__
      (.getBytes data661)
      k__499__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key662)
      mac__500__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.SHA3Digest 384))
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
   (compute-sha3-384660
    [data661 key662]
    (clojure.core/let
     [s__503__auto__
      data661
      c__504__auto__
      (clojure.core/int *buffer-size*)
      mac__505__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.SHA3Digest 384))
      k__506__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key662)
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
   (compute-sha3-384660
    [data661 key662]
    (clojure.core/with-open
     [data661
      (java.io.FileInputStream. (clojure.java.io/as-file data661))]
     (clojure.core/let
      [s__503__auto__
       data661
       c__504__auto__
       (clojure.core/int *buffer-size*)
       mac__505__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.SHA3Digest 384))
       k__506__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key662)
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
   sha3-384-hmac*
   "[HMAC] SHA-3 (384) (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha3-384660
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha3-384-hmac-file*
   "[HMAC] SHA-3 (384) (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha3-384660
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha3-384-hmac-file-bytes
   "[HMAC] SHA-3 (384) (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha3-384660
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha3-384-hmac-file
   "[HMAC] SHA-3 (384) (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha3-384660
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   sha3-384-hmac-bytes
   "[HMAC] SHA-3 (384) (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha3-384660
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha3-384-hmac
   "[HMAC] SHA-3 (384) (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha3-384660
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
