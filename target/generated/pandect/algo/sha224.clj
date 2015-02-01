(ns
 pandect.algo.sha224
 "SHA-224 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__701
   (compute-sha224699 [this__228__auto__]))
  (clojure.core/doseq
   [v__229__auto__ [#'G__701 #'compute-sha224699]]
   (clojure.core/alter-meta!
    v__229__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__701
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha224699
    [data700]
    (clojure.core/let
     [buf__514__auto__
      data700
      digest__515__auto__
      (new org.bouncycastle.crypto.digests.SHA224Digest)
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
   (compute-sha224699
    [data700]
    (clojure.core/let
     [buf__514__auto__
      (.getBytes data700 "UTF-8")
      digest__515__auto__
      (new org.bouncycastle.crypto.digests.SHA224Digest)
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
   (compute-sha224699
    [data700]
    (clojure.core/let
     [s__518__auto__
      data700
      c__519__auto__
      (clojure.core/int *buffer-size*)
      digest__520__auto__
      (new org.bouncycastle.crypto.digests.SHA224Digest)
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
   (compute-sha224699
    [data700]
    (clojure.core/with-open
     [data700
      (java.io.FileInputStream. (clojure.java.io/as-file data700))]
     (clojure.core/let
      [s__518__auto__
       data700
       c__519__auto__
       (clojure.core/int *buffer-size*)
       digest__520__auto__
       (new org.bouncycastle.crypto.digests.SHA224Digest)
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
   sha224*
   "[Hash] SHA-224 (raw value)"
   [x]
   (compute-sha224699 x))
  (clojure.core/defn
   sha224-file*
   "[Hash] SHA-224 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha224699 x)))
  (clojure.core/defn
   sha224-file-bytes
   "[Hash] SHA-224 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha224699 x)))
  (clojure.core/defn
   sha224-file
   "[Hash] SHA-224 (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha224699 x))))
  (clojure.core/defn
   sha224-bytes
   "[Hash] SHA-224 (value -> byte array)"
   [x]
   (compute-sha224699 x))
  (clojure.core/defn
   sha224
   "[Hash] SHA-224 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha224699 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__705
   (compute-sha224702 [this__404__auto__ key__405__auto__]))
  (clojure.core/doseq
   [v__406__auto__ [#'G__705 #'compute-sha224702]]
   (clojure.core/alter-meta!
    v__406__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__705
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha224702
    [data703 key704]
    (clojure.core/let
     [buf__501__auto__
      data703
      k__502__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key704)
      mac__503__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.SHA224Digest))
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
   (compute-sha224702
    [data703 key704]
    (clojure.core/let
     [buf__501__auto__
      (.getBytes data703 "UTF-8")
      k__502__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key704)
      mac__503__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.SHA224Digest))
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
   (compute-sha224702
    [data703 key704]
    (clojure.core/let
     [s__506__auto__
      data703
      c__507__auto__
      (clojure.core/int *buffer-size*)
      mac__508__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.SHA224Digest))
      k__509__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key704)
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
   (compute-sha224702
    [data703 key704]
    (clojure.core/with-open
     [data703
      (java.io.FileInputStream. (clojure.java.io/as-file data703))]
     (clojure.core/let
      [s__506__auto__
       data703
       c__507__auto__
       (clojure.core/int *buffer-size*)
       mac__508__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.SHA224Digest))
       k__509__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key704)
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
   sha224-hmac*
   "[HMAC] SHA-224 (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha224702
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha224-hmac-file*
   "[HMAC] SHA-224 (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha224702
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha224-hmac-file-bytes
   "[HMAC] SHA-224 (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-sha224702
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   sha224-hmac-file
   "[HMAC] SHA-224 (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-sha224702
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   sha224-hmac-bytes
   "[HMAC] SHA-224 (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-sha224702
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   sha224-hmac
   "[HMAC] SHA-224 (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-sha224702
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
