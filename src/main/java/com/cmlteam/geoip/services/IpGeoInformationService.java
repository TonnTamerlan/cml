package com.cmlteam.geoip.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmlteam.geoip.dataSets.IpGeoInformation;
import com.cmlteam.geoip.dataSets.IpLocation;
import com.cmlteam.geoip.repository.IpLocationRepository;
import com.cmlteam.geoip.utils.IPv4;

/**
 * The service for working with geoinformation about ip address
 * 
 * @author Alexey Kopylov
 *
 */
@Service
public class IpGeoInformationService {
    
    @Autowired
    private IpLocationRepository geoRepository;
	
	/**
	 * Getting geoinformation about specific ip address
	 * 
	 * @param ip - ip address in dot-decimal notation
	 * @return {@link Optional} with information? or whith <code>null</code> if information was not found
	 */
	public Optional<IpGeoInformation> getGeoInformationByIp(String ip)  {
	    long ipInDecimal = IPv4.convertToDecimal(ip);
	    Optional<IpLocation> optional = geoRepository.getGeoLocationByIp(ipInDecimal);
	    
        if (optional.isPresent()) {
            IpLocation ipLocation = optional.get();
            return Optional.of(new IpGeoInformation(ip, ipLocation.getCityName(), ipLocation.getCountryCode(),
                    ipLocation.getCountryName(), ipInDecimal, ipLocation.getLatitude(), ipLocation.getLongitude(),
                    ipLocation.getRegionName()));
        } else {
            return Optional.ofNullable(null);
        }
	}
}
