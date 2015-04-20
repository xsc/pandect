# pandect

__pandect__ is a fast and easy-to-use
[Message Digest](http://en.wikipedia.org/wiki/Message_digest),
[Checksum](http://en.wikipedia.org/wiki/Checksum) and
[HMAC](https://en.wikipedia.org/wiki/Hash-based_message_authentication_code)
library for Clojure.

[![Build Status](https://travis-ci.org/xsc/pandect.svg?branch=master)](https://travis-ci.org/xsc/pandect)
[![endorse](https://api.coderwall.com/xsc/endorsecount.png)](https://coderwall.com/xsc)

## Usage

__Leiningen__ ([via Clojars](https://clojars.org/pandect))

[![Clojars Project](http://clojars.org/pandect/latest-version.svg)](http://clojars.org/pandect)

__REPL__

```clojure
(require '[pandect.algo.sha1 :refer :all]
         '[clojure.java.io :as io])

(sha1 "Hello World!")           ;; => "2ef7bde608ce5404e97d5f042f95f89f1c232871"
(sha1-bytes "Hello World!")     ;; => #<byte[] [B@5293b95>
(sha1 (io/file "project.clj"))  ;; => "ff3b4565652aeb835edf2715b2a28586929ea4cc"
(sha1-file "project.clj")       ;; => "ff3b4565652aeb835edf2715b2a28586929ea4cc"
(sha1-file-bytes "project.clj") ;; => #<byte[] [B@e2606c7>

(sha1-hmac "Hello World!" "secret-key")       ;; => "399fc3d94f6df2213f92fcf2a8b6669279ef7d20"
(sha1-hmac-bytes "Hello World!" "secret-key") ;; => #<byte[] [B@602bd522>
```

You can tune stream processing using `pandect.buffer/with-buffer-size` (default is 2KB):

```clojure
(require '[pandect.buffer :refer [with-buffer-size]])

(with-open [in (io/input-stream "shootout/data/1mb.txt")]
  (with-buffer-size (* 512 1024)
    (sha256 in)))
```

If you want to hash a String using a specific encoding, you should create the respective byte array manually:

```clojure
(sha1 "Hällo World!")                          ;; => "f19c05a67c3d0f297b62e868657cf177913ce02a"
(sha1 (.getBytes "Hällo World!" "ISO-8859-1")) ;; => "cfe670bd6845020f5754b19a3c0eee602043eb89"
```

The namespace `pandect.core` contains all available algorithms but for better startup/compile times, using algorithm-specific ones is recommended.

## Supported Algorithms

See the [generated documentation](http://xsc.github.io/pandect) for the available
functions and their parameters.

| Checksum | MDx  | SHA      | SHA-3      | RIPEMD     | Others                  |
|----------|------|----------|------------|------------|-------------------------|
| Adler32* | MD2* | SHA-1    | SHA3-224   | RIPEMD-128 | SipHash-2-4<sup>+</sup> |
| CRC-32*  | MD4  | SHA-256  | SHA3-256   | RIPEMD-160 | SipHash-4-8<sup>+</sup> |
|          | MD5  | SHA-384  | SHA3-384   | RIPEMD-256 | Tiger192,3              |
|          |      | SHA-512  | SHA3-512   | RIPEMD-320 | Whirlpool               |

\* not available as MAC<br />
<sup>+</sup> only available as MAC

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
Core i7 2.7GHz/8GB RAM machine with Oracle JDK 1.7.0u67 (64-bit).

Results obtained using pandect __0.5.0__.

__Input: "Hello, World!"__

Library                   |  md2     |  md5     |  sha1    |  sha256  |  sha384  |  sha512  |  adler32 |  crc32   |
--------------------------|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|
__pandect__               |  4.27µs  |   910ns  |  1.07µs  |  1.27µs  |  1.68µs  |  1.74µs  |   302ns  |   315ns  |
__clj-digest__            |  5.92µs  |  2.62µs  |  3.47µs  |  4.36µs  |  5.65µs  |  7.09µs  |     -    |     -    |
__clj-message-digest__    |  28.2µs  |    25µs  |  30.2µs  |  44.7µs  |  66.4µs  |  80.9µs  |     -    |     -    |

__Input: 1KB file (times include I/O)__

Library                   |  md2     |  md5     |  sha1    |  sha256  |  sha384  |  sha512  |  adler32 |  crc32   |
--------------------------|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|
__pandect__               |   122µs  |  13.7µs  |  14.9µs  |  17.9µs  |  16.5µs  |  16.3µs  |  9.19µs  |  9.12µs  |
__clj-digest__            |   128µs  |  19.1µs  |  20.5µs  |  24.8µs  |  25.1µs  |  26.1µs  |     -    |     -    |
__clj-message-digest__    |   259µs  |   149µs  |   155µs  |   160µs  |   203µs  |   221µs  |     -    |     -    |

__Input: 1MB file (times include I/O)__

Library                   |  md2     |  md5     |  sha1    |  sha256  |  sha384  |  sha512  |  adler32 |  crc32   |
--------------------------|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|
__pandect__               |   112ms  |  3.14ms  |  4.57ms  |  6.74ms  |   5.1ms  |  5.11ms  |   582µs  |   833µs  |
__clj-digest__            |   113ms  |  4.18ms  |  5.81ms  |  7.87ms  |  6.15ms  |  6.23ms  |     -    |     -    |
__clj-message-digest__    |   112ms  |  3.07ms  |  4.59ms  |  6.73ms  |  5.17ms  |  5.19ms  |     -    |     -    |

## Contributors

- Yannick Scherer ([@xsc](https://github.com/xsc))
- Toshiki Takeuchi ([@totakke](https://github.com/totakke))
- Jan Stępień ([@jstepien](https://github.com/jstepien))
- Tom Crayford ([@tcrayford](https://github.com/tcrayford))

## License

```
The MIT License (MIT)

Copyright (c) 2014-2015 Yannick Scherer

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
