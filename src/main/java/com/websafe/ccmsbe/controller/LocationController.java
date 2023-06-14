package com.websafe.ccmsbe.controller;

import com.websafe.ccmsbe.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.io.IOException;
import com.maxmind.geoip2.exception.GeoIp2Exception;

@CrossOrigin
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/getUserLocation/{ipAddress}")
    public String getUserLocation(@PathVariable String ipAddress) {
        try {
            return locationService.getUserLocation(ipAddress);
        } catch (IOException | GeoIp2Exception e) {
            return null;
        }
    }

}
