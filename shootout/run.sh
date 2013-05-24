#!/bin/bash

ALGORITHMS="md2 md5 sha1 sha256 sha384 sha512"
IMPLS="pandect clj-digest clj-message-digest"
PANDECT_ONLY="adler32 crc32"

function runBenchmark() {
  local i="$1"
  local b="$2"
  local fn="$3"

  if [ ! -z "$fn" ]; then
      lein benchmark "$1" "--$2" --file "$fn"
  else
      lein benchmark "$1" "--$2"
  fi
}

function runBenchmarkAndPrint() {
  local s=`runBenchmark $@ | grep "Execution time mean"`
  local a=($s);
  local n=`printf "%.3g" "${a[4]}"`
  printf " %4s%s  | " $n "${a[5]}" 
}

printf "%-25s | " "Library"
for a in $ALGORITHMS $PANDECT_ONLY; do
    printf " %-7s | " "$a"
done;
echo
echo "--------------------------|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|"

for i in $IMPLS; do
    printf "%-25s | " "__${i}__";
    for a in $ALGORITHMS; do
        runBenchmarkAndPrint $i $a $1
    done
    if [[ "$i" == "pandect" ]]; then
        runBenchmarkAndPrint $i "adler32" $1
        runBenchmarkAndPrint $i "crc32" $1
    else
        echo -n "    -    |     -    |"
    fi
    echo 
done
