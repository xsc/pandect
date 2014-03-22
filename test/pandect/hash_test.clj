(ns ^{:doc "Hash Tests for Pandect"
      :author "Yannick Scherer"}
  pandect.hash-test
  (:import java.io.File)
  (:use midje.sweet
        pandect.core
        [clojure.java.io :only [input-stream delete-file]]))

(tabular
  (tabular
    (fact "about hash functions."
          (?digest ?input) => ?result)
    ?digest       ?result
    adler32       "1c49043e"
    crc32         "1c291ca3"
    gost          "636a32a952ecb9e8529ea759ecff1c33623945e5d868352a7df5f240ea747ded"
    md2           "315f7c67223f01fb7cab4b95100e872e"
    md4           "b2a5cc34fc21a764ae2fad94d56fadf6"
    md5           "ed076287532e86365e841e92bfc50d8c"
    sha1          "2ef7bde608ce5404e97d5f042f95f89f1c232871"
    sha224        "4575bb4ec129df6380cedde6d71217fe0536f8ffc4e18bca530a7a1b"
    sha256        "7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069"
    sha384        "bfd76c0ebbd006fee583410547c1887b0292be76d582d96c242d2a792723e3fd6fd061f9d5cfd13b8f961358e6adba4a"
    sha512        "861844d6704e8573fec34d967e20bcfef3d424cf48be04e6dc08f2bd58c729743371015ead891cc3cf1c9d34b49264b510751b1ff9e537937bc46b5d6ff4ecc8"
    sha3           "3ea2f1d0abf3fc66cf29eebb70cbd4e7fe762ef8a09bcc06c8edf641230afec0"
    sha3-224       "71519a3ec955d57fce5eabf34f64296e80890478eba9e9b36c9c9d5b"
    sha3-384       "1f93aefa2bf7e59893b2f29e0a21a58a7e9bbc3f3ce21f3ab3f7d41aa49fa27ca62fd1f42dc99f8497c346a505154b7e"
    sha3-512       "75b70545b09569a8d61251b06fc49b520b6ad5322684fd9466836eb143670afdfa25e0403492e0a7dfb7298a9c7e08576bcf26bc9875adfa88e886009cb2fe00"
    ripemd128      "24e23e5c25bc06c8aa43b696c1e11669"
    ripemd160      "8476ee4631b9b30ac2754b0ee0c47e161d3f724c"
    ripemd256      "c298f45ef908ac440513d24bc157efd387948584710236d2b7154b22503bcb51"
    ripemd320      "3735014bedbbee608b1f70cc885681c046be778cd177e5d65d973a95f34a24eb5fa79b28409ac3dd"
    tiger          "93afa8a33159ad5e9a2e818ca3582bb9247c68c581362de8"
    whirlpool      "d4b3ad3619bc70157376c5426b558dbdad30654cf441ab21d7c08e993873256becc80f32448d0218d5b1aab30bf4209e20e3928df002d3cbcfbe501a184680a8")
  ?input
  "Hello World!"
  (input-stream (.getBytes "Hello World!"))
  (let [f (doto (File/createTempFile "pandect" ".txt") (.deleteOnExit))]
    (spit f "Hello World!")
    f))
