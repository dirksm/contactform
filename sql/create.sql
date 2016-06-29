CREATE TABLE IF NOT EXISTS `states` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `abbrev` char(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 COMMENT 'State List';


INSERT INTO `states` (`name`, `abbrev`) VALUES
('Alaska', 'AK'),
('Alabama', 'AL'),
('Arizona', 'AZ'),
('Arkansas', 'AR'),
('California', 'CA'),
('Colorado', 'CO'),
('Connecticut', 'CT'),
('Delaware', 'DE'),
('District of Columbia', 'DC'),
('Florida', 'FL'),
('Georgia', 'GA'),
('Hawaii', 'HI'),
('Idaho', 'ID'),
('Illinois', 'IL'),
('Indiana', 'IN'),
('Iowa', 'IA'),
('Kansas', 'KS'),
('Kentucky', 'KY'),
('Louisiana', 'LA'),
('Maine', 'ME'),
('Maryland', 'MD'),
('Massachusetts', 'MA'),
('Michigan', 'MI'),
('Minnesota', 'MN'),
('Mississippi', 'MS'),
('Missouri', 'MO'),
('Montana', 'MT'),
('Nebraska', 'NE'),
('Nevada', 'NV'),
('New Hampshire', 'NH'),
('New Jersey', 'NJ'),
('New Mexico', 'NM'),
('New York', 'NY'),
('North Carolina', 'NC'),
('North Dakota', 'ND'),
('Ohio', 'OH'),
('Oklahoma', 'OK'),
('Oregon', 'OR'),
('Pennsylvania', 'PA'),
('Rhode Island', 'RI'),
('South Carolina', 'SC'),
('South Dakota', 'SD'),
('Tennessee', 'TN'),
('Texas', 'TX'),
('Utah', 'UT'),
('Vermont', 'VT'),
('Virginia', 'VA'),
('Washington', 'WA'),
('West Virginia', 'WV'),
('Wisconsin', 'WI'),
('Wyoming', 'WY');

CREATE TABLE IF NOT EXISTS `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address_1` varchar(64) NOT NULL,
  `address_2` varchar(64) NOT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `zip` varchar(5) DEFAULT NULL,
  `notes` text default NULL,  
  `created` datetime DEFAULT NULL,
  `created_by` varchar(64) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `modified_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY fk_state(state)
  REFERENCES states(id)
  ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1  COMMENT 'Table containing addresses.';

--
-- Triggers `address`
--
DROP TRIGGER IF EXISTS `ADDRESS_CREATE_TIMESTAMP`;
DELIMITER //
CREATE TRIGGER `ADDRESS_CREATE_TIMESTAMP` BEFORE INSERT ON `address`
 FOR EACH ROW SET NEW.CREATED = NOW()
//
DELIMITER ;
DROP TRIGGER IF EXISTS `ADDRESS_UPDATE_TIMESTAMP`;
DELIMITER //
CREATE TRIGGER `ADDRESS_UPDATE_TIMESTAMP` BEFORE UPDATE ON `address`
 FOR EACH ROW SET NEW.MODIFIED = NOW()
//
DELIMITER ;


CREATE TABLE IF NOT EXISTS `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) NOT NULL,
  `company` varchar(64) NOT NULL,
  `init_date` date default NULL COMMENT 'Date became customer',
  `notes` text default NULL,  
  `created` datetime DEFAULT NULL,
  `created_by` varchar(64) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `modified_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)  
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1  COMMENT 'Table containing customer info.';

--
-- Triggers `customer`
--
DROP TRIGGER IF EXISTS `CUSTOMER_CREATE_TIMESTAMP`;
DELIMITER //
CREATE TRIGGER `CUSTOMER_CREATE_TIMESTAMP` BEFORE INSERT ON `customer`
 FOR EACH ROW SET NEW.CREATED = NOW()
//
DELIMITER ;
DROP TRIGGER IF EXISTS `CUSTOMER_UPDATE_TIMESTAMP`;
DELIMITER //
CREATE TRIGGER `CUSTOMER_UPDATE_TIMESTAMP` BEFORE UPDATE ON `customer`
 FOR EACH ROW SET NEW.MODIFIED = NOW()
//
DELIMITER ;


CREATE TABLE IF NOT EXISTS `customer_address` (
  `customer_id` int(11) NOT NULL,
  `address_id` int(11) NOT NULL,
  `date_address_from` date NOT NULL,
  `date_address_to` date DEFAULT NULL,
  `notes` text default NULL,  
  PRIMARY KEY (`customer_id`, `address_id`, `date_address_from`) ,
  FOREIGN KEY fk_customer_id(customer_id)
  REFERENCES customer(id)
  ON DELETE CASCADE,
  FOREIGN KEY fk_address_id(address_id)
  REFERENCES address(id)
  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1  COMMENT 'Table linking address to customer info.';

CREATE TABLE IF NOT EXISTS `contact_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(128) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1  COMMENT 'Contact status info.';

INSERT INTO `contact_status` (`description`) VALUES
('Customer'),
('Vendor'),
('CIO'),
('CEO'),
('President');

CREATE TABLE IF NOT EXISTS `activity_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(128) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1  COMMENT 'Contact activity type info.';

INSERT INTO `activity_type` (`description`) VALUES
('Face-To-Face'),
('Meeting'),
('Phone Call');

CREATE TABLE IF NOT EXISTS `activity_outcome` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(128) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1  COMMENT 'Contact activity outcome info.';

INSERT INTO `activity_outcome` (`description`) VALUES
('Arrange Meeting'),
('Close Deal'),
('Follow-up');
  
CREATE TABLE IF NOT EXISTS `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `status_cd` int(11) NOT NULL,
  `email` varchar(128),
  `website` varchar(128),
  `salutation` int(11) default NULL,
  `contact_name` varchar(128) default NULL,
  `title` varchar(128) default NULL,
  `dept` varchar(128) default NULL,
  `work_phone` varchar(15) default NULL,
  `cell_phone` varchar(15) default NULL,
  `fax` varchar(15) default NULL,
  `notes` text default NULL,  
  `created` datetime DEFAULT NULL,
  `created_by` varchar(64) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `modified_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY fk_customer_id(customer_id)
  REFERENCES customer(id)
  ON DELETE RESTRICT,
  FOREIGN KEY fk_status_code(status_cd)
  REFERENCES contact_status(id)
  ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1  COMMENT 'Customer contact info.';

--
-- Triggers `contact`
--
DROP TRIGGER IF EXISTS `CONTACT_CREATE_TIMESTAMP`;
DELIMITER //
CREATE TRIGGER `CONTACT_CREATE_TIMESTAMP` BEFORE INSERT ON `contact`
 FOR EACH ROW SET NEW.CREATED = NOW()
//
DELIMITER ;
DROP TRIGGER IF EXISTS `CONTACT_UPDATE_TIMESTAMP`;
DELIMITER //
CREATE TRIGGER `CONTACT_UPDATE_TIMESTAMP` BEFORE UPDATE ON `contact`
 FOR EACH ROW SET NEW.MODIFIED = NOW()
//
DELIMITER ;


CREATE TABLE IF NOT EXISTS `contact_activities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contact_id` int(11) NOT NULL,
  `activity_type_id` int(11) NOT NULL,
  `activity_outcome_id` int(11) NOT NULL,
  `activity_date` date NOT NULL,
  `notes` text default NULL,  
  PRIMARY KEY (`id`),
  FOREIGN KEY fk_contact_id(contact_id)
  REFERENCES contact(id)
  ON DELETE CASCADE
  ON UPDATE NO ACTION,
  FOREIGN KEY fk_activity_type(activity_type_id)
  REFERENCES activity_type(id)
  ON DELETE RESTRICT,
  FOREIGN KEY fk_activity_outcome(activity_outcome_id)
  REFERENCES activity_outcome(id)
  ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1  COMMENT 'Contact activity info.';


CREATE TABLE IF NOT EXISTS `users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `username` VARCHAR(50) NOT NULL COMMENT '',
  `password` VARCHAR(255) NOT NULL COMMENT '',
  `first_name` VARCHAR(50) NOT NULL COMMENT '',
  `last_name` VARCHAR(50) NOT NULL COMMENT '',
  `email` VARCHAR(100) NOT NULL COMMENT '',
  `activated` CHAR(1) NOT NULL DEFAULT 'N' COMMENT '',
  `new_password_key` VARCHAR(128) NULL DEFAULT NULL COMMENT '',
  `new_password_requested` DATETIME NULL DEFAULT NULL COMMENT '',
  `position` VARCHAR( 64 ) NOT NULL COMMENT 'User Position',
  `last_login` DATETIME DEFAULT NULL COMMENT '',
  `created` DATETIME DEFAULT NULL,
  `modified` DATETIME DEFAULT NULL,
  `created_by` varchar(64) DEFAULT NULL,
  `modified_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)  COMMENT 'User Table')
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

DROP TRIGGER IF EXISTS `USERS_CREATE_TIMESTAMP`;
DELIMITER //
CREATE TRIGGER `USERS_CREATE_TIMESTAMP` BEFORE INSERT ON `users`
 FOR EACH ROW SET NEW.CREATED = NOW()
//
DELIMITER ;
DROP TRIGGER IF EXISTS `USERS_UPDATE_TIMESTAMP`;
DELIMITER //
CREATE TRIGGER `USERS_UPDATE_TIMESTAMP` BEFORE UPDATE ON `users`
 FOR EACH ROW SET NEW.modified = NOW()
//
DELIMITER ;

CREATE TABLE IF NOT EXISTS `user_types` (
  `user_type_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `title` VARCHAR(255) NOT NULL COMMENT '',
  PRIMARY KEY (`user_type_id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

INSERT INTO `user_types` (`title`) VALUES
('admin'),
('user');


CREATE TABLE IF NOT EXISTS `user_roles` (
  `username` VARCHAR(50) NOT NULL COMMENT 'username',
  `role` VARCHAR(255) NOT NULL COMMENT 'role from user_types',
  PRIMARY KEY (`username`, `role`)  COMMENT '')
ENGINE = InnoDB
COMMENT 'Maps user to user roles.  Used for security realm';

INSERT INTO `users` (`username`, `password`, `first_name`, `last_name`, `email`, `activated`, `new_password_key`, `new_password_requested`, `last_login`, `created_by`) VALUES
('dirksm', '6ca13d52ca70c883e0f0bb101e425a89e8624de51db2d2392593af6a84118090', 'Michael', 'Dirks', 'dirksm@email.net', 'Y', NULL, NULL, NULL, 'dirksm');
INSERT INTO `users` (`username`, `password`, `first_name`, `last_name`, `email`, `activated`, `new_password_key`, `new_password_requested`, `last_login`, `created_by`) VALUES
('testuser', '6ca13d52ca70c883e0f0bb101e425a89e8624de51db2d2392593af6a84118090', 'Test', 'User', 'test@email.net', 'Y', NULL, NULL, NULL, 'dirksm');

INSERT INTO `user_roles` (`username`, `role`) VALUES
('dirksm', 'admin'),
('dirksm', 'user'),
('testuser', 'user');


