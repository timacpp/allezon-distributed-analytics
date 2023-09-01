#!/bin/bash

source .env
set -eo pipefail

function deploy {
  local container="allezon-$1"
  local image="$container:latest"
  local tag="$DOCKER_REGISTRY/$image"
  docker build -t "$image" "$1"
  docker tag "$image" "$tag"
  docker push "$DOCKER_REGISTRY"/"$image"
  for host in "${@:2}"; do
    sshpass -p "$PASSWORD" ssh "$USER@$USER$host.rtb-lab.pl" "
        docker pull $tag &&
        docker rm -f $container &&
        docker run -d --net=host --name $container $image"
  done
}

mvn --file parent-project clean install
docker run -d -p 5000:5000 --name registry registry:2 2> /dev/null
deploy haproxy vm102
deploy aerospike vm103 vm104
deploy user-tags-api vm108 # vm105 vm106
deploy user-profiles-api vm109 # vm107 vm108
deploy aggregates-api vm110 # vm109 vm110