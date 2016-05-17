(ns
 pandect.algo.adler32
 "ADLER-32 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]))
(do
 (do
  (clojure.core/defprotocol G__1185 (compute-adler321167 [data1183]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1185 #'compute-adler321167]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1185
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-adler321167
    [data1183]
    (clojure.core/let
     [buf__898__auto__
      (clojure.core/bytes data1183)
      a__899__auto__
      (new java.util.zip.Adler32)]
     (.update
      a__899__auto__
      buf__898__auto__
      0
      (clojure.core/alength buf__898__auto__))
     (.getValue a__899__auto__)))
   java.lang.String
   (compute-adler321167
    [data1183]
    (clojure.core/let
     [data1183 (.getBytes data1183 "UTF-8")]
     (clojure.core/let
      [buf__898__auto__
       (clojure.core/bytes data1183)
       a__899__auto__
       (new java.util.zip.Adler32)]
      (.update
       a__899__auto__
       buf__898__auto__
       0
       (clojure.core/alength buf__898__auto__))
      (.getValue a__899__auto__)))))
  (clojure.core/extend-protocol
   G__1185
   java.io.InputStream
   (compute-adler321167
    [data1183]
    (clojure.core/let
     [s__900__auto__
      data1183
      c__901__auto__
      (clojure.core/int *buffer-size*)
      buf__902__auto__
      (clojure.core/byte-array c__901__auto__)
      a__903__auto__
      (new java.util.zip.Adler32)]
     (clojure.core/loop
      []
      (clojure.core/let
       [r__904__auto__
        (.read s__900__auto__ buf__902__auto__ 0 c__901__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__904__auto__ -1)
        (.update a__903__auto__ buf__902__auto__ 0 r__904__auto__)
        (recur))))
     (.getValue a__903__auto__)))
   java.io.File
   (compute-adler321167
    [data1183]
    (clojure.core/with-open
     [data1183 (clojure.java.io/input-stream data1183)]
     (clojure.core/let
      [s__900__auto__
       data1183
       c__901__auto__
       (clojure.core/int *buffer-size*)
       buf__902__auto__
       (clojure.core/byte-array c__901__auto__)
       a__903__auto__
       (new java.util.zip.Adler32)]
      (clojure.core/loop
       []
       (clojure.core/let
        [r__904__auto__
         (.read s__900__auto__ buf__902__auto__ 0 c__901__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__904__auto__ -1)
         (.update a__903__auto__ buf__902__auto__ 0 r__904__auto__)
         (recur))))
      (.getValue a__903__auto__)))))
  'G__1185)
 (do
  (clojure.core/defn
   adler32*
   "[Hash] ADLER-32 (raw value)"
   [x]
   (compute-adler321167 x))
  (clojure.core/defn
   adler32-file*
   "[Hash] ADLER-32 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-adler321167 x)))
  (clojure.core/defn
   adler32-bytes
   "[Hash] ADLER-32 (value -> byte array)"
   [x]
   (pandect.utils.convert/long->4-bytes (compute-adler321167 x)))
  (clojure.core/defn
   adler32-file-bytes
   "[Hash] ADLER-32 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/long->4-bytes (compute-adler321167 x))))
  (clojure.core/defn
   adler32
   "[Hash] ADLER-32 (value -> string)"
   [x]
   (pandect.utils.convert/long->hex (compute-adler321167 x)))
  (clojure.core/defn
   adler32-file
   "[Hash] ADLER-32 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/long->hex (compute-adler321167 x))))))
