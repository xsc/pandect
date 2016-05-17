(ns
 pandect.algo.sha3-384
 "SHA3-384 algorithm implementation\n\n(requires `org.bouncycastle/bcprov-jdk15on` to be on the classpath)"
 (:require
  [pandect.buffer :refer [*buffer-size*]]
  [pandect.utils.convert]
  pandect.utils.bouncy-castle-provider))
(do
 (do
  (clojure.core/defprotocol G__1341 (compute-sha3-3841339 [data1340]))
  (clojure.core/doseq
   [v__186__auto__ [#'G__1341 #'compute-sha3-3841339]]
   (clojure.core/alter-meta!
    v__186__auto__
    clojure.core/assoc
    :private
    true))
  (clojure.core/extend-protocol
   G__1341
   (clojure.core/class (clojure.core/byte-array 0))
   (compute-sha3-3841339
    [data1340]
    (clojure.core/let
     [md__668__auto__
      (java.security.MessageDigest/getInstance "SHA3-384")]
     (.digest md__668__auto__ data1340)))
   java.lang.String
   (compute-sha3-3841339
    [data1340]
    (clojure.core/let
     [data1340 (.getBytes data1340 "UTF-8")]
     (clojure.core/let
      [md__668__auto__
       (java.security.MessageDigest/getInstance "SHA3-384")]
      (.digest md__668__auto__ data1340)))))
  (clojure.core/extend-protocol
   G__1341
   java.io.InputStream
   (compute-sha3-3841339
    [data1340]
    (clojure.core/let
     [md__669__auto__
      (java.security.MessageDigest/getInstance "SHA3-384")
      c__670__auto__
      (clojure.core/int *buffer-size*)
      buf__671__auto__
      (clojure.core/byte-array c__670__auto__)
      s__672__auto__
      data1340]
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
   (compute-sha3-3841339
    [data1340]
    (clojure.core/with-open
     [data1340 (clojure.java.io/input-stream data1340)]
     (clojure.core/let
      [md__669__auto__
       (java.security.MessageDigest/getInstance "SHA3-384")
       c__670__auto__
       (clojure.core/int *buffer-size*)
       buf__671__auto__
       (clojure.core/byte-array c__670__auto__)
       s__672__auto__
       data1340]
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
  'G__1341)
 (do
  (clojure.core/defn
   sha3-384*
   "[Hash] SHA3-384 (raw value)"
   [x]
   (compute-sha3-3841339 x))
  (clojure.core/defn
   sha3-384-file*
   "[Hash] SHA3-384 (raw value)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3-3841339 x)))
  (clojure.core/defn
   sha3-384-bytes
   "[Hash] SHA3-384 (value -> byte array)"
   [x]
   (compute-sha3-3841339 x))
  (clojure.core/defn
   sha3-384-file-bytes
   "[Hash] SHA3-384 (file path -> byte array)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (compute-sha3-3841339 x)))
  (clojure.core/defn
   sha3-384
   "[Hash] SHA3-384 (value -> string)"
   [x]
   (pandect.utils.convert/bytes->hex (compute-sha3-3841339 x)))
  (clojure.core/defn
   sha3-384-file
   "[Hash] SHA3-384 (file path -> string)"
   [x]
   (clojure.core/with-open
    [x (clojure.java.io/input-stream (clojure.java.io/file x))]
    (pandect.utils.convert/bytes->hex (compute-sha3-3841339 x))))))
