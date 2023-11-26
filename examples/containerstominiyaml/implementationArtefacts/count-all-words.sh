#!/bin/bash

find -type f -name count-words.sh -execdir bash -c 'echo $(pwd); ./count-words.sh' \;
