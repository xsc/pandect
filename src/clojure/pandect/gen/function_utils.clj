(ns ^:no-doc pandect.gen.function-utils
  (:require [pandect.gen.core :refer [symbol+]]
            [clojure.java.io :as io]))

;; ## Helpers

(defn- generate-symbol
  [name part suffix]
  (-> name
    (symbol+ part)
    (symbol+ suffix)))

(defn- generate-docstring
  [docstring input-string type]
  (if (= type :none)
    docstring
    (format (or docstring "%s")
            (case type
              :raw    "raw value"
              :bytes  (str input-string " -> byte array")
              :string (str input-string " -> string")))))

(defn- generate-call
  [this
   {:keys [fn args transform wrap-string wrap-bytes]
    :or {transform identity}}
   type]
  (let [call (->> (map #(or (transform %) %) args)
                  (list* fn this))]
    (case type
      (:none :raw) call
      :bytes       (wrap-bytes call)
      :string      (wrap-string call))))

(defn- generate-direct-function
  [this {:keys [name args docstring] :as impl} suffix type]
  `(defn ~(generate-symbol name nil suffix)
     ~(generate-docstring docstring "value" type)
     [~this ~@args]
     ~(generate-call this impl type)))

(defn- generate-file-function
  [this {:keys [name args docstring] :as impl} suffix type]
  `(defn ~(generate-symbol name :file suffix)
     ~(generate-docstring docstring "file path" type)
     [~this ~@args]
     (with-open [~this (io/input-stream (io/file ~this))]
       ~(generate-call this impl type))))

;; ## Generator Utility

(defn generate
  [this & impls]
  `(do
     ~@(mapcat
         (fn [{:keys [suffixes]
               :or {suffixes {:*     :raw
                              :bytes :bytes
                              nil    :string}}
               :as impl}]
           (for [[suffix type] suffixes
                 f [generate-direct-function
                    generate-file-function]]
             (f this impl suffix type)))
         impls)))
