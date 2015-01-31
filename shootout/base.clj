(ns base
  (:use criterium.core))

;; ## String Hashing

(defmacro bench-hash-function
  [string & fs]
  `(do
     ~@(for [f fs]
         `((println "----------------------------------------------------")
           (println ~(str "Benchmarking: " f))
           (println "----------------------------------------------------")
           (println (str "Hashing String: " ~string " (" (~f ~string) ")"))
           (bench (~f ~string))
           (println "----------------------------------------------------")))))

;; ## File Hashing

(defmacro bench-file-hash-function
  [file & fs]
  `(do
     ~@(for [f fs]
         `((println "----------------------------------------------------")
           (println ~(str "Benchmarking: " f))
           (println "----------------------------------------------------")
           (println (str "Hashing File: " ~file " (" (~f ~file) ")"))
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
                   [flag `(case ~t
                            "--file" (bench-file-hash-function (first ~args) ~f2)
                            "--text" (bench-hash-function (first ~args) ~f1)
                            (bench-hash-function "Hello, World!" ~f1))])
                 (partition 2 hash-fn-map)))
           (println "Unknown Algorithm: " algorithm#))
         (catch NullPointerException e# nil)
         (catch Exception e#
           (.printStackTrace e#))))))
