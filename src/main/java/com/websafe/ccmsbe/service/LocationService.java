package com.websafe.ccmsbe.service;

import org.springframework.stereotype.Service;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
public class LocationService {

    private DatabaseReader databaseReader;

    @Value("${geoip.database.path}")
    private String geoipDatabasePath;

    @PostConstruct
    private void init() throws IOException {
        File database = new File(geoipDatabasePath);
        databaseReader = new DatabaseReader.Builder(database).build();
    }

    public String getUserLocation(String ipAddress) throws IOException, GeoIp2Exception {
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        CityResponse cityResponse = databaseReader.city(inetAddress);
        String countryName = cityResponse.getCountry().getName();
        String cityName = cityResponse.getCity().getName();
        return countryName + ", " + cityName;
    }
}
