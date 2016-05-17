(ns
 pandect.algo.sha3-256
 "SHA3-256 algorithm implementation\n\n(requires `org.bouncycastle/bcprov-jdk15on` to be on the classpath)"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  pandect.utils.bouncy-castle-provider))
(do
 (do
  (clojure.core/defprotocol G__1327 (compute-sha3-2561325 [data1326]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1327 #'compute-sha3-2561325]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1327
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha3-2561325
    [data1326]
    (clojure.core/let
     [md__668__auto__
      (java.security.MessageDigest/getInstance "SHA3-256")]
     (.digest md__668__auto__ data1326)))
   java.lang.String
   (compute-sha3-2561325
    [data1326]
    (clojure.core/let
     [data1326 (.getBytes data1326 "UTF-8")]
     (clojure.core/let
      [md__668__auto__
       (java.security.MessageDigest/getInstance "SHA3-256")]
      (.digest md__668__auto__ data1326)))))
  (clojure.core/extend-protocol
   G__1327
   java.io.InputStream
   (compute-sha3-2561325
    [data1326]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "SHA3-256")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1326]
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
   (compute-sha3-2561325
    [data1326]
    (clojure.core/with-open
     [data1326 (clojure.java.io/input-stream data1326)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "SHA3-256")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1326]
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
  'G__1327)
 (do
  (clojure.core/defn
   sha3-256*
   "[Hash] SHA3-256 (raw value)"
   [x]
   (compute-sha3-2561325 x))
  (clojure.core/defn
   sha3-256-file*
   "[Hash] SHA3-256 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3-2561325 x)))
  (clojure.core/defn
   sha3-256-bytes
   "[Hash] SHA3-256 (value -> byte array)"
   [x]
   (compute-sha3-2561325 x))
  (clojure.core/defn
   sha3-256-file-bytes
   "[Hash] SHA3-256 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3-2561325 x)))
  (clojure.core/defn
   sha3-256
   "[Hash] SHA3-256 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha3-2561325 x)))
  (clojure.core/defn
   sha3-256-file
   "[Hash] SHA3-256 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-sha3-2561325 x))))))
