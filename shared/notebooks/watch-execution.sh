#!/bin/bash

set -e
set -u
set -o pipefail

EXEC_COUNTER=${1:-}
echo "Watching counter ${EXEC_COUNTER}"
timeout 5 vh --valohai-token ${VALOHAI_TOKEN}   \
   exec watch ${EXEC_COUNTER}
echo "Stopped watching counter ${EXEC_COUNTER}"
echo "Re-run to continue to watch or run the './show-final-result.sh ${EXEC_COUNTER}' to see the final outcome"