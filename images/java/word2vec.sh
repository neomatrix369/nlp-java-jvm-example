#!/bin/bash

#
# Copyright 2019 Mani Sarkar
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

set -e
set -u
set -o pipefail

cd shared

if [ -d "word2dec-dl4j" ]; then
	echo "word2dec-dl4j already exists, aborting process, remove folder to perform download/update"
	exit -1
fi

mkdir -p "word2dec-dl4j"
cd word2dec-dl4j

git init
git remote add -f origin https://github.com/deeplearning4j/dl4j-examples
git config core.sparseCheckout true
echo "dl4j-examples/src/main/java/org/deeplearning4j/examples/nlp*" >> .git/info/sparse-checkout

git pull --depth=1 origin master


cd ../..