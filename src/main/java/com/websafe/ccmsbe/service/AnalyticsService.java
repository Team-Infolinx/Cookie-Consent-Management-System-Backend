package com.websafe.ccmsbe.service;

import com.websafe.ccmsbe.entity.Website;
import com.websafe.ccmsbe.repository.ConsentRepository;
import com.websafe.ccmsbe.repository.CookieRepository;
import com.websafe.ccmsbe.repository.WebsiteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@Transactional
public class AnalyticsService {

    @Autowired
    private CookieRepository cookieRepo;
    @Autowired
    private ConsentRepository consentRepo;

    @Autowired
    private WebsiteRepository websiteRepo;

    public Integer getNumOfCookies(String websiteId){
        return cookieRepo.getCookieByWebsiteId(websiteId);
    }

    public Integer getNumOfConsent(String websiteId){
        return consentRepo.getConsentByWebsiteId(websiteId);
    }

    public Integer getNumOfAcceptedConsent(String websiteId){
        return consentRepo.getAcceptedConsentByWebsiteId(websiteId);
    }

    public Float getAcceptanceRate(String websiteId){
        return (((float)consentRepo.getAcceptedConsentByWebsiteId(websiteId)/consentRepo.getConsentByWebsiteId(websiteId))*100);
    }

    public List<Website> getWebsites(){
        return websiteRepo.findAll();
    }
}
