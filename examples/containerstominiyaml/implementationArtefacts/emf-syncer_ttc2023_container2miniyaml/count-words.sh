#!/bin/bash

files_with_words() {
  find src/main/groovy/containerstominiyaml/yamtl -type f | (while read f; do 
    filename=$(basename "$f");
    echo "${filename%.*} ${filename##*.} $(cpp "$f" | grep -v '^#' | wc -w)";
  done) | sort -k2
}

files_with_words | awk '
  { types[$2] += $3; total += $3; }
  END {
    for (type in types) {
      printf "%s\t%s\n", type, types[type];
    }
    printf "total\t%s\n", total;
  }
'
