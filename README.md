# pandect

[![Clojars Project](https://img.shields.io/clojars/v/pandect.svg)](https://clojars.org/pandect)
[![Documentation](https://cljdoc.org/badge/pandect/pandect)](https://cljdoc.org/d/pandect/pandect/CURRENT)
[![CI](https://github.com/xsc/pandect/workflows/CI/badge.svg)](https://github.com/xsc/pandect/actions?query=workflow%3ACI)

__pandect__ is a fast and easy-to-use
[Message Digest](http://en.wikipedia.org/wiki/Message_digest),
[Checksum](http://en.wikipedia.org/wiki/Checksum),
[HMAC](https://en.wikipedia.org/wiki/Hash-based_message_authentication_code)
and [Signature](https://en.wikipedia.org/wiki/Digital_signature)
library for Clojure.

## Usage

Note that - for versions 0.6.0 and up - to use functions based on the
BouncyCastle crypto provider, you have to manually include a version of
the `org.bouncycastle/bcprov-jdk15on` artifact. Additionally, you need to take
special care when trying to create an uberjar
([see here](#uberjars-and-bouncycastle)).

__REPL__

```clojure
(require '[pandect.algo.sha1 :refer :all])

(sha1 "Hello World!")                      ;; => "2ef7bde608ce5404e97d5f042f95f89f1c232871"
(sha1 (.getBytes "Hello World!" "UTF-8"))  ;; => "2ef7bde608ce5404e97d5f042f95f89f1c232871"
(sha1 (File. "project.clj"))               ;; => "ff3b4565652aeb835edf2715b2a28586929ea4cc"
(sha1 (FileInputStream. "project.clj"))    ;; => "ff3b4565652aeb835edf2715b2a28586929ea4cc"

;; more variants
(sha1-bytes "Hello World!")                ;; => #<byte[] [B@5293b95>
(sha1-file "project.clj")                  ;; => "ff3b4565652aeb835edf2715b2a28586929ea4cc"
(sha1-file-bytes "project.clj")            ;; => #<byte[] [B@e2606c7>
```

HMAC functions take an additional key:

```clojure
(sha1-hmac "Hello World!" "secret-key")       ;; => "399fc3d94f6df2213f92fcf2a8b6669279ef7d20"
(sha1-hmac-bytes "Hello World!" "secret-key") ;; => #<byte[] [B@602bd522>
```

Signing functions expect a `java.security.PrivateKey`, while verification works
with `java.security.PublicKey` or `java.security.cert.Certificate`:

```clojure
(sha1-rsa "Hello World!" private-key)                 ;; => "5866..."
(sha1-rsa-verify "Hello World!" "5866..." public-key) ;; => true
```

You can tune stream processing using `pandect.buffer/with-buffer-size` (default
is 2KB):

```clojure
(require '[pandect.buffer :refer [with-buffer-size]])

(with-open [in (io/input-stream "shootout/data/1mb.txt")]
  (with-buffer-size (* 512 1024)
    (sha256 in)))
```

If you want to hash a String using a specific encoding, you should create the
respective byte array manually:

```clojure
(sha1 "Hällo World!")                          ;; => "f19c05a67c3d0f297b62e868657cf177913ce02a"
(sha1 (.getBytes "Hällo World!" "ISO-8859-1")) ;; => "cfe670bd6845020f5754b19a3c0eee602043eb89"
```

The namespace `pandect.core` contains all available algorithms but for better
startup/compile times, using algorithm-specific ones is recommended.

## Supported Algorithms


See the [generated documentation](https://cljdoc.org/d/pandect/pandect/CURRENT)
for the available functions and their parameters.

| Checksum | MDx  | SHA      | SHA-3      | RIPEMD     | BLAKE2      | Others                  |
|----------|------|----------|------------|------------|-------------|-------------------------|
| Adler32* | MD2* | SHA-1    | SHA3-224   | RIPEMD-128 | BLAKE2b-160 | SipHash-2-4<sup>+</sup> |
| CRC-32*  | MD4  | SHA-256  | SHA3-256   | RIPEMD-160 | BLAKE2b-256 | SipHash-4-8<sup>+</sup> |
|          | MD5  | SHA-384  | SHA3-384   | RIPEMD-256 | BLAKE2b-384 | Tiger192,3              |
|          |      | SHA-512  | SHA3-512   | RIPEMD-320 | BLAKE2b-512 | Whirlpool               |

\* not available as MAC<br />
<sup>+</sup> only available as MAC

## Uberjars and BouncyCastle

The BouncyCastle JAR has been signed to prevent tampering - and JCE won't allow
usage of any of its functions if that signature is not present. This poses a
problem when creating an Uberjar since all needed class files will be extracted
from their JARs and repackaged into a single one - removing any existing
signature.

You'll see, for example, the following exceptions in that case:

```
java.security.NoSuchAlgorithmException: Algorithm (...) not available
java.security.NoSuchProviderException: JCE cannot authenticate the provider BC
```

Primarily, this means you have to prevent the BouncyCastle files ending up
within the JAR  which can be accomplished using the `:provided` profile in you
`project.clj`:

```clojure
:profiles
{:provided
 {:dependencies [[org.bouncycastle/bcprov-jdk15on "1.54"]}}
```

Now you can build your uberjar but you have to make sure that the BouncyCastle
JAR is on your classpath when running it, i.e. assuming they are both in the
same directory:

```
java -cp bcprov-jdk15on-1.54.jar:app-standalone.jar app.core
```

Before version 0.6.0, pandect accessed the BouncyCastle functionality directly
(i.e. not via `MessageDigest/getInstance` or `Mac/getInstance` but using actual
constructor calls), meaning these limitations did not apply. It is, thus,
strongly recommended to do it right and upgrade to 0.6.0.

## Benchmarks

You can run benchmarks against `pandect`, `buddy` and `clj-digest` using
[perforate][]:

```sh
lein bench {--table|--edn}
```

[perforate]: https://github.com/davidsantiago/perforate

## License

```
MIT License

Copyright (c) 2014-2021 Yannick Scherer

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
