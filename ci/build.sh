#!/usr/bin/env bash

set -x -o errexit -o nounset -o pipefail

# requisites
./ci/install_ci_requisites.sh

# vars
export DOCKER_IMAGE=$(./ci/custom/get_docker_image_build.sh)

# get image
docker pull "${DOCKER_IMAGE}"

# build code isolatedly
docker run \
       --mount type=bind,source="$(pwd)",target=/repository \
       "${DOCKER_IMAGE}" \
       /bin/bash -c \
       'cd /repository; ./ci/custom/internal_build.sh'
