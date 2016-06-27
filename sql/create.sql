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
  PRIMARY KEY (`id`),
  FOREIGN KEY fk_state(state)
  REFERENCES states(id)
  ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1  COMMENT 'Table containing addresses.';

CREATE TABLE IF NOT EXISTS `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) NOT NULL,
  `company` varchar(64) NOT NULL,
  `init_date` date default NULL COMMENT 'Date became customer',
  `notes` text default NULL,  
  PRIMARY KEY (`id`)  
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1  COMMENT 'Table containing customer info.';

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
  PRIMARY KEY (id),
  FOREIGN KEY fk_customer_id(customer_id)
  REFERENCES customer(id)
  ON DELETE RESTRICT,
  FOREIGN KEY fk_status_code(status_cd)
  REFERENCES contact_status(id)
  ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1  COMMENT 'Customer contact info.';

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




