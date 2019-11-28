#!/bin/bash

set -e
set -u
set -o pipefail

if [[ ! -z ${VH_REPOSITORY_DIR:-} ]]; then
  cd ${VH_REPOSITORY_DIR}
  echo "~~~ Copying the Apache OpenNLP library from ${VH_INPUTS_DIR} to current location"
  cp ${VH_INPUTS_DIR}/apache-opennlp-jar/apache-opennlp-1.9.1-bin.tar.gz .
  tar xvzf apache-opennlp-1.9.1-bin.tar.gz
  echo "~~~ Copying the model from ${VH_INPUTS_DIR} to current location"
  cp ${VH_INPUTS_DIR}/model/*.bin .
fi

JAVA_NLP_PROGRAM_SOURCE_FILE_URL=${1:-}
JAVA_NLP_PROGRAM_SOURCE_FILE_NAME=$(basename "${JAVA_NLP_PROGRAM_SOURCE_FILE_URL}")
JAVA_NLP_PROGRAM_CLASSNAME=${JAVA_NLP_PROGRAM_SOURCE_FILE_NAME%.*}
SENTENCE="${2:-}"
OPENNLP_HOME=${OPENNLP_HOME:-apache-opennlp-1.9.1}

echo "~~~ Run the program to perform the NLP action"
if [[ -e "${JAVA_NLP_PROGRAM_SOURCE_FILE_NAME}" ]]; then
	echo "Found ${JAVA_NLP_PROGRAM_SOURCE_FILE_NAME}"
else
	wget ${JAVA_NLP_PROGRAM_SOURCE_FILE_URL}
fi

javac -cp "$(echo ${OPENNLP_HOME}/lib/*.jar | tr ' ' ':')" \
          "${JAVA_NLP_PROGRAM_SOURCE_FILE_NAME}"
set -x
java -cp ".:$(echo ${OPENNLP_HOME}/lib/*.jar | tr ' ' ':')" \
         "${JAVA_NLP_PROGRAM_CLASSNAME}" "${SENTENCE}"
set +x