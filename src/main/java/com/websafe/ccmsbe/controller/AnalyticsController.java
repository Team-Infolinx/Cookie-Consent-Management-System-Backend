package com.websafe.ccmsbe.controller;

import com.websafe.ccmsbe.entity.Website;
import com.websafe.ccmsbe.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(value = "api/v1/user")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;


    @GetMapping("/getNoOfCookies/{websiteId}")
    public Integer getNoOfCookies(@PathVariable String websiteId){
        return analyticsService.getNumOfCookies(websiteId);
    }

    @GetMapping("/getNoOfConsent/{websiteId}")
    public Integer getNoOfConsent(@PathVariable String websiteId){
        return analyticsService.getNumOfConsent(websiteId);
    }

    @GetMapping("/getNoOfAcceptedConsent/{websiteId}")
    public Integer getNoOfAcceptedConsent(@PathVariable String websiteId){
        return analyticsService.getNumOfAcceptedConsent(websiteId);
    }

    @GetMapping("/getAcceptanceRate/{websiteId}")
    public Float getAcceptancerate(@PathVariable String websiteId){
        return analyticsService.getAcceptanceRate(websiteId);
    }

    @GetMapping("/getWebsites")
    List<Website> getAllWebsites(){
        return analyticsService.getWebsites();
    }

}
