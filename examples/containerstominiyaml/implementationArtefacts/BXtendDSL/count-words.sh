#!/bin/bash

files_with_words() {
  find Containers2MiniYAML/src -type f | (while read f; do 
    filename=$(basename "$f");
    if [[ "$filename" == *.xtend ]]; then
      # A large part of the .xtend is generated from the .bxtend (imports,
      # class decl and overridden method declarations). Following a similar
      # approach to the submitted paper, we only count from after the first
      # overridden method.
      echo "${filename%.*} ${filename##*.} $(cpp "$f" | grep -v '^#' | sed -n '/override/,${/override/d; p}' | wc -w)";
    else
      echo "${filename%.*} ${filename##*.} $(cpp "$f" | grep -v '^#' | wc -w)";
    fi
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
