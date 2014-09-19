(ns pandect.utils.buffer)

;; ## Buffer Size

(defmacro with-buffer-size
  "Set buffer size for stream processing."
  [n & body]
  `(binding [*buffer-size* ~n]
     ~@body))

(def ^:dynamic *buffer-size*
  "Buffer Size for Stream Processing"
  2048)
