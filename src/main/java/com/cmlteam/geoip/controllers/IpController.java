package com.cmlteam.geoip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmlteam.geoip.dataSets.IpGeoInformation;
import com.cmlteam.geoip.exceptions.NotFoundException;
import com.cmlteam.geoip.exceptions.NotValidRequestException;
import com.cmlteam.geoip.services.IpGeoInformationService;
import com.cmlteam.geoip.utils.IPv4;

/**
 * Controller for getting geoinformation of ip address 
 * 
 * @author Alexey Kopylov
 *
 */
@RestController
public class IpController {
	
	private final IpGeoInformationService ipService;
	
	
	@Autowired
	public IpController(IpGeoInformationService ipService) {
        this.ipService = ipService;
    }



    @RequestMapping("/geoip/{ip}")
	public IpGeoInformation getGeoInformation(@PathVariable String ip) {
        if (!IPv4.isIPv4InDotDecimal(ip)) {
            String errorMessage = ip + " is not valid IPv4 address";
            throw new NotValidRequestException(errorMessage);
        }
        
        return ipService.getGeoInformationByIp(ip).orElseThrow(()->new NotFoundException("Information about " + ip + " is not found"));
	    
	}

}
