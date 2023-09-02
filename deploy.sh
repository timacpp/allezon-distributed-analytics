#!/bin/bash

source .env
set -eo pipefail

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
        sudo docker rm -f $container &&
        sudo docker run -d --net=host --name $container $image"
  done
}

if [[ -n $1 && -n $2 ]]; then
  if [[ $1 == *-api ]]; then
    mvn --file parent-project clean install
  fi
  deploy "$1" "$2"
else
  mvn --file parent-project clean install
  deploy haproxy vm102
  deploy aerospike vm103 vm104
  deploy user-tags-api vm108
  deploy user-profiles-api vm109
  deploy aggregates-api vm110
fi