-- Dumping database structure for test
CREATE SCHEMA IF NOT EXISTS `appdirect`;
USE `appdirect`;

-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
DROP TABLE IF EXISTS `user`;
CREATE TABLE  `user`
(
  primaryKey int(10) unsigned NOT NULL AUTO_INCREMENT,
  openId varchar(255) NOT NULL,
  firstName varchar(80) DEFAULT NULL,
  lastName varchar(80) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  isEnabled boolean NOT NULL,
  isExpired boolean NOT NULL,
  PRIMARY KEY (primaryKey)
);

INSERT INTO `user`
VALUES (1,'d9acd48369a1a20280c8c3b6921d8d8a','Admin','User','admin@email.com',1,0);

-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
DROP TABLE IF EXISTS `company`;
CREATE TABLE  `company`
(
  primaryKey int(10) unsigned NOT NULL AUTO_INCREMENT,
  country varchar(20) DEFAULT NULL,
  name varchar(255) NOT NULL,
  email varchar(255) DEFAULT NULL,
  phone varchar(40) DEFAULT NULL,
  website varchar(255) DEFAULT NULL,
  uuid varchar(255) NOT NULL,
  PRIMARY KEY (primaryKey)
);

INSERT INTO `company`
VALUES
	(1,'United States','AppDirect','appdirect@email.com','555-555-5555','www.appdirect.com','d15bb36e-5fb5-11e0-8c3c-00262d2cda03'),
	(2,'Canada','PBSC','pbsc@email.com','555-567-8910','www.pbsc.com','d15bb36e-5fb5-11e0-7777-00262d2cda03'),
	(3,'Germany','Good Games','goodgames@email.com','210-987-6543','www.goodgames.com','d15bb36e-5fb5-11e0-8888-00262d2cda03'),
	(4,'Russia','Kaspersky','kaspersky@email.com','555-333-4444','www.kaspersky.com','d15bb36e-5fb5-11e0-9999-00262d2cda03');