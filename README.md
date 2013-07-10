# pandect

__pandect__ is a fast and easy-to-use 
[Message Digest](http://en.wikipedia.org/wiki/Message_digest), 
[Checksum](http://en.wikipedia.org/wiki/Checksum) and 
[HMAC](https://en.wikipedia.org/wiki/Hash-based_message_authentication_code)
library for Clojure.

[![Build Status](https://travis-ci.org/xsc/panoptic.png)](https://travis-ci.org/xsc/pandect)
[![endorse](https://api.coderwall.com/xsc/endorsecount.png)](https://coderwall.com/xsc)

## Usage

__Leiningen__ ([via Clojars](https://clojars.org/pandect))

```clojure
[pandect "0.3.0"]
```

__REPL__

```clojure
(use 'pandect.core)

(sha1 "Hello World!")           ;; => "2ef7bde608ce5404e97d5f042f95f89f1c232871"
(sha1-bytes "Hello World!")     ;; => #<byte[] [B@5293b95>
(sha1-file "project.clj")       ;; => "ff3b4565652aeb835edf2715b2a28586929ea4cc"
(sha1-file-bytes "project.clj") ;; => #<byte[] [B@e2606c7>

(sha1-hmac "Hello World!" "secret-key")       ;; => "399fc3d94f6df2213f92fcf2a8b6669279ef7d20"
(sha1-hmac-bytes "Hello World!" "secret-key") ;; => #<byte[] [B@602bd522>
```

If you want to hash a String using a specific encoding, you should create the respective byte array manually:

```clojure
(sha1 "Hällo World!")                          ;; => "f19c05a67c3d0f297b62e868657cf177913ce02a"
(sha1 (.getBytes "Hällo World!" "ISO-8859-1")) ;; => "cfe670bd6845020f5754b19a3c0eee602043eb89"
```

## Supported Algorithms

__Checksum Algorithms:__

- Adler32 (`adler32`, `adler32-bytes`, `adler32-file`, `adler32-file-bytes`)
- CRC32 (`crc32`, `crc32-bytes`, `crc32-file`, `crc32-file-bytes`)

__Insecure Hash Functions__

- MD2 (`md2`, `md2-bytes`, `md2-file`, `md2-file-bytes`)
- MD5 (`md5`, `md5-bytes`, `md5-file`, `md5-file-bytes`)

__Secure Hash Functions (for now)__

- SHA-1 (`sha1`, `sha1-bytes`, `sha1-file`, `sha1-file-bytes`) 
- SHA-256 (`sha256`, `sha256-bytes`, `sha256-file`, `sha256-file-bytes`)
- SHA-384 (`sha384`, `sha384-bytes`, `sha384-file`, `sha384-file-bytes`)
- SHA-512 (`sha512`, `sha512-bytes`, `sha512-file`, `sha512-file-bytes`)

__HMAC Functions__

- MD5 (`md5-hmac`, `md5-hmac-bytes`, `md5-hmac-file`, `md5-hmac-file-bytes`)
- SHA1 (`sha1-hmac`, `sha1-hmac-bytes`, `sha1-hmac-file`, `sha1-hmac-file-bytes`)
- SHA256 (`sha256-hmac`, `sha256-hmac-bytes`, `sha256-hmac-file`, `sha256-hmac-file-bytes`)
- SHA384 (`sha384-hmac`, `sha384-hmac-bytes`, `sha384-hmac-file`, `sha384-hmac-file-bytes`)
- SHA512 (`sha512-hmac`, `sha512-hmac-bytes`, `sha512-hmac-file`, `sha512-hmac-file-bytes`)

`adler32`/`adler32-bytes`, `crc32`/`crc32-bytes`, etc... take one of the following as input
parameter:

- a string
- a byte array
- an input stream
- a `java.io.File` object

`adler32-file`/`adler32-file-bytes`, etc... take the path to a file as input parameter.

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
Core i7 2670QM/2.2GHz/8GB RAM machine with Oracle JDK 1.7.0u21 (64-bit). 

Results obtained using pandect __0.2.2__.

__Input: "Hello, World!"__

Library                   |  md2     |  md5     |  sha1    |  sha256  |  sha384  |  sha512  |  adler32 |  crc32   | 
--------------------------|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|
__pandect__               |  4.79µs  |   827ns  |  1.01µs  |  1.33µs  |  1.83µs  |  1.93µs  |   359ns  |   338ns  | 
__clj-digest__            |  6.34µs  |  2.18µs  |  2.96µs  |  4.10µs  |  6.03µs  |  8.10µs  |     -    |     -    |
__clj-message-digest__    |  29.4µs  |  26.4µs  |  30.1µs  |  52.2µs  |  61.9µs  |  84.3µs  |     -    |     -    |

__Input: 1KB file (times include I/O)__

Library                   |  md2     |  md5     |  sha1    |  sha256  |  sha384  |  sha512  |  adler32 |  crc32   | 
--------------------------|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|
__pandect__               |   141µs  |  9.12µs  |  11.7µs  |  15.7µs  |  14.0µs  |  14.1µs  |  6.52µs  |  6.59µs  | 
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
