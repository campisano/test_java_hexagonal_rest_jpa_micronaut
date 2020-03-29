#!/usr/bin/env bash

set -x -o errexit -o nounset -o pipefail

TOKEN="$1"

apt-get -qq -y update
apt-get -qq -y install curl git > /dev/null
apt-get -qq -y clean

bash <(curl -s https://codecov.io/bash) -X coveragepy -X xcode -s ./target -t "${TOKEN}"
