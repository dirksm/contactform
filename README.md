# Spring MVC Web Application with Bootstrap
This is an example of the web application using the following components:
* [Spring MVC](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html)
* [Maven](http://maven.apache.org/)
* [Bootstrap 3](http://getbootstrap.com/)
* [Admin LTE](https://almsaeedstudio.com/preview) for the front-end look and feel.
* [Vagrant](http://www.vagrantup.com)

## Steps to install
* Ensure VirtualBox(4.3) and Vagrant are installed on your workstation.
* Clone this repository.
* Execute ```vagrant up```
* Install apache tomcat version 7.
* Import this project into [Spring Tool Suite (STS)](https://spring.io/tools).
* Right-click on project in STS and select Maven->Update project.
* Add project to Tomcat server
* Start server.  Application should be available at [http://localhost:8080/itsd/].

## Database access
The database is on the Vagrant box and can be accessible by the IP address you set in the Vagrantfile (default to 192.168.33.21).
You can access PHPMyAdmin from 192.168.33.21/phpmyadmin.  The username is root and the password is the value set by the ```DBROOTPASSWORD``` variable below.  The database is ```contact```.

## Vagrant setup for Ubuntu 14.04, Apache, MySQL, and phpMyAdmin

### Description
This project is a boilerplate for setting up a web server using [Vagrant](http://www.vagrantup.com).  This requires the installation of Vagrant
and Oracle's Virtual Box (Version 4.3 is that latest version to work with Vagrant).

More information can be found on the [Vagrant - Getting Started](https://www.vagrantup.com/docs/getting-started/) web page.

The Vagrantfile:
* sets up a Ubuntu 14.04 LTS 32bit box
* makes the box accessable by the host at IP ```192.168.33.21```
* syncs the ```/sql``` folder with ```/vagrant/sql``` inside the box (permanently, in both directions)
* automatically perform all the commands in bootstrap.sh directly after setting up the box for the first time

bootstrap.sh holds your chosen password and your chosen project folder name and does this:

* updates and upgrades Ubuntu 14.04 to the latest version and updates
* creates the sql folder inside /vagrant/sql
* installs Apache 2.4, PHP 5.5, MySQL, PHPMyAdmin
* sets the pre-chosen password for MySQL and PHPMyAdmin
* activates mod_rewrite and add AllowOverride All to the vhost settings
* fixes the missing mcrypt error in phpmyadmin

### Example
* Clone this project to your workspace. 
* Modify the ```DBPASSWORD``` variable in ```bootstrap.sh``` to your chosen root password. 
* Modify the ```DBROOTPASSWORD``` variable in ```bootstrap.sh``` to your chosen project name. 
* Modify the ```config.vm.network "private_network", ip: "192.168.33.21"``` to your chosen IP address.
* Make sure the IP address you choose is outside your network gateway (If your router address is 192.168.1.1, use an address outside of 192.168.1.*).

To create a new virtual machine environment run:
```
vagrant up
```

Make sure you already have the ubuntu/trusty32 loaded.  If not do
```
vagrant box add ubuntu/trusty32
```

###PostScript
To safely stop the box simply run the command:
```vagrant halt```

To destroy the box run:
```vagrant destroy```

If you modify ```bootstrap.sh``` you can reprovision the box by running:
```vagrant provision```

