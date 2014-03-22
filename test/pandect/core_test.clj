(ns ^{:doc "Core Tests for Pandect"
      :author "Yannick Scherer"}
  pandect.core-test
  (:use midje.sweet
        [pandect.utils.convert :only [bytes->hex]]))

(tabular
  (fact "about bytes->hex"
    (let [^"[B" d (byte-array (map byte ?data))]
      (bytes->hex d) => ?result))
  ?data                ?result
  [0 0 0 0]            "00000000"
  [-1 7 16 64]         "ff071040"
  [-5 127 4 15]        "fb7f040f")
