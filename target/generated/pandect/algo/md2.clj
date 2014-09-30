(clojure.core/ns
 pandect.algo.md2
 (:require
  [pandect.utils [buffer :refer [*buffer-size*]] [convert]]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__666
   (compute-md2664 [this__228__auto__]))
  (clojure.core/extend-protocol
   G__666
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md2664
    [data665]
    (clojure.core/let
     [md__429__auto__ (java.security.MessageDigest/getInstance "MD2")]
     (.digest md__429__auto__ data665)))
   java.lang.String
   (compute-md2664
    [data665]
    (clojure.core/let
     [md__429__auto__ (java.security.MessageDigest/getInstance "MD2")]
     (.digest md__429__auto__ (.getBytes data665))))
   java.io.InputStream
   (compute-md2664
    [data665]
    (clojure.core/let
     [md__430__auto__
      (java.security.MessageDigest/getInstance "MD2")
      c__431__auto__
      (clojure.core/int *buffer-size*)
      buf__432__auto__
      (clojure.core/byte-array c__431__auto__)
      s__433__auto__
      data665]
     (clojure.core/loop
      []
      (clojure.core/let
       [r__434__auto__
        (.read s__433__auto__ buf__432__auto__ 0 c__431__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__434__auto__ -1)
        (.update md__430__auto__ buf__432__auto__ 0 r__434__auto__)
        (recur))))
     (.digest md__430__auto__)))
   java.io.File
   (compute-md2664
    [data665]
    (clojure.core/with-open
     [data665
      (java.io.FileInputStream. (clojure.java.io/as-file data665))]
     (clojure.core/let
      [md__430__auto__
       (java.security.MessageDigest/getInstance "MD2")
       c__431__auto__
       (clojure.core/int *buffer-size*)
       buf__432__auto__
       (clojure.core/byte-array c__431__auto__)
       s__433__auto__
       data665]
      (clojure.core/loop
       []
       (clojure.core/let
        [r__434__auto__
         (.read s__433__auto__ buf__432__auto__ 0 c__431__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__434__auto__ -1)
         (.update md__430__auto__ buf__432__auto__ 0 r__434__auto__)
         (recur))))
      (.digest md__430__auto__))))))
 [(clojure.core/defn
   md2*
   "[Hash] MD2 (raw value)"
   [x]
   (compute-md2664 x))
  (clojure.core/defn
   md2-file*
   "[Hash] MD2 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-md2664 x)))
  (clojure.core/defn
   md2-file-bytes
   "[Hash] MD2 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-md2664 x)))
  (clojure.core/defn
   md2-file
   "[Hash] MD2 (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-md2664 x))))
  (clojure.core/defn
   md2-bytes
   "[Hash] MD2 (value -> byte array)"
   [x]
   (compute-md2664 x))
  (clojure.core/defn
   md2
   "[Hash] MD2 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-md2664 x)))])
