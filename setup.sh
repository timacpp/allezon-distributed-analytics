#!/bin/bash

source .env
set -eo pipefail

function setup {
  cd "$1"
  ansible-playbook -i ../hosts --extra-vars \
    "ansible_user=st101 ansible_password=$PASSWORD ansible_ssh_extra_args='-o StrictHostKeyChecking=no'" playbook.yaml
  cd ..
}

if [[ -n $1 ]]; then
  setup "$1"
  exit
fi

if [[ -z $(which java) ]]; then
  sudo add-apt-repository ppa:ansible/ansible
  sudo apt -y install ansible sshpass zip
  curl -s "https://get.sdkman.io" | bash
  source "$HOME/.sdkman/bin/sdkman-init.sh"
  sdk install maven
  sdk install java 20-tem
  source "$HOME/.sdkman/bin/sdkman-init.sh"
fi

setup docker-registry
setup kafka