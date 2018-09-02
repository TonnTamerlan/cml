DROP DATABASE IF EXISTS ip2location;
CREATE DATABASE ip2location;
USE ip2location;
CREATE TABLE `ip2location_db5`(
	`id` INT auto_increment not null primary key,
    `ip_from` INT(10) UNSIGNED,
	`ip_to` INT(10) UNSIGNED,
	`country_code` CHAR(2),
	`country_name` VARCHAR(64),
	`region_name` VARCHAR(128),
	`city_name` VARCHAR(128),
	`latitude` double,
	`longitude` DOUBLE,
    INDEX `idx_ip_from` (`ip_from`),
	INDEX `idx_ip_to` (`ip_to`)
	) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;