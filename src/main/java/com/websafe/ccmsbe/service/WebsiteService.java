package com.websafe.ccmsbe.service;

import com.websafe.ccmsbe.entity.Website;
import com.websafe.ccmsbe.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
@Service
public class WebsiteService {
    private final WebsiteRepository websiteRepository;
    @Autowired
    public WebsiteService(WebsiteRepository websiteRepository) {
        this.websiteRepository = websiteRepository;
    }
    public class WebsiteNotFoundException extends RuntimeException {
        public WebsiteNotFoundException(String message) {
            super(message);
        }
    }
    public Website addWebsite(Long userId, Website website) {
        website.setUserId(userId);
        return websiteRepository.save(website);
    }
    public List<Website> getWebsiteByUserId(Long userId) {
        return websiteRepository.getWebsitesByUserId(userId);
    }
    public Boolean deleteWebsite(Long userId, Long websiteId) {
        Website website = websiteRepository.findById(websiteId).orElse(null);
        if (website != null) {
            if(Objects.equals(website.getUserId(), userId)) {
                websiteRepository.deleteById(websiteId);
                return true;
            }
            return false;
        }
        return false;
    }
    public Website updateWebsite(Long userId, Website updatedWebsite) {
        Website website = websiteRepository.findById((updatedWebsite.getWebsiteId())).orElse(null);

        if (website != null) {
            if (Objects.equals(website.getUserId(), userId)) { //changed was done here.
                website.setConfigName(updatedWebsite.getConfigName());
                website.setDomain(updatedWebsite.getDomain());
                return websiteRepository.save(website);
            }
            return null;
        }
        return null;
    }
    public Website getWebsiteByUserIdAndWebsiteId(Long userId, Long websiteId) {
        Website website = websiteRepository.findById(websiteId).orElse(null);
        if (website != null) {
            if (Objects.equals(website.getUserId(), userId)) {
                return website;
            }
            return null;
        }
        return null;
    }
    public List<Website> getPrivacyRegulationsFromWebsite(Long websiteId) {
        try {
            List<Website> websites = websiteRepository.findByWebsiteId(websiteId);
            if (websites.isEmpty()) {
                throw new WebsiteNotFoundException("Website not found with ID: " + websiteId);
            }
            return websites;
        } catch (WebsiteNotFoundException e) {
            // Handle the custom exception appropriately
            throw e; // Re-throw the exception to be handled at a higher level
        } catch (Exception e) {
            // Handle other exceptions if needed
            throw new RuntimeException("An error occurred while retrieving privacy regulations from the website");
        }
    }
}
