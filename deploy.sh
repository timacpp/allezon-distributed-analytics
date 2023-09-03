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
        sudo docker images &&
        sudo docker rm -f $container &&
        sudo docker run -d --net=host --name $container $tag"
  done
}

if [[ -n $1 && -n $2 ]]; then
  deploy "$1" "${@:2}"
  exit
fi

mvn --file parent-project clean install -DskipTests
deploy haproxy vm101
deploy aerospike vm102 vm103 vm104
deploy user-tags-api vm108
deploy user-profiles-api vm109
deploy aggregates-api vm110