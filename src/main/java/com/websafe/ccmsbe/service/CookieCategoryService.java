package com.websafe.ccmsbe.service;

import com.websafe.ccmsbe.entity.CookieCategory;
import com.websafe.ccmsbe.entity.Website;
import com.websafe.ccmsbe.exception.CookieCategoryNotFoundException;
import com.websafe.ccmsbe.exception.WebsiteNotFoundException;
import com.websafe.ccmsbe.repository.CookieCategoryRepository;
import com.websafe.ccmsbe.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CookieCategoryService {
    private final CookieCategoryRepository cookieCategoryRepository;
    private final WebsiteRepository websiteRepository;

    @Autowired
    public CookieCategoryService(CookieCategoryRepository cookieCategoryRepository, WebsiteRepository websiteRepository) {
        this.cookieCategoryRepository = cookieCategoryRepository;
        this.websiteRepository = websiteRepository;
    }
    public List<CookieCategory> getCookieCategories(Long websiteId) {
        Website website = websiteRepository.findById(websiteId).orElseThrow(
                () -> new WebsiteNotFoundException("Website not found with id " + websiteId)
        );
        return website.getCookieCategories();
    }

    public CookieCategory addNewCategory(Long websiteId, CookieCategory cookieCategory) {
        Website website = websiteRepository.findById(websiteId).orElseThrow(
                () -> new WebsiteNotFoundException("Website not found with id " + websiteId)
        );
        website.addCookieCategoryToWebsite(cookieCategory);
        return cookieCategoryRepository.save(cookieCategory);
    }

    public Boolean deleteCookieCategory(Long websiteId, Long categoryId) {
        Website website = websiteRepository.findById(websiteId).orElseThrow(
                () -> new WebsiteNotFoundException("Website not found with id " + websiteId)
        );
        CookieCategory cookieCategory = cookieCategoryRepository.findById(categoryId).orElseThrow(
                () -> new CookieCategoryNotFoundException("Cookie category not found with id " + categoryId)
        );
        CookieCategory deleteCategory = cookieCategoryRepository.findByCategoryIdAndWebsite(categoryId,website);
        cookieCategoryRepository.delete(deleteCategory);
        return true;
    }

    public CookieCategory updateCookieCategory(Long websiteId, Long categoryId, CookieCategory cookieCategory) {
        Website website = websiteRepository.findById(websiteId).orElseThrow(
                () -> new WebsiteNotFoundException("Website not found with id " + websiteId)
        );
        CookieCategory existingCookieCategory = cookieCategoryRepository.findById(categoryId).orElseThrow(
                () -> new CookieCategoryNotFoundException("Cookie category not found with id" + categoryId)
        );
        existingCookieCategory.setCategoryName(cookieCategory.getCategoryName());
        existingCookieCategory.setCategoryDescription(cookieCategory.getCategoryDescription());
        return cookieCategoryRepository.save(existingCookieCategory);
    }

    // Not using.
    public List<CookieCategory> addCookieCategoryList(Long websiteId, List<CookieCategory> cookieCategoryList) {
        Website website = websiteRepository.findById(websiteId).orElse(null);
        if (website != null) {
            cookieCategoryList.forEach(cookieCategory -> {cookieCategory.setWebsite(website);});
            cookieCategoryRepository.saveAll(cookieCategoryList);
            return cookieCategoryRepository.getCookieCategoriesByWebsiteEquals(website);
        }
        return null;
    }
}
