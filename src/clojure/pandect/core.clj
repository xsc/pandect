(ns ^{:doc "Digest Creation for Pandect"
      :author "Yannick Scherer"}
  pandect.core
  (:require [pandect.utils.buffer :refer [*buffer-size*]]
            [potemkin :refer [import-vars]]
            [pandect.codegen :refer [algorithms]]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

;; ## Re-export all algorithms

(defmacro ^:private reexport-algos
  []
  (list*
    `import-vars
    (for [algo (algorithms)
          :let [ns (symbol (str 'pandect.algo. algo))]]
      (do
        (require ns)
        (list*
          ns
          (for [[sym _] (ns-publics (symbol (str 'pandect.algo. algo)))
                :when (.startsWith (str sym) (str algo))]
            sym))))))

(reexport-algos)

(import-vars pandect.utils.buffer/with-buffer-size)
