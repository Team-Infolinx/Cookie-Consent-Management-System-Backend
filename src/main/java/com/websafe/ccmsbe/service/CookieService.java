package com.websafe.ccmsbe.service;

import com.websafe.ccmsbe.entity.Cookie;
import com.websafe.ccmsbe.entity.CookieCategory;
import com.websafe.ccmsbe.entity.Website;
import com.websafe.ccmsbe.exception.CookieCategoryNotFoundException;
import com.websafe.ccmsbe.exception.CookieNotFoundException;
import com.websafe.ccmsbe.exception.WebsiteNotFoundException;
import com.websafe.ccmsbe.repository.CookieCategoryRepository;
import com.websafe.ccmsbe.repository.CookieRepository;
import com.websafe.ccmsbe.repository.WebsiteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CookieService {
    private final WebsiteRepository websiteRepository;
    private final CookieRepository cookieRepository;
    private final CookieCategoryRepository cookieCategoryRepository;

    public CookieService(WebsiteRepository websiteRepository, CookieRepository cookieRepository, CookieCategoryRepository cookieCategoryRepository) {
        this.websiteRepository = websiteRepository;
        this.cookieRepository = cookieRepository;
        this.cookieCategoryRepository = cookieCategoryRepository;
    }

    public List<Cookie> getCookiesToWebsite(Long websiteId) {
        Website website = websiteRepository.findById(websiteId).orElseThrow(
                () -> new WebsiteNotFoundException("Website not found with the id " + websiteId)
        );
        return website.getCookies();
    }

    public Cookie addCookieManually(Long websiteId, Cookie cookie) {
        Website website = websiteRepository.findById(websiteId).orElseThrow(
                () -> new WebsiteNotFoundException("Website not found with id " + websiteId)
        );
        if (cookie == null) {
            throw new CookieNotFoundException("Cookie is invalid. Could not save.");
        }
        cookie.setWebsite(website);
        return cookieRepository.save(cookie);
    }

    public Boolean deleteCookie(Long websiteId, Long cookieId) {
        Website website = websiteRepository.findById(websiteId).orElseThrow(
                () -> new WebsiteNotFoundException("Website not found with id " + websiteId)
        );
        Cookie cookie = cookieRepository.findById(cookieId).orElseThrow(
                () -> new CookieNotFoundException("Cookie not found with id " + cookieId)
        );
        cookieRepository.delete(cookie);
        return true;
    }

    @Transactional
    public Cookie updateCategoryInCookie(Long websiteId, Long cookieId, Long categoryId){
        Website website = websiteRepository.findById(websiteId).orElseThrow(
                () -> new WebsiteNotFoundException("Website not found with id " + websiteId)
        );
        Cookie cookie  = cookieRepository.findById(cookieId).orElseThrow(
                () -> new CookieNotFoundException("Cookie not found with id " + cookieId)
        );
        CookieCategory cookieCategory = cookieCategoryRepository.findById(categoryId).orElseThrow(
                () -> new CookieCategoryNotFoundException("Cookie category not found with id " + categoryId)
        );
        cookie.setWebsite(website);
        cookie.setCookieCategory(cookieCategory);
        return cookieRepository.save(cookie);
    }

    public Cookie updateCookie(Long websiteId, Long cookieId, Cookie cookie) {
        Website website = websiteRepository.findById(websiteId).orElseThrow(
                () -> new WebsiteNotFoundException("Website not found with id " + websiteId)
        );
        Cookie existingCookie = cookieRepository.findById(cookieId).orElseThrow(
                () -> new CookieNotFoundException("Cookie not found with id " + cookieId)
        );
        existingCookie.setCookieName(cookie.getCookieName());
        existingCookie.setDomain(cookie.getDomain());
        existingCookie.setPath(cookie.getPath());
        existingCookie.setDurationUnit(cookie.getDurationUnit());
        existingCookie.setExpireDuration(cookie.getExpireDuration());
        return cookieRepository.save(existingCookie);
    }
}
