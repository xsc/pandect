(clojure.core/ns
 pandect.algo.tiger
 (:require
  [pandect.utils [buffer :refer [*buffer-size*]] [convert]]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__613
   (compute-tiger611 [this__228__auto__]))
  (clojure.core/extend-protocol
   G__613
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-tiger611
    [data612]
    (clojure.core/let
     [buf__511__auto__
      data612
      digest__512__auto__
      (new org.bouncycastle.crypto.digests.TigerDigest)
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
   (compute-tiger611
    [data612]
    (clojure.core/let
     [buf__511__auto__
      (.getBytes data612)
      digest__512__auto__
      (new org.bouncycastle.crypto.digests.TigerDigest)
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
   (compute-tiger611
    [data612]
    (clojure.core/let
     [s__515__auto__
      data612
      c__516__auto__
      (clojure.core/int *buffer-size*)
      digest__517__auto__
      (new org.bouncycastle.crypto.digests.TigerDigest)
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
   (compute-tiger611
    [data612]
    (clojure.core/with-open
     [data612
      (java.io.FileInputStream. (clojure.java.io/as-file data612))]
     (clojure.core/let
      [s__515__auto__
       data612
       c__516__auto__
       (clojure.core/int *buffer-size*)
       digest__517__auto__
       (new org.bouncycastle.crypto.digests.TigerDigest)
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
   tiger*
   "[Hash] Tiger (192,3) (raw value)"
   [x]
   (compute-tiger611 x))
  (clojure.core/defn
   tiger-file*
   "[Hash] Tiger (192,3) (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-tiger611 x)))
  (clojure.core/defn
   tiger-file-bytes
   "[Hash] Tiger (192,3) (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-tiger611 x)))
  (clojure.core/defn
   tiger-file
   "[Hash] Tiger (192,3) (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-tiger611 x))))
  (clojure.core/defn
   tiger-bytes
   "[Hash] Tiger (192,3) (value -> byte array)"
   [x]
   (compute-tiger611 x))
  (clojure.core/defn
   tiger
   "[Hash] Tiger (192,3) (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-tiger611 x)))])
(do
 (do
  (clojure.core/defprotocol
   G__617
   (compute-tiger614 [this__382__auto__ key__383__auto__]))
  (clojure.core/extend-protocol
   G__617
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-tiger614
    [data615 key616]
    (clojure.core/let
     [buf__498__auto__
      data615
      k__499__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key616)
      mac__500__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.TigerDigest))
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
   (compute-tiger614
    [data615 key616]
    (clojure.core/let
     [buf__498__auto__
      (.getBytes data615)
      k__499__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key616)
      mac__500__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.TigerDigest))
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
   (compute-tiger614
    [data615 key616]
    (clojure.core/let
     [s__503__auto__
      data615
      c__504__auto__
      (clojure.core/int *buffer-size*)
      mac__505__auto__
      (org.bouncycastle.crypto.macs.HMac.
       (new org.bouncycastle.crypto.digests.TigerDigest))
      k__506__auto__
      (org.bouncycastle.crypto.params.KeyParameter. key616)
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
   (compute-tiger614
    [data615 key616]
    (clojure.core/with-open
     [data615
      (java.io.FileInputStream. (clojure.java.io/as-file data615))]
     (clojure.core/let
      [s__503__auto__
       data615
       c__504__auto__
       (clojure.core/int *buffer-size*)
       mac__505__auto__
       (org.bouncycastle.crypto.macs.HMac.
        (new org.bouncycastle.crypto.digests.TigerDigest))
       k__506__auto__
       (org.bouncycastle.crypto.params.KeyParameter. key616)
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
   tiger-hmac*
   "[HMAC] Tiger (192,3) (raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-tiger614
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   tiger-hmac-file*
   "[HMAC] Tiger (192,3) (file path -> raw value)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-tiger614
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   tiger-hmac-file-bytes
   "[HMAC] Tiger (192,3) (file path -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-tiger614
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))
  (clojure.core/defn
   tiger-hmac-file
   "[HMAC] Tiger (192,3) (file path -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-tiger614
      x
      (pandect.gen.hmac-generator/convert-to-byte-array secret)))))
  (clojure.core/defn
   tiger-hmac-bytes
   "[HMAC] Tiger (192,3) (value -> byte array)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (compute-tiger614
    x
    (pandect.gen.hmac-generator/convert-to-byte-array secret)))
  (clojure.core/defn
   tiger-hmac
   "[HMAC] Tiger (192,3) (value -> string)\nThe secret can be given as any value implementing `pandect.gen.hmac-generator/ByteConvertable`."
   [x secret]
   (pandect.utils.convert/bytes->hex
    (compute-tiger614
     x
     (pandect.gen.hmac-generator/convert-to-byte-array secret))))])
