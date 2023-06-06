package com.websafe.ccmsbe.service;

import com.websafe.ccmsbe.entity.CookieCategory;
import com.websafe.ccmsbe.entity.Website;
import com.websafe.ccmsbe.exception.AccessDeniedException;
import com.websafe.ccmsbe.exception.WebsiteNotFoundException;
import com.websafe.ccmsbe.repository.CookieCategoryRepository;
import com.websafe.ccmsbe.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class WebsiteService {

    private final WebsiteRepository websiteRepository;
    private final CookieCategoryRepository cookieCategoryRepository;

    @Autowired
    public WebsiteService(WebsiteRepository websiteRepository, CookieCategoryRepository cookieCategoryRepository) {
        this.websiteRepository = websiteRepository;
        this.cookieCategoryRepository = cookieCategoryRepository;
    }

    public Website addWebsite(Long userId, Website website) {
        website.setUserId(userId);
        Website websiteSaved =  websiteRepository.save(website);
        List<CookieCategory> defaultCategories = createDefaultCategories(websiteSaved);
        cookieCategoryRepository.saveAll(defaultCategories);
        websiteSaved.setCookieCategories(defaultCategories);
        return websiteSaved;
    }

    private List<CookieCategory> createDefaultCategories(Website website) {
        List<CookieCategory> defaultCategories = new ArrayList<>();

        CookieCategory necessary = new CookieCategory();
        necessary.setCategoryName("Necessary");
        necessary.setCategoryDescription("These cookies are essential for the website to function properly. They enable basic functionalities such as page navigation, access to secure areas, and ensuring the website's core features work correctly.");
        necessary.setWebsite(website);
        defaultCategories.add(necessary);

        CookieCategory functional = new CookieCategory();
        functional.setCategoryName("Functional");
        functional.setCategoryDescription("Functional cookies enhance the usability of the website by providing additional features and personalization. They enable things like remembering user preferences, language selection, and customization of the website's appearance.");
        functional.setWebsite(website);
        defaultCategories.add(functional);

        CookieCategory target = new CookieCategory();
        target.setCategoryName("Target");
        target.setCategoryDescription("Targeting cookies are used to track users across different websites to create a profile of their interests. They are often used for personalized advertising and delivering targeted content or promotions based on the user's browsing behavior.");
        target.setWebsite(website);
        defaultCategories.add(target);

        CookieCategory other = new CookieCategory();
        other.setCategoryName("Other");
        other.setCategoryDescription("The \"Other\" category can be used for any additional types of cookies that are specific to your website and don't fall into the above categories. You can provide a brief description explaining the purpose or functionality of these cookies.");
        other.setWebsite(website);
        defaultCategories.add(other);

        return defaultCategories;
    }

    public List<Website> getWebsitesByUserId(Long userId) {
        return websiteRepository.getWebsitesByUserId(userId);
    }

    public Boolean deleteWebsite(Long userId, Long websiteId) {
        Website website = websiteRepository.findById(websiteId).orElseThrow(
                () -> new WebsiteNotFoundException("Website not found with id " + websiteId)
        );
        if(!Objects.equals(website.getUserId(), userId)) {
            throw new AccessDeniedException("User not found with id : " + userId);
        }
        websiteRepository.deleteById(websiteId);
        return true;
    }

    public Website updateWebsite(Long userId, Long websiteId, Website updatedWebsite) {
        Website website = websiteRepository.findById(websiteId).orElseThrow(
                () -> new WebsiteNotFoundException("Website not found with id " + websiteId)
        );
        if (!Objects.equals(website.getUserId(), userId)) {
            throw new AccessDeniedException("User not found with id : " + userId);
        }
        website.setConfigName(updatedWebsite.getConfigName());
        website.setDomain(updatedWebsite.getDomain());
        return websiteRepository.save(website);
    }

    public Website getWebsiteByUserIdAndWebsiteId(Long userId, Long websiteId) {
        Website website = websiteRepository.findById(websiteId).orElseThrow(
                () -> new WebsiteNotFoundException("Website not found with id " + websiteId)
        );
        if (!Objects.equals(website.getUserId(), userId)) {
            throw new AccessDeniedException("User not found with id : " + userId);
        }
        return website;
    }

}
