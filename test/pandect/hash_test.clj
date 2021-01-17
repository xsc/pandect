(ns pandect.hash-test
  (:require [pandect.core :as core]
            [clojure.test :refer [deftest are is]]))

(def test-string "Hello World!")

(let [inputs {:string  identity
              :bytes  #(.getBytes ^String % "UTF-8")
              :stream #(java.io.ByteArrayInputStream. (.getBytes ^String % "UTF-8"))}]
  (deftest t-hash-algorithms
    (are [algorithm result]
         (do
           (doseq [[k input] inputs]
             (is (= result (algorithm (input test-string))) (str "input was: " k)))
           true)

         core/adler32        "1c49043e"
         core/crc32          "1c291ca3"
         core/blake2b-160    "e7338d05e5aa2b5e4943389f9475fce2525b92f2"
         core/blake2b-256    "bf56c0728fd4e9cf64bfaf6dabab81554103298cdee5cc4d580433aa25e98b00"
         core/blake2b-384    "53fd759520545fe93270e61bac03b243b686af32ed39a4aa635555be47a89004851d6a13ece00d95b7bdf9910cb71071"
         core/blake2b-512    "54b113f499799d2f3c0711da174e3bc724737ad18f63feb286184f0597e1466436705d6c8e8c7d3d3b88f5a22e83496e0043c44a3c2b1700e0e02259f8ac468e"
         core/gost           "636a32a952ecb9e8529ea759ecff1c33623945e5d868352a7df5f240ea747ded"
         core/md2            "315f7c67223f01fb7cab4b95100e872e"
         core/md4            "b2a5cc34fc21a764ae2fad94d56fadf6"
         core/md5            "ed076287532e86365e841e92bfc50d8c"
         core/sha1           "2ef7bde608ce5404e97d5f042f95f89f1c232871"
         core/sha224         "4575bb4ec129df6380cedde6d71217fe0536f8ffc4e18bca530a7a1b"
         core/sha256         "7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069"
         core/sha384         "bfd76c0ebbd006fee583410547c1887b0292be76d582d96c242d2a792723e3fd6fd061f9d5cfd13b8f961358e6adba4a"
         core/sha512         "861844d6704e8573fec34d967e20bcfef3d424cf48be04e6dc08f2bd58c729743371015ead891cc3cf1c9d34b49264b510751b1ff9e537937bc46b5d6ff4ecc8"
         core/sha3-224       "716596afadfa17cd1cb35133829a02b03e4eed398ce029ce78a2161d"
         core/sha3-256       "d0e47486bbf4c16acac26f8b653592973c1362909f90262877089f9c8a4536af"
         core/sha3-384       "f324cbd421326a2abaedf6f395d1a51e189d4a71c755f531289e519f079b224664961e385afcc37da348bd859f34fd1c"
         core/sha3-512       "32400b5e89822de254e8d5d94252c52bdcb27a3562ca593e980364d9848b8041b98eabe16c1a6797484941d2376864a1b0e248b0f7af8b1555a778c336a5bf48"
         core/keccak-224     "71519a3ec955d57fce5eabf34f64296e80890478eba9e9b36c9c9d5b"
         core/keccak-256     "3ea2f1d0abf3fc66cf29eebb70cbd4e7fe762ef8a09bcc06c8edf641230afec0"
         core/keccak-384     "1f93aefa2bf7e59893b2f29e0a21a58a7e9bbc3f3ce21f3ab3f7d41aa49fa27ca62fd1f42dc99f8497c346a505154b7e"
         core/keccak-512     "75b70545b09569a8d61251b06fc49b520b6ad5322684fd9466836eb143670afdfa25e0403492e0a7dfb7298a9c7e08576bcf26bc9875adfa88e886009cb2fe00"
         core/ripemd128      "24e23e5c25bc06c8aa43b696c1e11669"
         core/ripemd160      "8476ee4631b9b30ac2754b0ee0c47e161d3f724c"
         core/ripemd256      "c298f45ef908ac440513d24bc157efd387948584710236d2b7154b22503bcb51"
         core/ripemd320      "3735014bedbbee608b1f70cc885681c046be778cd177e5d65d973a95f34a24eb5fa79b28409ac3dd"
         core/tiger          "93afa8a33159ad5e9a2e818ca3582bb9247c68c581362de8"
         core/whirlpool      "d4b3ad3619bc70157376c5426b558dbdad30654cf441ab21d7c08e993873256becc80f32448d0218d5b1aab30bf4209e20e3928df002d3cbcfbe501a184680a8")))
