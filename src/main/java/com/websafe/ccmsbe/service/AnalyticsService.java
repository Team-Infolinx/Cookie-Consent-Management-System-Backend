package com.websafe.ccmsbe.service;

import com.websafe.ccmsbe.entity.Website;
import com.websafe.ccmsbe.repository.ConsentRepository;
import com.websafe.ccmsbe.repository.CookieRepository;
import com.websafe.ccmsbe.repository.WebsiteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class AnalyticsService {

    @Autowired
    private CookieRepository cookieRepo;
    @Autowired
    private ConsentRepository consentRepo;


    public Boolean isNumber(String str){
        try {
            double number = Double.parseDouble(str);
            return number != 0.0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Integer getNumOfCookies(String websiteId){
        int numOfCookies= cookieRepo.getCookieByWebsiteId(websiteId);
        if (isNumber(String.valueOf(numOfCookies))){
            return numOfCookies;
        }
        else{
                return 0;
            }
        }


    public Integer getNumOfConsent(String websiteId){
        int numOfConsent= consentRepo.getConsentByWebsiteId(websiteId);
        if (isNumber(String.valueOf(numOfConsent))){
            return numOfConsent;
        }
        else{
            return 0;
        }
    }

    public Integer getNumOfAcceptedConsent(String websiteId){
        int numOfAcceptedConsent= consentRepo.getAcceptedConsentByWebsiteId(websiteId);
        if (isNumber(String.valueOf(numOfAcceptedConsent))){
            return numOfAcceptedConsent;
        }
        else{
            return 0;
        }
    }

    public Float getAcceptanceRate(String websiteId){
        int numOfAcceptedConsent= consentRepo.getAcceptedConsentByWebsiteId(websiteId);
        int numOfTotalConsent=consentRepo.getConsentByWebsiteId(websiteId);
        if (isNumber(String.valueOf(numOfAcceptedConsent)) && isNumber(String.valueOf(numOfTotalConsent))){
            String formattedRate=String.format("%.2f",(((float)numOfAcceptedConsent/numOfTotalConsent)*100));
            return Float.parseFloat(formattedRate);
        }
        else{
            return (float)0;
        }
    }


    public List<Integer> getWebsiteVisits(String websiteId){
        return consentRepo.getConsentCountGroupByDate(websiteId);
    }

    public List<Date>getWebsiteVisitsDates(String websiteId){
        return consentRepo.getConsentDatesByWebsiteId(websiteId);
    }


}
