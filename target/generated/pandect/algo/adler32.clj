(ns
 pandect.algo.adler32
 "ADLER-32 algorithm implementation"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__753
   (compute-adler32751 [this__228__auto__]))
  (clojure.core/doseq
   [v__229__auto__ [#'G__753 #'compute-adler32751]]
   (clojure.core/alter-meta!
    v__229__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__753
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-adler32751
    [data752]
    (clojure.core/let
     [buf__467__auto__
      data752
      a__468__auto__
      (new java.util.zip.Adler32)]
     (.update
      a__468__auto__
      buf__467__auto__
      0
      (clojure.core/count buf__467__auto__))
     (.getValue a__468__auto__)))
   java.lang.String
   (compute-adler32751
    [data752]
    (clojure.core/let
     [buf__467__auto__
      (.getBytes data752 "UTF-8")
      a__468__auto__
      (new java.util.zip.Adler32)]
     (.update
      a__468__auto__
      buf__467__auto__
      0
      (clojure.core/count buf__467__auto__))
     (.getValue a__468__auto__)))
   java.io.InputStream
   (compute-adler32751
    [data752]
    (clojure.core/let
     [s__469__auto__
      data752
      c__470__auto__
      (clojure.core/int *buffer-size*)
      buf__471__auto__
      (clojure.core/byte-array c__470__auto__)
      a__472__auto__
      (new java.util.zip.Adler32)]
     (clojure.core/loop
      []
      (clojure.core/let
       [r__473__auto__
        (.read s__469__auto__ buf__471__auto__ 0 c__470__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__473__auto__ -1)
        (.update a__472__auto__ buf__471__auto__ 0 r__473__auto__)
        (recur))))
     (.getValue a__472__auto__)))
   java.io.File
   (compute-adler32751
    [data752]
    (clojure.core/with-open
     [data752
      (java.io.FileInputStream. (clojure.java.io/as-file data752))]
     (clojure.core/let
      [s__469__auto__
       data752
       c__470__auto__
       (clojure.core/int *buffer-size*)
       buf__471__auto__
       (clojure.core/byte-array c__470__auto__)
       a__472__auto__
       (new java.util.zip.Adler32)]
      (clojure.core/loop
       []
       (clojure.core/let
        [r__473__auto__
         (.read s__469__auto__ buf__471__auto__ 0 c__470__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__473__auto__ -1)
         (.update a__472__auto__ buf__471__auto__ 0 r__473__auto__)
         (recur))))
      (.getValue a__472__auto__))))))
 [(clojure.core/defn
   adler32*
   "[Hash] ADLER-32 (raw value)"
   [x]
   (compute-adler32751 x))
  (clojure.core/defn
   adler32-file*
   "[Hash] ADLER-32 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-adler32751 x)))
  (clojure.core/defn
   adler32-file-bytes
   "[Hash] ADLER-32 (file path -> byte array)"
   [x]
   (pandect.utils.convert/long->4-bytes
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-adler32751 x))))
  (clojure.core/defn
   adler32-file
   "[Hash] ADLER-32 (file path -> string)"
   [x]
   (pandect.utils.convert/long->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-adler32751 x))))
  (clojure.core/defn
   adler32-bytes
   "[Hash] ADLER-32 (value -> byte array)"
   [x]
   (pandect.utils.convert/long->4-bytes (compute-adler32751 x)))
  (clojure.core/defn
   adler32
   "[Hash] ADLER-32 (value -> string)"
   [x]
   (pandect.utils.convert/long->hex (compute-adler32751 x)))])
