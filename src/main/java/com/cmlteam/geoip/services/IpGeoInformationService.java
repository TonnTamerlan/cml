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
    
    private final IpLocationRepository geoRepository;
    
    @Autowired
	public IpGeoInformationService(IpLocationRepository geoRepository) {
        this.geoRepository = geoRepository;
    }



    /**
	 * Getting geoinformation about specific ip address
	 * 
	 * @param ip - ip address in dot-decimal notation
	 * @return {@link Optional} with information? or with <code>null</code> if information was not found
	 */
	public Optional<IpGeoInformation> getGeoInformationByIp(String ip)  {
	    long ipInDecimal = IPv4.fromDotDecimalToDecimal(ip);
	    Optional<IpLocation> optional = geoRepository.getGeoLocationByIp(ipInDecimal);
	    
        if (optional.isPresent()) {
            IpLocation ipLocation = optional.get();
            
            return Optional.of(new IpGeoInformation(IPv4.fromDecimalToDotDecimal(ipInDecimal), ipLocation.getCityName(), ipLocation.getCountryCode(),
                    ipLocation.getCountryName(), ipInDecimal, ipLocation.getLatitude(), ipLocation.getLongitude(),
                    ipLocation.getRegionName()));
        } else {
            return Optional.empty();
        }
	}
}
