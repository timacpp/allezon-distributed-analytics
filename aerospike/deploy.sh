#!/bin/bash

source ../.env
set -eo pipefail

AEROSPIKE_HOSTS=(st101vm101.rtb-lab.pl st101vm101.rtb-lab.pl)
DOCKER_IMAGE=allezon-aerospike:latest
DOCKER_CONTAINER=allezon-aerospike

for host in "${AEROSPIKE_HOSTS[@]}"; do
  docker build -t "$DOCKER_IMAGE" .
  docker push "$DOCKER_REGISTRY"/"$DOCKER_IMAGE"
  sshpass -p "$PASSWORD" ssh "$USER"@"$host" "
    docker pull $DOCKER_REGISTRY/$DOCKER_IMAGE &&
    docker rm $DOCKER_CONTAINER &&
    docker run -d -p 3002:3002 --name $DOCKER_CONTAINER $DOCKER_IMAGE"
done