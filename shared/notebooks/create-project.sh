#!/bin/bash

set -e
set -u
set -o pipefail

PROJECT_NAME=${1:-}
vh --valohai-token ${VALOHAI_TOKEN} \
   project create                   \
   -n ${PROJECT_NAME} --yes