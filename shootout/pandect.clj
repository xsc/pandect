(ns pandect
  (:require [pandect.algo
             md2 md5 sha1
             sha256 sha384 sha512
             adler32 crc32])
  (:use base))

(defbench
  "--md2"     [pandect.algo.md2/md2         pandect.algo.md2/md2-file]
  "--md5"     [pandect.algo.md5/md5         pandect.algo.md5/md5-file]
  "--sha1"    [pandect.algo.sha1/sha1       pandect.algo.sha1/sha1-file]
  "--sha256"  [pandect.algo.sha256/sha256   pandect.algo.sha256/sha256-file]
  "--sha384"  [pandect.algo.sha384/sha384   pandect.algo.sha384/sha384-file]
  "--sha512"  [pandect.algo.sha512/sha512   pandect.algo.sha512/sha512-file]
  "--adler32" [pandect.algo.adler32/adler32 pandect.algo.adler32/adler32-file]
  "--crc32"   [pandect.algo.crc32/crc32     pandect.algo.crc32/crc32-file])
