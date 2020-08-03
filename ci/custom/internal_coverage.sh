#!/usr/bin/env bash

set -x -o errexit -o nounset -o pipefail

TOKEN="$1"

export DEBIAN_FRONTEND=noninteractive
apt-get -qq -y update
apt-get -qq -y install --no-install-recommends apt-utils > /dev/null
apt-get -qq -y install curl git > /dev/null

bash <(curl -s https://codecov.io/bash) -X coveragepy -X xcode -s ./target -t "${TOKEN}"
