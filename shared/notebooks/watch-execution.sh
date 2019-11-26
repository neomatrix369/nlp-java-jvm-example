#!/bin/bash

set -e
set -u
set -o pipefail

EXEC_COUNTER=${1:-}
echo "Watching counter ${EXEC_COUNTER}"
timeout 5 vh --valohai-token ${VALOHAI_TOKEN}   \
   exec watch ${EXEC_COUNTER}