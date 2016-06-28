# -*- mode: ruby -*-
# vi: set ft=ruby :

# Vagrantfile API/syntax version. Don't touch unless you know what you're doing!
VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

  # Every Vagrant virtual environment requires a box to build off of.
  config.vm.box = "ubuntu/trusty32"

  # Create a private network, which allows host-only access to the machine using a specific IP.
  config.vm.network "private_network", ip: "192.168.33.21"
  #config.vm.network :forwarded_port, host: 8888, guest: 80

  # Share an additional folder to the guest VM. The first argument is the path on the host to the actual folder.
  # The second argument is the path on the guest to mount the folder.
  #config.vm.synced_folder "./", "/var/www/html"
  config.vm.synced_folder "./sql", "/vagrant/sql", owner: "vagrant", group: "vagrant"
  #config.vm.synced_folder "./webapps", "/vagrant/webapps", owner: "vagrant", group: "vagrant"

  # Define the bootstrap file: A (shell) script that runs after first setup of your box (= provisioning)
  config.vm.provision :shell, path: "bootstrap.sh"

end
