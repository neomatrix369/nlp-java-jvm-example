#!/bin/bash

set -e
set -u
set -o pipefail

STEPNAME=${1:-}
SENTENCE=${2:-}
echo "Executing step ${STEPNAME}"
echo "Sentence: ${SENTENCE}"
vh --valohai-token ${VALOHAI_TOKEN}   \
   exec run ${STEPNAME}               \
   --sentence "${SENTENCE}"           \
   --adhoc