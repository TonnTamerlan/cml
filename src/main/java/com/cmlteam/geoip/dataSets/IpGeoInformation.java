package com.cmlteam.geoip.dataSets;

import lombok.Data;

/**
 * The class presents an object which contains geoinformation about specific ip address 
 * 
 * @author Alexey Kopylov
 *
 */
@Data
public class IpGeoInformation {
    
    private final String canonicalIPv4Representation;
    private final String cityName;
    private final String countryCode;
    private final String countryName;
    private final String IPv4;
    private final String latitude;
    private final String longitude;
    private final String regionName;
    
    
    public IpGeoInformation(String canonicalIPv4Representation, String cityName, String countryCode, String countryName,
            long iPv4, double latitude, double longitude, String regionName) {
        super();
        this.canonicalIPv4Representation = canonicalIPv4Representation;
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.IPv4 = Long.toString(iPv4);
        this.latitude = Double.toString(latitude);
        this.longitude = Double.toString(longitude);
        this.regionName = regionName;
    }
    
    

}
