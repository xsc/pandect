(ns ^{:doc "Digest Creation for Pandect"
      :author "Yannick Scherer"}
  pandect.core
  (:use pandect.hashable)
  (:import [java.io File]))

(set! *warn-on-reflection* true)

;; ## Conversion

(def ^:const ^:private ^String hex-chars "0123456789abcdef")

(defn bytes->hex
  "Convert Byte Array to Hex String"
  ^String
  [^"[B" data]
  (let [^StringBuilder sb (StringBuilder. (* 2 (count data)))
        len (count data)]
    (loop [i 0]
      (when (< i len)
        (let [b (aget data i)]
          (doto sb
            (.append (.charAt hex-chars (bit-shift-right (bit-and b 0xF0) 4)))
            (.append (.charAt hex-chars (bit-and b 0x0F)))))))
    (.toString sb)))

;; ## Available Algorithms

(def ^:private algorithms
  {'md5    "MD5"     'md2    "MD2"
   'sha1   "SHA-1"   'sha256 "SHA-256"
   'sha384 "SHA-384" 'sha512 "SHA-512"})

(defmacro ^:private create-digest-functions
  []
  `(do
     ~@(for [[sym algorithm] algorithms]
         (let [byte-sym (symbol (str sym "-bytes"))
               file-byte-sym (symbol (str sym "-file-bytes"))
               file-sym (symbol (str sym "-file"))
               path-sym (vary-meta (gensym "path") assoc :tag `String)]
           (vector
             `(defn ~byte-sym [e#] (digest e# ~algorithm))
             `(defn ~sym [e#] (bytes->hex (digest e# ~algorithm)))
             `(defn ~file-byte-sym [~path-sym] (digest (File. ~path-sym) ~algorithm))
             `(defn ~file-sym [path#] (bytes->hex (~file-byte-sym path#))))))))

(create-digest-functions)
