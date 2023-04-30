package com.websafe.ccmsbe.service;

import com.websafe.ccmsbe.entity.Cookie;
import com.websafe.ccmsbe.entity.CookieCategory;
import com.websafe.ccmsbe.entity.Website;
import com.websafe.ccmsbe.repository.CookieCategoryRepository;
import com.websafe.ccmsbe.repository.CookieRepository;
import com.websafe.ccmsbe.repository.WebsiteRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
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
    @Transactional
    public Cookie updateCategoryInCookie(Long cookieId, Long cookieCategoryId) {
        CookieCategory cookieCategory = cookieCategoryRepository.findById(cookieCategoryId).orElse(null);
        Cookie cookie = cookieRepository.findById(cookieId).orElse(null);

        if (cookie != null && cookieCategory != null) {
            cookie.setCookieCategory(cookieCategory);
            return cookieRepository.save(cookie);
        }
        return null;
    }

//    public String updateCookie(Cookie cookie) {
//        if (cookie != null && cookie.getCookieId() != null && cookie.getWebsite() != null) {
//            Cookie existingCookie = cookieRepository.findById(cookie.getCookieId()).orElse(null);
//            if (existingCookie != null) {
//                existingCookie.setCookieName(cookie.getCookieName());
//                existingCookie.setDomain(cookie.getDomain());
//                existingCookie.setPath(cookie.getPath());
//                existingCookie.setExpireDate(cookie.getExpireDate());
////                return cookieRepository.save(existingCookie);
//                return "inside the cookie";
//            }
//        }
//        return "not through the if : " + cookie;
//    }

    public Cookie updateCookie(Cookie cookie) {
        if (cookie.getCookieId() != null && cookie.getWebsite() != null) {
            Cookie existingCookie = cookieRepository.findById(cookie.getCookieId()).orElse(null);
            if (existingCookie != null) {
                existingCookie.setCookieName(cookie.getCookieName());
                existingCookie.setDomain(cookie.getDomain());
                existingCookie.setPath(cookie.getPath());
                existingCookie.setExpireDate(cookie.getExpireDate());
                return cookieRepository.save(existingCookie);
            }
        }
        return null;
    }
}
