# Spring MVC Web Application with Bootstrap
This is an example of the web application using the following components:
* [Spring MVC](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html)
* [Maven](http://maven.apache.org/)
* [Bootstrap 3](http://getbootstrap.com/)
* [Admin LTE](https://almsaeedstudio.com/preview) for the front-end look and feel.
* [Vagrant](http://www.vagrantup.com)

## Vagrant setup for Ubuntu 14.04, Apache, MySQL, and phpMyAdmin

```
Note: If you want Tomcat7 included, clone the tomcat branch
```

### Description
This project is a boilerplate for setting up a web server using [Vagrant](http://www.vagrantup.com). 

The Vagrantfile:
* sets up a Ubuntu 14.04 LTS 32bit box
* makes the box accessable by the host at IP ```192.168.33.22```
* syncs the current folder with ```/var/www/html``` inside the box (permanently, in both directions)
* automatically perform all the commands in bootstrap.sh directly after setting up the box for the first time

bootstrap.sh holds your chosen password and your chosen project folder name and does this:

* updates and upgrades Ubuntu 14.04 to the latest version and updates
* creates the project folder inside /var/www/html
* installs Apache 2.4, PHP 5.5, MySQL, PHPMyAdmin, git and Composer
* sets the pre-chosen password for MySQL and PHPMyAdmin
* activates mod_rewrite and add AllowOverride All to the vhost settings
* fixes the missing mcrypt error in phpmyadmin

### Example
* Clone this project to your workspace. 
* Modify the ```PASSWORD``` variable in ```bootstrap.sh``` to your chosen root password. 
* Modify the ```PROJECTFOLDER``` variable in ```bootstrap.sh``` to your chosen project name. 
* Modify the ```config.vm.network "private_network", ip: "192.168.33.22"``` to your chosen IP address.
* Make sure the IP address you choose is outside your network gateway (If your router address is 192.168.1.1, use an address outside of 192.168.1.*).

To create a new virtual machine environment run:
```
vagrant up
```

Make sure you already have the ubuntu/trusty32 loaded.  If not do
```
vagrant box add ubuntu/trusty32
```
* Add your project files to the ```PROJECTFOLDER``` folder.

After that you will have a fully installed box, synced with your local project folder.

###PostScript
To safely stop the box simply run the command:
```vagrant halt```

To destroy the box run:
```vagrant destroy```

If you modify ```bootstrap.sh``` you can reprovision the box by running:
```vagrant provision```

