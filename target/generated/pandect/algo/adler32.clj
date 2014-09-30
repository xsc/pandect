(clojure.core/ns
 pandect.algo.adler32
 (:require
  [pandect.utils [buffer :refer [*buffer-size*]] [convert]]
  [pandect.gen [hash-generator] [hmac-generator]]))
(do
 (do
  (clojure.core/defprotocol
   G__746
   (compute-adler32744 [this__228__auto__]))
  (clojure.core/extend-protocol
   G__746
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-adler32744
    [data745]
    (clojure.core/let
     [buf__464__auto__
      data745
      a__465__auto__
      (new java.util.zip.Adler32)]
     (.update
      a__465__auto__
      buf__464__auto__
      0
      (clojure.core/count buf__464__auto__))
     (.getValue a__465__auto__)))
   java.lang.String
   (compute-adler32744
    [data745]
    (clojure.core/let
     [buf__464__auto__
      (.getBytes data745)
      a__465__auto__
      (new java.util.zip.Adler32)]
     (.update
      a__465__auto__
      buf__464__auto__
      0
      (clojure.core/count buf__464__auto__))
     (.getValue a__465__auto__)))
   java.io.InputStream
   (compute-adler32744
    [data745]
    (clojure.core/let
     [s__466__auto__
      data745
      c__467__auto__
      (clojure.core/int *buffer-size*)
      buf__468__auto__
      (clojure.core/byte-array c__467__auto__)
      a__469__auto__
      (new java.util.zip.Adler32)]
     (clojure.core/loop
      []
      (clojure.core/let
       [r__470__auto__
        (.read s__466__auto__ buf__468__auto__ 0 c__467__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__470__auto__ -1)
        (.update a__469__auto__ buf__468__auto__ 0 r__470__auto__)
        (recur))))
     (.getValue a__469__auto__)))
   java.io.File
   (compute-adler32744
    [data745]
    (clojure.core/with-open
     [data745
      (java.io.FileInputStream. (clojure.java.io/as-file data745))]
     (clojure.core/let
      [s__466__auto__
       data745
       c__467__auto__
       (clojure.core/int *buffer-size*)
       buf__468__auto__
       (clojure.core/byte-array c__467__auto__)
       a__469__auto__
       (new java.util.zip.Adler32)]
      (clojure.core/loop
       []
       (clojure.core/let
        [r__470__auto__
         (.read s__466__auto__ buf__468__auto__ 0 c__467__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__470__auto__ -1)
         (.update a__469__auto__ buf__468__auto__ 0 r__470__auto__)
         (recur))))
      (.getValue a__469__auto__))))))
 [(clojure.core/defn
   adler32*
   "[Hash] ADLER-32 (raw value)"
   [x]
   (compute-adler32744 x))
  (clojure.core/defn
   adler32-file*
   "[Hash] ADLER-32 (file path -> raw value)"
   [x]
   (clojure.core/with-open
    [x (java.io.FileInputStream. (clojure.core/str x))]
    (compute-adler32744 x)))
  (clojure.core/defn
   adler32-file-bytes
   "[Hash] ADLER-32 (file path -> byte array)"
   [x]
   (pandect.utils.convert/long->4-bytes
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-adler32744 x))))
  (clojure.core/defn
   adler32-file
   "[Hash] ADLER-32 (file path -> string)"
   [x]
   (pandect.utils.convert/long->hex
    (clojure.core/with-open
     [x (java.io.FileInputStream. (clojure.core/str x))]
     (compute-adler32744 x))))
  (clojure.core/defn
   adler32-bytes
   "[Hash] ADLER-32 (value -> byte array)"
   [x]
   (pandect.utils.convert/long->4-bytes (compute-adler32744 x)))
  (clojure.core/defn
   adler32
   "[Hash] ADLER-32 (value -> string)"
   [x]
   (pandect.utils.convert/long->hex (compute-adler32744 x)))])
