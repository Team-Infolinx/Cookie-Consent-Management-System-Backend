package com.websafe.ccmsbe.service;
import com.websafe.ccmsbe.entity.CookieBanner;
import com.websafe.ccmsbe.entity.Website;
import com.websafe.ccmsbe.repository.CookieBannerRepository;
import com.websafe.ccmsbe.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CookieBannerService {

    @Autowired
    private CookieBannerRepository cookieBannerRepository;

    private final WebsiteRepository websiteRepository;

    public CookieBannerService(WebsiteRepository websiteRepository) {

        this.websiteRepository = websiteRepository;
    }

    public CookieBanner getCookieBanner(Long websiteId) {
        Website website = websiteRepository.findById(websiteId).orElse(null);
        if (website != null) {
           return website.getCookieBanner();
        }
        return null;
    }


    public CookieBanner addNewBanner(Long websiteId, CookieBanner cookieBanner) {
        Website website = websiteRepository.findById(websiteId).orElse(null);
        if (website != null) {
            cookieBanner.setWebsite(website);
            return cookieBannerRepository.save(cookieBanner);
        }
        return null;

    }

    //make return type void
    public String deleteBanner(Long id) {
        cookieBannerRepository.deleteById(id);
        return "Deletion success";
    }


    public CookieBanner updateBanner(CookieBanner cookieBanner) {
        return cookieBannerRepository.save(cookieBanner);
    }

    public CookieBanner updateById(Long websiteId, CookieBanner cookieBanner) {
        Website website = websiteRepository.findById(websiteId).orElse(null);
        if (website != null) {
            Optional<CookieBanner> optionalCookieBanner = Optional.ofNullable(website.getCookieBanner());
            if (optionalCookieBanner.isPresent()) {
                CookieBanner banner = optionalCookieBanner.get();
                banner.setBannerPosition(cookieBanner.getBannerPosition());
                banner.setBannerAlignment(cookieBanner.getBannerAlignment());
                banner.setBannerColor(cookieBanner.getBannerColor());
                return cookieBannerRepository.save(banner);
            }
        }
        return null;
    }


}
