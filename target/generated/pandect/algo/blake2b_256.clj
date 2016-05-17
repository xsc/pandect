(ns
 pandect.algo.blake2b-256
 "BLAKE2B-256 algorithm implementation\n\n(requires `org.bouncycastle/bcprov-jdk15on` to be on the classpath)"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  pandect.utils.bouncy-castle-provider))
(do
 (do
  (clojure.core/defprotocol
   G__1178
   (compute-blake2b-2561171 [data1173]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1178 #'compute-blake2b-2561171]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1178
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-blake2b-2561171
    [data1173]
    (clojure.core/let
     [md__668__auto__
      (java.security.MessageDigest/getInstance "BLAKE2B-256")]
     (.digest md__668__auto__ data1173)))
   java.lang.String
   (compute-blake2b-2561171
    [data1173]
    (clojure.core/let
     [data1173 (.getBytes data1173 "UTF-8")]
     (clojure.core/let
      [md__668__auto__
       (java.security.MessageDigest/getInstance "BLAKE2B-256")]
      (.digest md__668__auto__ data1173)))))
  (clojure.core/extend-protocol
   G__1178
   java.io.InputStream
   (compute-blake2b-2561171
    [data1173]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "BLAKE2B-256")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1173]
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
   (compute-blake2b-2561171
    [data1173]
    (clojure.core/with-open
     [data1173 (clojure.java.io/input-stream data1173)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "BLAKE2B-256")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1173]
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
  'G__1178)
 (do
  (clojure.core/defn
   blake2b-256*
   "[Hash] BLAKE2B-256 (raw value)"
   [x]
   (compute-blake2b-2561171 x))
  (clojure.core/defn
   blake2b-256-file*
   "[Hash] BLAKE2B-256 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-blake2b-2561171 x)))
  (clojure.core/defn
   blake2b-256-bytes
   "[Hash] BLAKE2B-256 (value -> byte array)"
   [x]
   (compute-blake2b-2561171 x))
  (clojure.core/defn
   blake2b-256-file-bytes
   "[Hash] BLAKE2B-256 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-blake2b-2561171 x)))
  (clojure.core/defn
   blake2b-256
   "[Hash] BLAKE2B-256 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-blake2b-2561171 x)))
  (clojure.core/defn
   blake2b-256-file
   "[Hash] BLAKE2B-256 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-blake2b-2561171 x))))))
