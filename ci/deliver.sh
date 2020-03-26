#!/usr/bin/env bash

set -x -o errexit -o nounset -o pipefail

# requisites
./ci/install_ci_requisites.sh

# vars
export DOCKER_IMAGE=$(./ci/custom/get_docker_image_run.sh)
export PROJECT_NAME=$(./ci/custom/get_project_name.sh)
export PROJECT_VERSION=$(./ci/custom/get_project_version.sh)
export RELEASE_TAG="${PROJECT_NAME}-${PROJECT_VERSION}"

# grant docker login
echo "${DOCKER_PASSWORD}" | docker login --username "${DOCKER_USERNAME}" --password-stdin

# push new tag, locking version and grant git write access
git config user.name "${GIT_USERNAME}"
git config user.email "${GIT_EMAIL}"
git remote set-url origin "https://${GIT_USERNAME}:${GIT_PASSWORD}@${GIT_REPOSITORY_URL}"
git tag ${RELEASE_TAG}
git push origin tag ${RELEASE_TAG}

# build docker image
docker build \
       --build-arg "FROM_IMAGE=${DOCKER_IMAGE=}" \
       --tag "${DOCKER_REPOSITORY}:${RELEASE_TAG}" \
       --file ci/custom/Dockerfile .

# push image tags
docker pull "${DOCKER_REPOSITORY}:${RELEASE_TAG}" &> /dev/null && echo "ERROR: docker image \"${DOCKER_REPOSITORY}:${RELEASE_TAG}\" already exists" && exit 1
docker push "${DOCKER_REPOSITORY}:${RELEASE_TAG}"
docker tag "${DOCKER_REPOSITORY}:${RELEASE_TAG}" "${DOCKER_REPOSITORY}:${PROJECT_NAME}-latest"
docker push "${DOCKER_REPOSITORY}:${PROJECT_NAME}-latest"
