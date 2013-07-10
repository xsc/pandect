(ns ^{:doc "Hash Tests for Pandect"
      :author "Yannick Scherer"}
  pandect.hash-test
  (:import java.io.File)
  (:use midje.sweet
        pandect.core
        [clojure.java.io :only [input-stream delete-file]]))

(tabular
  (fact "about hashing 'Hello World!' as a string"
    (?digest "Hello World!") => ?result)
  ?digest       ?result
  adler32       "1c49043e"
  crc32         "1c291ca3"
  md2           "315f7c67223f01fb7cab4b95100e872e"
  md5           "ed076287532e86365e841e92bfc50d8c"
  sha1          "2ef7bde608ce5404e97d5f042f95f89f1c232871"
  sha256        "7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069" 
  sha384        "bfd76c0ebbd006fee583410547c1887b0292be76d582d96c242d2a792723e3fd6fd061f9d5cfd13b8f961358e6adba4a"
  sha512        "861844d6704e8573fec34d967e20bcfef3d424cf48be04e6dc08f2bd58c729743371015ead891cc3cf1c9d34b49264b510751b1ff9e537937bc46b5d6ff4ecc8")

(tabular
  (fact "about hashing 'Hello World!' as an input stream"
    (?digest (input-stream (.getBytes "Hello World!"))) => ?result)
  ?digest       ?result
  adler32       "1c49043e"
  crc32         "1c291ca3"
  md2           "315f7c67223f01fb7cab4b95100e872e"
  md5           "ed076287532e86365e841e92bfc50d8c"
  sha1          "2ef7bde608ce5404e97d5f042f95f89f1c232871"
  sha256        "7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069" 
  sha384        "bfd76c0ebbd006fee583410547c1887b0292be76d582d96c242d2a792723e3fd6fd061f9d5cfd13b8f961358e6adba4a"
  sha512        "861844d6704e8573fec34d967e20bcfef3d424cf48be04e6dc08f2bd58c729743371015ead891cc3cf1c9d34b49264b510751b1ff9e537937bc46b5d6ff4ecc8")

(let [path (.getAbsolutePath (doto (File/createTempFile "pandect" ".txt") (.deleteOnExit)))]
  (tabular
    (fact "about hashing 'Hello World!' from a file"
      (spit path "Hello World!")
      (?digest path) => ?result)
    ?digest       ?result
    adler32-file  "1c49043e"
    crc32-file    "1c291ca3"
    md2-file      "315f7c67223f01fb7cab4b95100e872e"
    md5-file      "ed076287532e86365e841e92bfc50d8c"
    sha1-file     "2ef7bde608ce5404e97d5f042f95f89f1c232871"
    sha256-file   "7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069" 
    sha384-file   "bfd76c0ebbd006fee583410547c1887b0292be76d582d96c242d2a792723e3fd6fd061f9d5cfd13b8f961358e6adba4a"
    sha512-file   "861844d6704e8573fec34d967e20bcfef3d424cf48be04e6dc08f2bd58c729743371015ead891cc3cf1c9d34b49264b510751b1ff9e537937bc46b5d6ff4ecc8"))
