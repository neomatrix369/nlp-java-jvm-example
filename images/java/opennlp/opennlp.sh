#!/bin/bash

set -e
set -u
set -o pipefail

SCRIPT_DIR="$(cd $(dirname "${BASH_SOURCE[0]}")/.. && pwd)"
source ${SCRIPT_DIR}/common.sh

if [[ -f /.dockerenv ]]; then
	SHARED_FOLDER="../shared/"
else
	SHARED_FOLDER="../../../shared/"
fi

mkdir -p ${SHARED_FOLDER}
cd ${SHARED_FOLDER}

FOLDER=apache-opennlp-1.9.1-bin
ARTIFACT=${FOLDER}.tar.gz

echo "Starting to download and unpack ${ARTIFACT}"
downloadArtifact http://apache.mirror.anlx.net/opennlp/opennlp-1.9.1/${ARTIFACT} \
                 ${ARTIFACT}                                 \
                 "${PWD}/${FOLDER}"
cd -
echo "Finished downloading and unpacking ${ARTIFACT} in ${PWD}/${FOLDER}"