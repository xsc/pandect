(ns ^:no-doc pandect.gen.protocol-utils
  (:require [clojure.java.io :as io])
  (:import [java.io File InputStream]))

;; ## Helpers

(defn- generate-protocol
  "Generate a private protocl offering the given functions."
  [P this fns]
  (vector
    `(defprotocol ~P
       ~@(for [{:keys [name args]} fns]
           `(~name [~this ~@args])))
    `(doseq [v# [(var ~P) ~@(map #(list `var (:name %)) fns)]]
       (alter-meta! v# assoc :private true))))

(defn- generate-byte-extends
  "Generate protocol implementations for byte array and String."
  [P this fns]
  (let [impls (filter :bytes fns)]
    `(extend-protocol ~P
       (class (byte-array 0))
       ~@(for [{:keys [name args bytes]} impls]
           `(~name [~this ~@args] ~bytes))
       String
       ~@(for [{:keys [name args bytes]} impls]
           `(~name [~this ~@args]
                   (let [~this (.getBytes ~this "UTF-8")]
                     ~bytes))))))

(defn- generate-stream-extends
  "Generate protocol implementations for InputStream and File."
  [P this fns]
  (let [impls (filter :stream fns)]
    `(extend-protocol ~P
       InputStream
       ~@(for [{:keys [name args stream]} impls]
           `(~name [~this ~@args] ~stream))
       File
       ~@(for [{:keys [name args stream]} impls]
           `(~name [~this ~@args]
                   (with-open [~this (io/input-stream ~this)]
                     ~stream))))))

;; ## Generate Utility

(defn generate
  "Generate a `defprotocol' form, implementing it for the following classes:
   - byte array,
   - `String`,
   - `InputStream`,
   - `File`
   "
  [[this {:keys [protocol-name]}] & fns]
  (let [P (with-meta (or protocol-name (gensym)) {:private true})]
    `(do
       ~@(generate-protocol P this fns)
       ~(generate-byte-extends P this fns)
       ~(generate-stream-extends P this fns)
       (quote ~P))))
