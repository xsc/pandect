(ns ^{:doc "Digest Creation for Pandect"
      :author "Yannick Scherer"}
  pandect.core
  (:require [pandect.utils.buffer :refer [*buffer-size*]]
            [potemkin :refer [import-vars]]
            [pandect.codegen :refer [algorithms algorithm-namespace]]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

;; ## Re-export all algorithms

(defn- collect-algorithm-functions
  [ns algorithm-symbol]
  (require ns)
  (let [s (name algorithm-symbol)]
    (->> (ns-publics ns)
         (map (comp first))
         (filter #(.startsWith (name %) s)))))

(defmacro ^:private reexport-algos
  []
  `(do
     (import-vars pandect.utils.buffer/with-buffer-size)
     ~@(for [algo (algorithms)
             :let [ns (algorithm-namespace algo)]]
         `(do
            (require '~ns)
            (import-vars
              [~ns ~@(collect-algorithm-functions ns algo)])))))

(reexport-algos)
