package com.cmlteam.geoip.dataSets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The class presents the entity for ip location which is stored in the database
 * 
 * @author Alexey Kopylov
 *
 */
@Entity
@Table(name="ip2Location_db5")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IpLocation {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Column(name="ip_from")
    private long ipFrom;
    
    @Column(name="ip_to")
    private long ipTo;
    
    @Column(name="country_code")
    private String countryCode;
    
    @Column(name="country_name")
    private String countryName;
    
    @Column(name="region_name")
    private String regionName;
    
    @Column(name="city_name")
    private String cityName;
    
    private double latitude;
    private double longitude;

}
