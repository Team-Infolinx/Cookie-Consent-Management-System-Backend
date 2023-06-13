package com.websafe.ccmsbe.service;

import org.springframework.stereotype.Service;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
public class LocationService {

    private DatabaseReader databaseReader;

    @PostConstruct
    private void init() throws IOException {
        File database = new File("C:\\L2 S2\\Project\\Final-Backend\\Websafe-Backend\\src\\main\\resources\\geoip\\GeoLite2-City.mmdb");
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


