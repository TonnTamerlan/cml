RESTful GeoIP Web Service
=========================
About
-----
"RESTful GeoIP Web Service" is a web service for fetching data about ip-adress like a country, region, coordinates, etc.

### Prepare to run
#### Database
Web service uses data from  https://lite.ip2location.com/ – DB5.LITE. IPv4 

You need to download csv file with data and to load them to the database. MySQL is used as a database.

###### Create table
```
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
	`latitude` DOUBLE,
	`longitude` DOUBLE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
```
###### Import the database
```
LOAD DATA LOCAL
	INFILE 'path/IP2LOCATION-LITE-DB5.CSV' # where path is the path to the file
INTO TABLE
	`ip2location_db5` 
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 0 LINES
(ip_from, ip_to, country_code, country_name, region_name, city_name, latitude, longitude);
```
