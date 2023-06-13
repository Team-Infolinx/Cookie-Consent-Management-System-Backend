package com.websafe.ccmsbe.controller;

import com.websafe.ccmsbe.entity.Consent;
import com.websafe.ccmsbe.entity.CookieCategory;
import com.websafe.ccmsbe.service.ConsentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/websites/{websiteId}/consents")
@CrossOrigin
public class ConsentController {

    private final ConsentService consentService;
    @Autowired
    public ConsentController(ConsentService consentService) {
        this.consentService = consentService;
    }

    @PostMapping
    public Consent addConsent(
            @PathVariable(name = "websiteId") Long websiteId,
            @RequestBody List<CookieCategory> allowedCategories
    ) {
        return consentService.addConsent(websiteId, allowedCategories);
    }

}
