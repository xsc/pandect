(ns ^{:doc "Digest Creation for Pandect"
      :author "Yannick Scherer"}
  pandect.core
  (:use pandect.hashable)
  (:import [java.io File]))

;; ## Conversion

(def ^:const ^:private ^String hex-chars "0123456789abcdef")

(defn bytes->hex
  "Convert Byte Array to Hex String"
  ^String
  [^"[B" data]
  (let [^StringBuilder sb (StringBuilder. (* 2 (count data)))]
    (doseq [b data]
      (doto sb
        (.append (.charAt hex-chars (bit-shift-right (bit-and b 0xF0) 4)))
        (.append (.charAt hex-chars (bit-and b 0x0F)))))
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
               file-sym (symbol (str sym "-file"))]
           (vector
             `(defn ~byte-sym [e#] (digest e# ~algorithm))
             `(defn ~sym [e#] (bytes->hex (~byte-sym e#)))
             `(defn ~file-byte-sym [path#] (~byte-sym (File. path#)))
             `(defn ~file-sym [path#] (bytes->hex (~file-byte-sym path#))))))))

(create-digest-functions)
