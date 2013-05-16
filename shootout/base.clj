(ns base
  (:use criterium.core))

;; ## String Hashing

(def ^:const ^String HELLO_WORLD "Hello, World!")

(defmacro bench-hash-function
  [& fs]
  `(do
     ~@(for [f fs]
         `((println "----------------------------------------------------") 
           (println ~(str "Benchmarking: " f)) 
           (println "----------------------------------------------------") 
           (println ~(str "Hashing \"" HELLO_WORLD "\"")) 
           (bench (~f ~HELLO_WORLD)) 
           (println "----------------------------------------------------")))))

;; ## File Hashing

(defmacro bench-file-hash-function
  [file & fs]
  `(do
     ~@(for [f fs]
         `((println "----------------------------------------------------") 
           (println ~(str "Benchmarking: " f)) 
           (println "----------------------------------------------------") 
           (println ~(str "Hashing File:") ~file) 
           (bench (~f ~file)) 
           (println "----------------------------------------------------")))))

;; ## Test Bench

(defmacro defbench 
  [& hash-fn-map]
  (let [t (gensym "t")
        args (gensym "args")]
    `(defn ~'-main
       [algorithm# & [~t & ~args]]
       (try
         (condp = algorithm#
           ~@(doall 
               (mapcat 
                 (fn [[flag [f1 f2]]] 
                   [flag `(if (and ~f2 (= ~t "--file")) 
                            (bench-file-hash-function (first ~args) ~f2)
                            (bench-hash-function ~f1))])
                 (partition 2 hash-fn-map)))
           (println "Unknown Algorithm: " algorithm#))
         (catch Exception _# nil)))))
