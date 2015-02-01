(ns
 pandect.algo.md2
 "MD2 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__673
   (compute-md2671 [this__228__auto__]))
  (clojure.core/doseq
   [v__229__auto__ [#'G__673 #'compute-md2671]]
   (clojure.core/alter-meta!
    v__229__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__673
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-md2671
    [data672]
    (clojure.core/let
     [md__432__auto__ (java.security.MessageDigest/getInstance "MD2")]
     (.digest md__432__auto__ data672)))
   java.lang.String
   (compute-md2671
    [data672]
    (clojure.core/let
     [md__432__auto__ (java.security.MessageDigest/getInstance "MD2")]
     (.digest md__432__auto__ (.getBytes data672 "UTF-8"))))
   java.io.InputStream
   (compute-md2671
    [data672]
    (clojure.core/let
     [md__433__auto__
      (java.security.MessageDigest/getInstance "MD2")
      c__434__auto__
      (clojure.core/int *buffer-size*)
      buf__435__auto__
      (clojure.core/byte-array c__434__auto__)
      s__436__auto__
      data672]
     (clojure.core/loop
      []
      (clojure.core/let
       [r__437__auto__
        (.read s__436__auto__ buf__435__auto__ 0 c__434__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__437__auto__ -1)
        (.update md__433__auto__ buf__435__auto__ 0 r__437__auto__)
        (recur))))
     (.digest md__433__auto__)))
   java.io.File
   (compute-md2671
    [data672]
    (clojure.core/with-open
     [data672
      (java.io.FileInputStream. (clojure.java.io/as-file data672))]
     (clojure.core/let
      [md__433__auto__
       (java.security.MessageDigest/getInstance "MD2")
       c__434__auto__
       (clojure.core/int *buffer-size*)
       buf__435__auto__
       (clojure.core/byte-array c__434__auto__)
       s__436__auto__
       data672]
      (clojure.core/loop
       []
       (clojure.core/let
        [r__437__auto__
         (.read s__436__auto__ buf__435__auto__ 0 c__434__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__437__auto__ -1)
         (.update md__433__auto__ buf__435__auto__ 0 r__437__auto__)
         (recur))))
      (.digest md__433__auto__))))))
 [(clojure.core/defn
   md2*
   "[Hash] MD2 (raw value)"
   [x]
   (compute-md2671 x))
  (clojure.core/defn
   md2-file*
   "[Hash] MD2 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-md2671 x)))
  (clojure.core/defn
   md2-file-bytes
   "[Hash] MD2 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-md2671 x)))
  (clojure.core/defn
   md2-file
   "[Hash] MD2 (file path -> string)"
   [x]
   (pandect.utils.convert/bytes->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-md2671 x))))
  (clojure.core/defn
   md2-bytes
   "[Hash] MD2 (value -> byte array)"
   [x]
   (compute-md2671 x))
  (clojure.core/defn
   md2
   "[Hash] MD2 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-md2671 x)))])
