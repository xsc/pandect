# pandect

__pandect__ is a fast and easy-to-use [Message Digest](http://en.wikipedia.org/wiki/Message_digest) and 
[Checksum](http://en.wikipedia.org/wiki/Checksum) library for Clojure.

[![Build Status](https://travis-ci.org/xsc/panoptic.png)](https://travis-ci.org/xsc/pandect)
[![endorse](https://api.coderwall.com/xsc/endorsecount.png)](https://coderwall.com/xsc)

## Usage

__Leiningen__ ([via Clojars](https://clojars.org/pandect))

```clojure
[pandect "0.2.0"]
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

## License

Copyright &copy; 2013 Yannick Scherer

Distributed under the Eclipse Public License, the same as Clojure.
