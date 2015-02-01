(ns
 pandect.algo.whirlpool
 "Whirlpool algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__676
   (compute-whirlpool674 [this__228__auto__]))
  (clojure.core/doseq
   [v__229__auto__ [#'G__676 #'compute-whirlpool674]]
   (clojure.core/alter-meta!
    v__229__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__676
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-whirlpool674
    [data675]
    (clojure.core/let
     [buf__514__auto__
      data675
      digest__515__auto__
      (new org.bouncycastle.crypto.digests.WhirlpoolDigest)
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
   (compute-whirlpool674
    [data675]
    (clojure.core/let
     [buf__514__auto__
      (.getBytes data675 "UTF-8")
      digest__515__auto__
      (new org.bouncycastle.crypto.digests.WhirlpoolDigest)
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
   (compute-whirlpool674
    [data675]
    (clojure.core/let
     [s__518__auto__
      data675
      c__519__auto__
      (clojure.core/int *buffer-size*)
      digest__520__auto__
      (new org.bouncycastle.crypto.digests.WhirlpoolDigest)
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
   (compute-whirlpool674
    [data675]
    (clojure.core/with-open
     [data675
      (java.io.FileInputStream. (clojure.java.io/as-file data675))]
     (clojure.core/let
      [s__518__auto__
       data675
       c__519__auto__
       (clojure.core/int *buffer-size*)
       digest__520__auto__
       (new org.bouncycastle.crypto.digests.WhirlpoolDigest)
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
   whirlpool*
   "[Hash] Whirlpool (raw value)"
   [x]
   (compute-whirlpool674 x))
  (clojure.core/defn
   whirlpool-file*
   "[Hash] Whirlpool (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-whirlpool674 x)))
  (clojure.core/defn
   whirlpool-file-bytes
   "[Hash] Whirlpool (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-whirlpool674 x)))
  (clojure.core/defn
   whirlpool-file
   "[Hash] Whirlpool (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-whirlpool674 x))))
  (clojure.core/defn
   whirlpool-bytes
   "[Hash] Whirlpool (value -> byte array)"
   [x]
   (compute-whirlpool674 x))
  (clojure.core/defn
   whirlpool
   "[Hash] Whirlpool (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-whirlpool674 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__680
   (compute-whirlpool677 [this__404__auto__ key__405__auto__]))
  (clojure.core/doseq
   [v__406__auto__ [#'G__680 #'compute-whirlpool677]]
   (clojure.core/alter-meta!
    v__406__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__680
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-whirlpool677
    [data678 key679]
    (clojure.core/let
     [buf__501__auto__
      data678
      k__502__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key679)
      mac__503__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.WhirlpoolDigest))
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
   (compute-whirlpool677
    [data678 key679]
    (clojure.core/let
     [buf__501__auto__
      (.getBytes data678 "UTF-8")
      k__502__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key679)
      mac__503__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.WhirlpoolDigest))
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
   (compute-whirlpool677
    [data678 key679]
    (clojure.core/let
     [s__506__auto__
      data678
      c__507__auto__
      (clojure.core/int *buffer-size*)
      mac__508__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.WhirlpoolDigest))
      k__509__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key679)
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
   (compute-whirlpool677
    [data678 key679]
    (clojure.core/with-open
     [data678
      (java.io.FileInputStream. (clojure.java.io/as-file data678))]
     (clojure.core/let
      [s__506__auto__
       data678
       c__507__auto__
       (clojure.core/int *buffer-size*)
       mac__508__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.WhirlpoolDigest))
       k__509__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key679)
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
   whirlpool-hmac*
   "[HMAC] Whirlpool (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-whirlpool677
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   whirlpool-hmac-file*
   "[HMAC] Whirlpool (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-whirlpool677
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   whirlpool-hmac-file-bytes
   "[HMAC] Whirlpool (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-whirlpool677
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   whirlpool-hmac-file
   "[HMAC] Whirlpool (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-whirlpool677
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   whirlpool-hmac-bytes
   "[HMAC] Whirlpool (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-whirlpool677
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   whirlpool-hmac
   "[HMAC] Whirlpool (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-whirlpool677
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
