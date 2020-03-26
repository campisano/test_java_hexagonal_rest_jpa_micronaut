#!/usr/bin/env bash

set -x -o errexit -o nounset -o pipefail

MVN_VER="3.6.2"
URL="https://archive.apache.org/dist/maven/maven-3/${MVN_VER}/binaries/apache-maven-${MVN_VER}-bin.tar.gz"

curl -s -C - -kLO "${URL}"
tar -C /usr/local -xzf "apache-maven-${MVN_VER}-bin.tar.gz"
rm -f "apache-maven-${MVN_VER}-bin.tar.gz"
ln -s "/usr/local/apache-maven-${MVN_VER}/bin/mvn" /usr/local/bin