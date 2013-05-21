(ns pandect
  (:require pandect.core)
  (:use base))

(defbench 
  "--md2"     [pandect.core/md2     pandect.core/md2-file]
  "--md5"     [pandect.core/md5     pandect.core/md5-file]
  "--sha1"    [pandect.core/sha1    pandect.core/sha1-file]
  "--sha256"  [pandect.core/sha256  pandect.core/sha256-file]
  "--sha384"  [pandect.core/sha384  pandect.core/sha384-file]
  "--sha512"  [pandect.core/sha512  pandect.core/sha512-file]
  "--adler32" [pandect.core/adler32 pandect.core/adler32-file]
  "--crc32"   [pandect.core/crc32   pandect.core/crc32*-file])
