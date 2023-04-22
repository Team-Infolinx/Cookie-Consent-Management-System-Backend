package com.websafe.ccmsbe.service;

import com.websafe.ccmsbe.entity.CookieCategory;
import com.websafe.ccmsbe.entity.Website;
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
        Website website = websiteRepository.findById(websiteId).orElse(null);
        if (website != null) {
            return website.getCookieCategories();
        }
        return null;
    }
    public CookieCategory addNewCategory(Long websiteId , CookieCategory cookieCategory) {
        Website website = websiteRepository.findById(websiteId).orElse(null);
        if (website != null) {
            if (cookieCategory.getCategoryName() != null) {
                List<String> cookieCategoryNames = cookieCategoryRepository.getCookieCategoryNamesByWebsite(website);
                if(!cookieCategoryNames.contains(cookieCategory.getCategoryName())){
                    website.addCookieCategoryToWebsite(cookieCategory);
                    cookieCategoryRepository.save(cookieCategory);
                    return cookieCategory;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    public Boolean deleteCookieCategory(Long websiteId, Long categoryId) {
        Website website = websiteRepository.findById(websiteId).orElse(null);
        CookieCategory cookieCategory = cookieCategoryRepository.findById(categoryId).orElse(null);
        if (website != null && cookieCategory != null) {
            CookieCategory deleteCategory = cookieCategoryRepository.findByCategoryIdAndWebsite(categoryId,website);
            cookieCategoryRepository.delete(deleteCategory);
            return true;
        }
        return false;
    }

    //Updating cookie category name.
    public CookieCategory updateCookieCategory(Long categoryId,String newCategoryName) {
        CookieCategory cookieCategory = cookieCategoryRepository.findById(categoryId).orElse(null);
        if (cookieCategory != null) {
            cookieCategory.setCategoryName(newCategoryName);
            return cookieCategoryRepository.save(cookieCategory);
        }
        return null;
    }

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
