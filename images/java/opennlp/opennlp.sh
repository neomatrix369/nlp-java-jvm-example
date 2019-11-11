#!/bin/bash

set -e
set -u
set -o pipefail

source ../common.sh

mkdir -p ../shared
cd ../shared
FOLDER=apache-opennlp-1.9.1-bin
ARTIFACT=${FOLDER}.tar.gz

echo "Starting to download and unpack ${ARTIFACT}"
downloadArtifact http://apache.mirror.anlx.net/opennlp/opennlp-1.9.1/${ARTIFACT} \
                 ${ARTIFACT}                                 \
                 "${PWD}/${FOLDER}"
cd -
echo "Finished downloading and unpacking ${ARTIFACT} in ${PWD}/${FOLDER}"