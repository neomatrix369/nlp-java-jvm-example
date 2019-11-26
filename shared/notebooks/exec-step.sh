#!/bin/bash

set -e
set -u
set -o pipefail

STEPNAME=${1:-}
echo "Executing step ${STEPNAME}"
vh --valohai-token ${VALOHAI_TOKEN}   \
   exec run ${STEPNAME}               \
   --adhoc