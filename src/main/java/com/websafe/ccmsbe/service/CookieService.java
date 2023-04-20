package com.websafe.ccmsbe.service;

import com.websafe.ccmsbe.entity.Cookie;
import com.websafe.ccmsbe.entity.CookieCategory;
import com.websafe.ccmsbe.entity.Website;
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
        Website website = websiteRepository.findById(websiteId).orElse(null);
        if (website != null){
            return website.getCookies();
        }
        return null;
    }

    public List<Cookie> addCookieCategoryToCookies(Long websiteId, List<Cookie> cookies) {
        Website website = websiteRepository.findById(websiteId).orElse(null);
        if (website != null){
            cookies.forEach(cookie -> {cookie.setWebsite(website);});
            website.setCookies(cookies);
            websiteRepository.save(website);
            cookieRepository.saveAll(website.getCookies());

            return website.getCookies();
        }
        return null;
    }

    public Cookie addCookieManually(Long websiteId, Cookie cookie) {
        Website website = websiteRepository.findById(websiteId).orElse(null);
        if (website != null) {
            if (cookie != null) {
                cookie.setWebsite(website);
                return cookieRepository.save(cookie);
            }
            return null;
        }
        return null;
    }

    public Cookie addCookieCategoryToCookie(
            Long websiteId,
            Long cookieId,
            CookieCategory cookieCategory
    ) {
        Website website = websiteRepository.findById(websiteId).orElse(null);
        Cookie cookie = cookieRepository.findById(cookieId).orElse(null);
        if (website != null & cookie != null) {
            CookieCategory category = cookieCategoryRepository.findById(cookieCategory.getCategoryId()).orElse(null);
            if (category != null){
                cookie.setCookieCategory(category);
                return cookieRepository.save(cookie);
            }
            return null;
        }
        return null;
    }

    public Boolean deleteCookie(Long websiteId, Long cookieId) {
        Website website = websiteRepository.findById(websiteId).orElse(null);
        if(website != null){
            Cookie cookie = cookieRepository.findById(cookieId).orElse(null);
            if (cookie != null) {
                cookieRepository.delete(cookie);
                return true;
            }
            return false;
        }
        return false;
    }

    public Cookie updateCategoryInCookie(Long cookieId, Long cookieCategoryId) {
        Cookie cookie = cookieRepository.findById(cookieId).orElse(null);
        CookieCategory cookieCategory = cookieCategoryRepository.findById(cookieCategoryId).orElse(null);

        if (cookie != null && cookieCategory != null) {
            cookie.setCookieCategory(cookieCategory);
            return cookieRepository.save(cookie);
        }
        return null;
    }


}
