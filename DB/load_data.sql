# Load file with data from https://lite.ip2location.com/ – DB5.LITE


LOAD DATA LOCAL
	INFILE 'D:\IP2LOCATION-LITE-DB5.CSV' #the path to the file with data
INTO TABLE
	`ip2location_db5` 
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 0 LINES
(ip_from, ip_to, country_code, country_name, region_name, city_name, latitude, longitude);