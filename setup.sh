#!/bin/bash

source .env
set -eo pipefail

function setupProject {
  cd "$1"
  ansible-playbook -i ../hosts --extra-vars \
    "ansible_user=st101 ansible_password=$PASSWORD ansible_ssh_extra_args='-o StrictHostKeyChecking=no'" playbook.yaml
  cd ..
}

function setupDependencies {
  sudo add-apt-repository -y ppa:ansible/ansible
  sudo apt -y install ansible sshpass zip
}

function setupNetwork {
  for i in $(seq -w 01 10); do
    sshpass -p "$PASSWORD" ssh st101@st101vm1"$i".rtb-lab.pl -o StrictHostKeyChecking=no -C "/bin/true";
  done
}

function setupJava {
  curl -s "https://get.sdkman.io" | bash
  source "$HOME/.sdkman/bin/sdkman-init.sh"
  sdk install maven
  sdk install java 20-tem
}

if [[ -n $1 ]]; then
  for project in "$@"; do
    setupProject $project
  done
  exit
fi

setupDependencies
setupNetwork
setupJava
setupProject docker-registry
setupProject kafka
setupProject aerospike
