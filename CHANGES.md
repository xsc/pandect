## Changelog: `pandect`

### 0.6.0

- __BREAKING__: BouncyCastle dependency has to be explicitly included.
- __BREAKING__: The implementation of SHA3 included in versions <= 0.5.4 has
  been renamed to Keccak, i.e. `sha3-224` is now `keccak-224`.
- added Digest algorithms:
  - SHA3
  - BLAKE2b

### 0.5.4

- replaces two more occurrences of `count` with `alength` and `String.length()`.
- implements RSA/DSA signing functions for MD2, MD5, SHA-1, SHA-256, SHA-384 and
  SHA-512.

### 0.5.3

- replaces a `count` on byte arrays with `alength` (see #13).
- upgrade main Clojure version to 1.7.0 (add test against 1.6.0).

### 0.5.2

- now available under MIT License.

### 0.5.1

- __BREAKING__ `pandect.utils.buffer` is now at `pandect.buffer`.

### 0.5.0

- __POSSIBLY BREAKING__ Strings are treated as UTF-8 by default.
- __POSSIBLY BREAKING__ Files/InputStreams used as HMAC secrets are read directly as bytes
  (formerly round-trip through String).

### 0.4.1

- fix problems with AOT compilation. (see #8)

### 0.4.0

- create a single namespace `pandect.algo.XXX` for each algorithm `XXX` and import to
  `pandect.core` from there.
- upgrade `org.bouncycastle/bcprov-jdk15on` to version "1.51".
- use Clojure 1.6 internally.

### 0.3.4

- remove accidentally created MD2 HMAC functions

### 0.3.3

- added HMAC algorithms:
  - SipHash-2-4

### 0.3.2

- remove reflection when converting HMAC key to byte array. (thanks to @totakke, PR #1)

### 0.3.1

- added hash algorithms from BouncyCastle:
  - MD4
  - GOST 34.11-94
  - SHA-224
  - SHA3-224
  - SHA3-256
  - SHA3-384
  - SHA3-512
  - RIPEMD-128
  - RIPEMD-160
  - RIPEMD-256
  - RIPEMD-320
  - Tiger (192,3)
  - Whirlpool
- added corresponding HMAC functions.
- added `with-buffer-size` to influence the buffer for stream processing.

### 0.3.0

- added hash-based message authentication code (HMAC) functions:
  - `md5-hmac`, `md5-hmac-bytes`, ...
  - `sha1-hmac`, `sha1-hmac-bytes`, ...
  - `sha256-hmac`, `sha256-hmac-bytes`, ...
  - `sha384-hmac`, `sha384-hmac-bytes`, ...
  - `sha512-hmac`, `sha512-hmac-bytes`, ...
- changed `<hash>*-file` (raw result of file hash) to `<hash>-file*`.

### 0.2.3

- removed unnecessary `java.io.File` creation from file hashing functions

### 0.2.2

- switched to compile-time code generation for hash functions

### 0.2.1

- Adler32 and CRC32 now create a byte array directly, not via a `java.nio.ByteBuffer`

### 0.2.0

- added abstraction (i.e. protocol) `Digest`
- added checksum functions `adler32` and `crc32`

### 0.1.0

- Initial Release
