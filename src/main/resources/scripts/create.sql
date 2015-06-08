-- Dumping database structure for test
CREATE SCHEMA IF NOT EXISTS `appdirect`;
USE `appdirect`;


DROP TABLE IF EXISTS `user`;
CREATE TABLE  `user`
(
  primaryKey int(10) unsigned NOT NULL,
  firstName varchar(45) DEFAULT NULL,
  lastName varchar(45) DEFAULT NULL,
  email varchar(45) DEFAULT NULL,
  username varchar(45) NOT NULL,
  password varchar(80) NOT NULL,
  openId varchar(80) NOT NULL,
  isEnabled boolean NOT NULL,
  isExpired boolean NOT NULL,
  isLocked boolean NOT NULL,
  created timestamp DEFAULT CURRENT_TIMESTAMP,
  modified timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (primaryKey)
);

INSERT INTO `user`
VALUES (1,'Admin','User','admin@email.com','adminuser','d9acd48369a1a20280c8c3b6921d8d8a','open_id_admin',1,0,0,'2015-01-01 00:00:00','2015-01-01 00:00:00');


DROP TABLE IF EXISTS `role`;
CREATE TABLE  `role`
(
  primaryKey int(10) unsigned NOT NULL,
  roleName varchar(45) NOT NULL,
  roleDescription varchar(80) DEFAULT NULL,
  PRIMARY KEY (primaryKey)
);

INSERT INTO `role`
VALUES (1,'ROLE_ADMIN','System administrator');

DROP TABLE IF EXISTS `user_role_association`;
CREATE TABLE  `user_role_association`
(
  primaryKey int(10) unsigned NOT NULL,
  authorityUser int(10) NOT NULL,
  authorityRole int(10) NOT NULL,
  PRIMARY KEY (primaryKey)
);