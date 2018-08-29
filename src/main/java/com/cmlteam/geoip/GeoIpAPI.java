package com.cmlteam.geoip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class of GeoIP service
 * 
 * @author Alexey Kopylov
 *
 */
@SpringBootApplication
public class GeoIpAPI {

	public static void main(String[] args) {
		SpringApplication.run(GeoIpAPI.class, args);
	}
	

}
