(ns
 pandect.algo.gost
 "GOST 34.11-94 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__715
   (compute-gost713 [this__228__auto__]))
  (clojure.core/doseq
   [v__229__auto__ [#'G__715 #'compute-gost713]]
   (clojure.core/alter-meta!
    v__229__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__715
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-gost713
    [data714]
    (clojure.core/let
     [buf__514__auto__
      data714
      digest__515__auto__
      (new org.bouncycastle.crypto.digests.GOST3411Digest)
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
   (compute-gost713
    [data714]
    (clojure.core/let
     [buf__514__auto__
      (.getBytes data714 "UTF-8")
      digest__515__auto__
      (new org.bouncycastle.crypto.digests.GOST3411Digest)
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
   (compute-gost713
    [data714]
    (clojure.core/let
     [s__518__auto__
      data714
      c__519__auto__
      (clojure.core/int *buffer-size*)
      digest__520__auto__
      (new org.bouncycastle.crypto.digests.GOST3411Digest)
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
   (compute-gost713
    [data714]
    (clojure.core/with-open
     [data714
      (java.io.FileInputStream. (clojure.java.io/as-file data714))]
     (clojure.core/let
      [s__518__auto__
       data714
       c__519__auto__
       (clojure.core/int *buffer-size*)
       digest__520__auto__
       (new org.bouncycastle.crypto.digests.GOST3411Digest)
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
   gost*
   "[Hash] GOST 34.11-94 (raw value)"
   [x]
   (compute-gost713 x))
  (clojure.core/defn
   gost-file*
   "[Hash] GOST 34.11-94 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-gost713 x)))
  (clojure.core/defn
   gost-file-bytes
   "[Hash] GOST 34.11-94 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-gost713 x)))
  (clojure.core/defn
   gost-file
   "[Hash] GOST 34.11-94 (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-gost713 x))))
  (clojure.core/defn
   gost-bytes
   "[Hash] GOST 34.11-94 (value -> byte array)"
   [x]
   (compute-gost713 x))
  (clojure.core/defn
   gost
   "[Hash] GOST 34.11-94 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-gost713 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__719
   (compute-gost716 [this__404__auto__ key__405__auto__]))
  (clojure.core/doseq
   [v__406__auto__ [#'G__719 #'compute-gost716]]
   (clojure.core/alter-meta!
    v__406__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__719
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-gost716
    [data717 key718]
    (clojure.core/let
     [buf__501__auto__
      data717
      k__502__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key718)
      mac__503__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.GOST3411Digest))
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
   (compute-gost716
    [data717 key718]
    (clojure.core/let
     [buf__501__auto__
      (.getBytes data717 "UTF-8")
      k__502__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key718)
      mac__503__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.GOST3411Digest))
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
   (compute-gost716
    [data717 key718]
    (clojure.core/let
     [s__506__auto__
      data717
      c__507__auto__
      (clojure.core/int *buffer-size*)
      mac__508__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.GOST3411Digest))
      k__509__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key718)
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
   (compute-gost716
    [data717 key718]
    (clojure.core/with-open
     [data717
      (java.io.FileInputStream. (clojure.java.io/as-file data717))]
     (clojure.core/let
      [s__506__auto__
       data717
       c__507__auto__
       (clojure.core/int *buffer-size*)
       mac__508__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.GOST3411Digest))
       k__509__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key718)
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
   gost-hmac*
   "[HMAC] GOST 34.11-94 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-gost716
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   gost-hmac-file*
   "[HMAC] GOST 34.11-94 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-gost716
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   gost-hmac-file-bytes
   "[HMAC] GOST 34.11-94 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-gost716
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   gost-hmac-file
   "[HMAC] GOST 34.11-94 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-gost716
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   gost-hmac-bytes
   "[HMAC] GOST 34.11-94 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-gost716
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   gost-hmac
   "[HMAC] GOST 34.11-94 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-gost716
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
