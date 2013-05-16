# pandect

__pandect__ is a [Message Digest](http://en.wikipedia.org/wiki/Message_digest) library for Clojure.

[![Build Status](https://travis-ci.org/xsc/panoptic.png)](https://travis-ci.org/xsc/pandect)
[![endorse](https://api.coderwall.com/xsc/endorsecount.png)](https://coderwall.com/xsc)

## Usage

__Leiningen__ ([via Clojars](https://clojars.org/pandect))

```clojure
[pandect "0.1.0-SNAPSHOT"]
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

These algorithms can be used using `java.security.MessageDigest`:

- MD2 (`md2`)
- MD5 (`md5`)
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

## License

Copyright &copy; Yannick Scherer

Distributed under the Eclipse Public License, the same as Clojure.
