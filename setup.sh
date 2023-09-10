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
  for project in "$@"; do
    setup $project
  done
  exit
fi

for i in $(seq -w 01 10); do
  sshpass -p "$PASSWORD" ssh st101@st101vm1"$i".rtb-lab.pl -o StrictHostKeyChecking=no -C "/bin/true";
done

setup docker-registry
setup kafka
setup aerospike

if [[ -z $(which java) ]]; then
  sudo add-apt-repository -y ppa:ansible/ansible
  sudo apt -y install ansible sshpass zip
  curl -s "https://get.sdkman.io" | bash
  source "$HOME/.sdkman/bin/sdkman-init.sh"
  sdk install maven
  sdk install java 20-tem
  source "$HOME/.sdkman/bin/sdkman-init.sh"
fi
