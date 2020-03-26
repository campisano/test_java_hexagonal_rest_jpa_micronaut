#!/usr/bin/env bash

set -o errexit -o nounset -o pipefail

REQS=""

type -P xmlstarlet &>/dev/null || REQS="${REQS} xmlstarlet"

echo "${REQS}"
