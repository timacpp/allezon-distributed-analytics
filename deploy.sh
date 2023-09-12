#!/bin/bash

source .env
set -eo pipefail

DOCKER_REGISTRY=st101vm101.rtb-lab.pl

function deploy {
  local container="allezon-$1"
  local image="$container:latest"
  local tag="$DOCKER_REGISTRY/$image"
  sudo docker build -t "$image" "$1"
  sudo docker tag "$image" "$tag"
  sudo docker push "$DOCKER_REGISTRY"/"$image"
  for vm in "${@:2}"; do
    sshpass -p "$PASSWORD" ssh "st101@st101$vm.rtb-lab.pl" "
        sudo docker pull $tag &&
        sudo docker run -d --rm \
          --net=host \
          --name $container \
          --log-driver json-file \
          --log-opt mode=non-blocking \
          --log-opt max-buffer-size=30m \
          --log-opt max-size=750m \
          --log-opt max-file=3 \
          $tag"
  done
}

if [[ -n $1 && -n $2 ]]; then
  mvn --file "$1" clean install -DskipTests
  deploy "$1" "${@:2}"
  exit
fi

mvn --file parent-project clean install -DskipTests
deploy haproxy vm101
deploy user-tags-api vm109 vm110
deploy user-profiles-api vm109 vm110
deploy aggregates-api vm109 vm110
deploy user-profiles-loader vm109 vm110
deploy aggregates-loader vm109 vm110
