(ns
 pandect.algo.adler32
 "ADLER-32 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1156 (compute-adler321154 [data1155]))
  (clojure.core/doseq
   [v__166__auto__ [#'G__1156 #'compute-adler321154]]
   (clojure.core/alter-meta!
    v__166__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1156
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-adler321154
    [data1155]
    (clojure.core/let
     [buf__835__auto__
      data1155
      a__836__auto__
      (new java.util.zip.Adler32)]
     (.update
      a__836__auto__
      buf__835__auto__
      0
      (clojure.core/count buf__835__auto__))
     (.getValue a__836__auto__)))
   java.lang.String
   (compute-adler321154
    [data1155]
    (clojure.core/let
     [data1155 (.getBytes data1155 "UTF-8")]
     (clojure.core/let
      [buf__835__auto__
       data1155
       a__836__auto__
       (new java.util.zip.Adler32)]
      (.update
       a__836__auto__
       buf__835__auto__
       0
       (clojure.core/count buf__835__auto__))
      (.getValue a__836__auto__)))))
  (clojure.core/extend-protocol
   G__1156
   java.io.InputStream
   (compute-adler321154
    [data1155]
    (clojure.core/let
     [s__837__auto__
      data1155
      c__838__auto__
      (clojure.core/int *buffer-size*)
      buf__839__auto__
      (clojure.core/byte-array c__838__auto__)
      a__840__auto__
      (new java.util.zip.Adler32)]
     (clojure.core/loop
      []
      (clojure.core/let
       [r__841__auto__
        (.read s__837__auto__ buf__839__auto__ 0 c__838__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__841__auto__ -1)
        (.update a__840__auto__ buf__839__auto__ 0 r__841__auto__)
        (recur))))
     (.getValue a__840__auto__)))
   java.io.File
   (compute-adler321154
    [data1155]
    (clojure.core/with-open
     [data1155 (clojure.java.io/input-stream data1155)]
     (clojure.core/let
      [s__837__auto__
       data1155
       c__838__auto__
       (clojure.core/int *buffer-size*)
       buf__839__auto__
       (clojure.core/byte-array c__838__auto__)
       a__840__auto__
       (new java.util.zip.Adler32)]
      (clojure.core/loop
       []
       (clojure.core/let
        [r__841__auto__
         (.read s__837__auto__ buf__839__auto__ 0 c__838__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__841__auto__ -1)
         (.update a__840__auto__ buf__839__auto__ 0 r__841__auto__)
         (recur))))
      (.getValue a__840__auto__)))))
  'G__1156)
 (do
  (clojure.core/defn
   adler32*
   "[Hash] ADLER-32 (raw value)"
   [x]
   (compute-adler321154 x))
  (clojure.core/defn
   adler32-file*
   "[Hash] ADLER-32 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-adler321154 x)))
  (clojure.core/defn
   adler32-bytes
   "[Hash] ADLER-32 (value -> byte array)"
   [x]
   (pandect.utils.convert/long->4-bytes (compute-adler321154 x)))
  (clojure.core/defn
   adler32-file-bytes
   "[Hash] ADLER-32 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/long->4-bytes (compute-adler321154 x))))
  (clojure.core/defn
   adler32
   "[Hash] ADLER-32 (value -> string)"
   [x]
   (pandect.utils.convert/long->hex (compute-adler321154 x)))
  (clojure.core/defn
   adler32-file
   "[Hash] ADLER-32 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/long->hex (compute-adler321154 x))))))
