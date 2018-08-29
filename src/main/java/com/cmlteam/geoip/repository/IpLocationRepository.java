package com.cmlteam.geoip.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmlteam.geoip.dataSets.IpLocation;

/**
 * The interface presents repository for {@link IpLocation} entity
 * 
 * @author Alexey Kopylov
 *
 */
public interface IpLocationRepository extends CrudRepository<IpLocation, Integer> {
    
    @Query("select ip" + 
           " from IpLocation ip where ip.ipFrom <= :ip and ip.ipTo >= :ip")
    public Optional<IpLocation> getGeoLocationByIp(@Param("ip") long ip);
}
