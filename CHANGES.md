## Changelog: `pandect`

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
