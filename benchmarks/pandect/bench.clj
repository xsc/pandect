(ns pandect.bench
  (:require [perforate.core :refer [defcase defgoal]]
            [clojure.java.io :as io]
            [clojure.string :as string]))

;; ## Inputs

(def +short-string+
  {:setup (constantly "The quick brown fox jumps over the lazy dog.")})

(def +long-string+
  {:setup (constantly
           (->> (repeat 1024 +short-string+)
                (string/join "\n")))})

(def +small-file+
  {:setup (constantly (io/file (io/resource "data/1kb.txt")))})

(def +large-file+
  {:setup (constantly (io/file (io/resource "data/1mb.txt")))})

;; ## Macro

(defmacro defbench-single
  "Create hash benchmark for a specific input."
  [sym input fns]
  `(do
     (let [input# ~input]
       (defgoal ~sym ~(name sym)
         :setup (comp vector (:setup input#))))
     ~@(for [[k f] fns]
         `(do
            (require '~(symbol (namespace f)))
            (let [f# ~f]
              (defcase ~sym ~k
                [in#]
                (f# in#)))))))

(defn- suffix
  [sym s]
  (symbol (str sym "-" s)))

(defmacro defbench
  [sym {:keys [all strings files]}]
  `(do
     ~(when-let [fns (merge all strings)]
        `(do
           (defbench-single ~(suffix sym "short-string")
             +short-string+
             ~fns)
           (defbench-single ~(suffix sym "long-string")
             +long-string+
             ~fns)))
     ~(when-let [fns (merge all files)]
        `(do
           (defbench-single ~(suffix sym "small-file")
             +small-file+
             ~fns)
           (defbench-single ~(suffix sym "large-file")
             +large-file+
             ~fns)))))
