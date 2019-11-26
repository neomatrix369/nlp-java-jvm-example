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
      echo "~~~ Copying the Java source file from ${VH_INPUTS_DIR} to current location"
      cp ${VH_INPUTS_DIR}/java-program/*.java .      
fi

JAVA_NLP_PROGRAM_NAME=${1:-}
echo "~~~ Run the program to perform the NLP action"
OPENNLP_HOME=apache-opennlp-1.9.1
javac -cp "$(echo ${OPENNLP_HOME}/lib/*.jar | tr ' ' ':')" "${JAVA_NLP_PROGRAM_NAME}.java"
java -cp ".:$(echo ${OPENNLP_HOME}/lib/*.jar | tr ' ' ':')" "${JAVA_NLP_PROGRAM_NAME}"