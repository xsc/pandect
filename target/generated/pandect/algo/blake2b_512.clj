(ns
 pandect.algo.blake2b-512
 "BLAKE2B-512 algorithm implementation\n\n(requires `org.bouncycastle/bcprov-jdk15on` to be on the classpath)"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  pandect.utils.bouncy-castle-provider))
(do
 (do
  (clojure.core/defprotocol
   G__1189
   (compute-blake2b-5121186 [data1188]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1189 #'compute-blake2b-5121186]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1189
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-blake2b-5121186
    [data1188]
    (clojure.core/let
     [md__668__auto__
      (java.security.MessageDigest/getInstance "BLAKE2B-512")]
     (.digest md__668__auto__ data1188)))
   java.lang.String
   (compute-blake2b-5121186
    [data1188]
    (clojure.core/let
     [data1188 (.getBytes data1188 "UTF-8")]
     (clojure.core/let
      [md__668__auto__
       (java.security.MessageDigest/getInstance "BLAKE2B-512")]
      (.digest md__668__auto__ data1188)))))
  (clojure.core/extend-protocol
   G__1189
   java.io.InputStream
   (compute-blake2b-5121186
    [data1188]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "BLAKE2B-512")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1188]
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
   (compute-blake2b-5121186
    [data1188]
    (clojure.core/with-open
     [data1188 (clojure.java.io/input-stream data1188)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "BLAKE2B-512")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1188]
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
  'G__1189)
 (do
  (clojure.core/defn
   blake2b-512*
   "[Hash] BLAKE2B-512 (raw value)"
   [x]
   (compute-blake2b-5121186 x))
  (clojure.core/defn
   blake2b-512-file*
   "[Hash] BLAKE2B-512 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-blake2b-5121186 x)))
  (clojure.core/defn
   blake2b-512-bytes
   "[Hash] BLAKE2B-512 (value -> byte array)"
   [x]
   (compute-blake2b-5121186 x))
  (clojure.core/defn
   blake2b-512-file-bytes
   "[Hash] BLAKE2B-512 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-blake2b-5121186 x)))
  (clojure.core/defn
   blake2b-512
   "[Hash] BLAKE2B-512 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-blake2b-5121186 x)))
  (clojure.core/defn
   blake2b-512-file
   "[Hash] BLAKE2B-512 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-blake2b-5121186 x))))))
