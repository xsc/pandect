# pandect

__pandect__ is a fast and easy-to-use [Message Digest](http://en.wikipedia.org/wiki/Message_digest) library for Clojure.

[![Build Status](https://travis-ci.org/xsc/panoptic.png)](https://travis-ci.org/xsc/pandect)
[![endorse](https://api.coderwall.com/xsc/endorsecount.png)](https://coderwall.com/xsc)

## Usage

__Leiningen__ ([via Clojars](https://clojars.org/pandect))

```clojure
[pandect "0.1.0"]
```

__REPL__

```clojure
(use 'pandect.core)

(md5 "Hello World!")
;; => "ed076287532e86365e841e92bfc50d8c"

(sha256 "Hello World!")
;; => "7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069"

(sha1-file "project.clj")
;; => "cc5fdf7b312356fafa580b40429f97beda7e749f"
```

## Supported Algorithms

These algorithms can be used (see `java.security.MessageDigest`):

- MD2 (`md2`) __DO NOT USE FOR CRYPTOGRAPHIC HASHES!__
- MD5 (`md5`) __DO NOT USE FOR CRYPTOGRAPHIC HASHES!__
- SHA-1 (`sha1`)
- SHA-256 (`sha256`)
- SHA-384 (`sha384`)
- SHA-512 (`sha512`)

The following functions are created per algorithm identifier (see in parrens above):

- `<algorithm>`: create string representing the hexadecimal value of the desired hash
  (e.g. `(md5 "Hello World")`).
- `<algorithm>-bytes`: create byte array containing the desired hash 
  (e.g. `(md5-bytes "Hello World")`).
- `<algorithm>-file`: create string representing the hexadecimal hash of the file at 
  the given path (e.g. `(md5-file "project.clj")`).
- `<algorithm>-file-bytes`: create byte array containing the hash of the file at the
  given path (e.g `(md5-file-bytes "project.clj")`).

## Benchmarks

### Running Benchmarks

You can run benchmarks on the following Message Digest implementations:

- [pandect](https://github.com/xsc/pandect) (obviously)
- [clj-digest](https://github.com/tebeka/clj-digest) 1.4.3
- [clj-message-digest](https://github.com/ray1729/clj-message-digest) 1.0.0

Use the following command:

```
lein benchmark {pandect|clj-digest|clj-message-digest}\
               {--md2|--md5|--sha1|--sha256|--sha384|--sha512}\
               [--file <Path>]
```

If a file is supplied a benchmark will be run hashing its contents; if no file
is given, the simple string "Hello, World!" will be hashed.

### Results

Benchmarks are run using [Criterium](https://github.com/hugoduncan/criterium) on an Intel 
Core i7 2670QM/2.2GHz/8GB RAM machine with Oracle JDK 1.7.0u21. `quick-bench` is the benchmarking function that
obtained the following results.

__String ("Hello, World!", 13 bytes) to hexadecimal Digest String__

Library                |   MD2   |   MD5   |  SHA-1  | SHA-256 | SHA-384 | SHA-512
:----------------------|:-------:|:-------:|:-------:|:-------:|:-------:|:-------:
__pandect__            | 4.90us  |  754ns  |  941ns  | 1.18us  | 1.62us  | 1.55us
__clj-digest__         | 6.34us  | 2.35us  | 3.07us  | 4.30us  | 6.60us  | 8.77us
__clj-message-digest__ | 29.6us  | 25.8us  | 30.0us  | 48.1us  | 71.9us  | 89.9us

__File (8811 bytes), given as path, to hexadecimal Digest String__

Library                |   MD2   |   MD5   |  SHA-1  | SHA-256 | SHA-384 | SHA-512
:----------------------|:-------:|:-------:|:-------:|:-------:|:-------:|:-------:
__pandect__            | 1.26ms  | 39.2us  | 59.8us  | 95.9us  | 70.9us  | 73.8us
__clj-digest__         | 1.26ms  | 49.6us  | 70.6us  |  105us  | 84.0us  | 88.7us
__clj-message-digest__ | 1.44ms  |  210us  |  238us  |  292us  |  284us  |  320us

## License

Copyright &copy; 2013 Yannick Scherer

Distributed under the Eclipse Public License, the same as Clojure.
