package com.websafe.ccmsbe.service;
import com.websafe.ccmsbe.entity.CookieBanner;
import com.websafe.ccmsbe.entity.Website;
import com.websafe.ccmsbe.exception.BannerNotFoundException;
import com.websafe.ccmsbe.exception.WebsiteNotFoundException;
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
        throw new WebsiteNotFoundException("Website doesn't exist for id "+websiteId);
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
                banner.setBannerTextColor(cookieBanner.getBannerTextColor());
                return cookieBannerRepository.save(banner);
            }
            else{
                throw new BannerNotFoundException("Banner doesn't exist for websiteId "+websiteId);
            }
        }
        throw new WebsiteNotFoundException("Website doesn't exist for id "+websiteId);
    }

}
