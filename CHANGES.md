## Changelog

### 0.3.0

- CRC-32 implemented directly in Clojure
- switched to compile-time code generation for hash functions
- performance improvements, e.g. computing time (for "Hello, World!" example) reduced for:
  - CRC-32: -50%
  - MD5: -12%

### 0.2.1

- Adler32 and CRC32 now create a byte array directly, not via a `ByteBuffer`

### 0.2.0

- added abstraction (i.e. protocol) `Digest`
- added checksum functions `adler32` and `crc32`

### 0.1.0

- Initial Release
