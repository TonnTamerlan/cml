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
    
    private String canonicalIPv4Representation;
    private String cityName;
    private String countryCode;
    private String countryName;
    private String IPv4;
    private String latitude;
    private String longitude;
    private String regionName;
    
    
    public IpGeoInformation(String canonicalIPv4Representation, String cityName, String countryCode, String countryName,
            long iPv4, double latitude, double longitude, String regionName) {
        super();
        this.canonicalIPv4Representation = canonicalIPv4Representation;
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.countryName = countryName;
        IPv4 = Long.toString(iPv4);
        this.latitude = Double.toString(latitude);
        this.longitude = Double.toString(longitude);
        this.regionName = regionName;
    }
    
    

}
