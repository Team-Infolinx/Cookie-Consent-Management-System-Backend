package com.websafe.ccmsbe.service;

import com.websafe.ccmsbe.entity.Consent;
import com.websafe.ccmsbe.entity.CookieCategory;
import com.websafe.ccmsbe.entity.Website;
import com.websafe.ccmsbe.exception.WebsiteNotFoundException;
import com.websafe.ccmsbe.repository.ConsentRepository;
import com.websafe.ccmsbe.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ConsentService {

    private final WebsiteRepository websiteRepository;
    private final ConsentRepository consentRepository;

    @Autowired
    public ConsentService(WebsiteRepository websiteRepository, ConsentRepository consentRepository) {
        this.websiteRepository = websiteRepository;
        this.consentRepository = consentRepository;
    }

    public Consent addConsent(Long websiteId, List<CookieCategory> allowedCategories) {
        Website website = websiteRepository.findById(websiteId).orElseThrow(
                () -> new WebsiteNotFoundException("Website not found with id " + websiteId)
        );
        Consent consent = new Consent();
//        consent.setCreatedDate(new Date());
        consent.setCreatedAt(new Time(System.currentTimeMillis()));
        consent.setAllowedCookieCategories(allowedCategories);
        if (allowedCategories == null) {
            consent.setIsGiven("false");
        }else {
            consent.setIsGiven("true");
        }
        List<Consent> consents = website.getConsent();
        consents.add(consent);
        website.setConsent(consents);
        websiteRepository.save(website);
        return consentRepository.save(consent);
    }

}
