# pandect

__pandect__ is a fast and easy-to-use [Message Digest](http://en.wikipedia.org/wiki/Message_digest) and 
[Checksum](http://en.wikipedia.org/wiki/Checksum) library for Clojure.

[![Build Status](https://travis-ci.org/xsc/panoptic.png)](https://travis-ci.org/xsc/pandect)
[![endorse](https://api.coderwall.com/xsc/endorsecount.png)](https://coderwall.com/xsc)

## Usage

__Leiningen__ ([via Clojars](https://clojars.org/pandect))

```clojure
[pandect "0.2.1"]
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

__Checksum Algorithms:__

- Adler32 (`adler32`)
- CRC32 (`crc32`)

__Insecure Hash Functions__

- MD2 (`md2`)
- MD5 (`md5`)

__Secure Hash Functions (for now)__

- SHA-1 (`sha1`) 
- SHA-256 (`sha256`)
- SHA-384 (`sha384`)
- SHA-512 (`sha512`)

The following functions are created per algorithm identifier (see in parrens above):

- `<algorithm>`: takes an entity, creates the hexadecimal string representation of its hash
  (e.g. `(md5 "Hello World")`).
- `<algorithm>-bytes`: takes an entity, creates a byte array containing the desired hash 
  (e.g. `(md5-bytes "Hello World")`).
- `<algorithm>-file`: create string representing the hexadecimal hash of the file at 
  the given path (e.g. `(md5-file "project.clj")`).
- `<algorithm>-file-bytes`: create byte array containing the hash of the file at the
  given path (e.g `(md5-file-bytes "project.clj")`).

## Benchmarks

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

## Benchmark Results

Benchmarks are run using [Criterium](https://github.com/hugoduncan/criterium) on an Intel 
Core i7 2670QM/2.2GHz/8GB RAM machine with Oracle JDK 1.7.0u21.

__Input: "Hello, World!"__

Library                   |  md2     |  md5     |  sha1    |  sha256  |  sha384  |  sha512  |  adler32 |  crc32   | 
--------------------------|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|
__pandect__               |  4.79µs  |   827ns  |  1.01µs  |  1.33µs  |  1.83µs  |  1.93µs  |   576ns  |   586ns  | 
__clj-digest__            |  6.34µs  |  2.18µs  |  2.96µs  |  4.10µs  |  6.03µs  |  8.10µs  |     -    |     -    |
__clj-message-digest__    |  29.4µs  |  26.4µs  |  30.1µs  |  52.2µs  |  61.9µs  |  84.3µs  |     -    |     -    |

__Input: 1KB file (times include I/O)__

Library                   |  md2     |  md5     |  sha1    |  sha256  |  sha384  |  sha512  |  adler32 |  crc32   | 
--------------------------|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|
__pandect__               |   141µs  |  9.12µs  |  11.7µs  |  15.7µs  |  14.0µs  |  14.1µs  |  6.79µs  |  6.85µs  | 
__clj-digest__            |   145µs  |  14.4µs  |  16.6µs  |  22.0µs  |  21.3µs  |  23.7µs  |     -    |     -    |
__clj-message-digest__    |   309µs  |   178µs  |   184µs  |   203µs  |   221µs  |   237µs  |     -    |     -    |

__Input: 8KB file (times include I/O)__

Library                   |  md2     |  md5     |  sha1    |  sha256  |  sha384  |  sha512  |  adler32 |  crc32   | 
--------------------------|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|
__pandect__               |  1.07ms  |  34.1µs  |  52.4µs  |  82.2µs  |  64.1µs  |  64.2µs  |  15.3µs  |  16.4µs  | 
__clj-digest__            |  1.07ms  |  42.4µs  |  60.4µs  |  91.7µs  |  74.7µs  |  77.4µs  |     -    |     -    |
__clj-message-digest__    |  1.24ms  |   201µs  |   223µs  |   272µs  |   278µs  |   300µs  |     -    |     -    |

__Input: 1MB file (times include I/O)__

Library                   |  md2     |  md5     |  sha1    |  sha256  |  sha384  |  sha512  |  adler32 |  crc32   | 
--------------------------|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|
__pandect__               |   135ms  |  3.66ms  |  5.95ms  |  9.76ms  |  7.34ms  |  7.36ms  |  1.33ms  |  1.49ms  | 
__clj-digest__            |   135ms  |  4.24ms  |  6.69ms  |  10.5ms  |  8.04ms  |  7.99ms  |     -    |     -    |
__clj-message-digest__    |   134ms  |  3.83ms  |  6.19ms  |  10.0ms  |  7.66ms  |  7.57ms  |     -    |     -    |

## License

Copyright &copy; 2013 Yannick Scherer

Distributed under the Eclipse Public License, the same as Clojure.
