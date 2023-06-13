package com.websafe.ccmsbe.controller;
import com.websafe.ccmsbe.entity.Consent;
import com.websafe.ccmsbe.entity.Website;
import com.websafe.ccmsbe.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin
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
    public List<Website> getAllWebsites(){
        return analyticsService.getWebsites();
    }

    @GetMapping("/getWebsiteVisitsCount/{websiteId}")
    public List<Integer> getAllWebsiteVisits(@PathVariable String websiteId){
        return analyticsService.getWebsiteVisits(websiteId);
    }

    @GetMapping("/getWebsiteVisitsdates/{websiteId}")
    public List<Date> getAllWebsiteVisitsDates(@PathVariable String websiteId){
        return analyticsService.getWebsiteVisitsDates(websiteId);
    }

}
