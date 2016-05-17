(ns
 pandect.algo.blake2b-160
 "BLAKE2B-160 algorithm implementation\n\n(requires `org.bouncycastle/bcprov-jdk15on` to be on the classpath)"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  pandect.utils.bouncy-castle-provider))
(do
 (do
  (clojure.core/defprotocol
   G__1176
   (compute-blake2b-1601166 [data1172]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1176 #'compute-blake2b-1601166]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1176
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-blake2b-1601166
    [data1172]
    (clojure.core/let
     [md__668__auto__
      (java.security.MessageDigest/getInstance "BLAKE2B-160")]
     (.digest md__668__auto__ data1172)))
   java.lang.String
   (compute-blake2b-1601166
    [data1172]
    (clojure.core/let
     [data1172 (.getBytes data1172 "UTF-8")]
     (clojure.core/let
      [md__668__auto__
       (java.security.MessageDigest/getInstance "BLAKE2B-160")]
      (.digest md__668__auto__ data1172)))))
  (clojure.core/extend-protocol
   G__1176
   java.io.InputStream
   (compute-blake2b-1601166
    [data1172]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "BLAKE2B-160")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1172]
     (clojure.core/loop
      []
      (clojure.core/let
       [r__673__auto__
        (.read s__672__auto__ buf__671__auto__ 0 c__670__auto__)]
       (clojure.core/when-not
        (clojure.core/= r__673__auto__ -1)
        (.update md__669__auto__ buf__671__auto__ 0 r__673__auto__)
        (recur))))
     (.digest md__669__auto__)))
   java.io.File
   (compute-blake2b-1601166
    [data1172]
    (clojure.core/with-open
     [data1172 (clojure.java.io/input-stream data1172)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "BLAKE2B-160")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1172]
      (clojure.core/loop
       []
       (clojure.core/let
        [r__673__auto__
         (.read s__672__auto__ buf__671__auto__ 0 c__670__auto__)]
        (clojure.core/when-not
         (clojure.core/= r__673__auto__ -1)
         (.update md__669__auto__ buf__671__auto__ 0 r__673__auto__)
         (recur))))
      (.digest md__669__auto__)))))
  'G__1176)
 (do
  (clojure.core/defn
   blake2b-160*
   "[Hash] BLAKE2B-160 (raw value)"
   [x]
   (compute-blake2b-1601166 x))
  (clojure.core/defn
   blake2b-160-file*
   "[Hash] BLAKE2B-160 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-blake2b-1601166 x)))
  (clojure.core/defn
   blake2b-160-bytes
   "[Hash] BLAKE2B-160 (value -> byte array)"
   [x]
   (compute-blake2b-1601166 x))
  (clojure.core/defn
   blake2b-160-file-bytes
   "[Hash] BLAKE2B-160 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-blake2b-1601166 x)))
  (clojure.core/defn
   blake2b-160
   "[Hash] BLAKE2B-160 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-blake2b-1601166 x)))
  (clojure.core/defn
   blake2b-160-file
   "[Hash] BLAKE2B-160 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-blake2b-1601166 x))))))
