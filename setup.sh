#!/bin/bash

source .env
set -eo pipefail

sudo apt -y install ansible sshpass zip
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install maven
sdk install java 20-tem

for i in $(seq -w 01 10); do
  sshpass -p "$PASSWORD" ssh st101@st101vm1"$i".rtb-lab.pl -o StrictHostKeyChecking=no -C "/bin/true";
done

cd docker-registry
ansible-playbook -i hosts --extra-vars "ansible_user=st101 ansible_password=$PASSWORD" playbook.yaml
cd ..
