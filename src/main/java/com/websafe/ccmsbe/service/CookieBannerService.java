package com.websafe.ccmsbe.service;


import com.websafe.ccmsbe.entity.CookieBanner;
import com.websafe.ccmsbe.repository.CookieBannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CookieBannerService {

    @Autowired
    private CookieBannerRepository cookieBannerRepository;


    public List<CookieBanner> getAllBanners() {

        return cookieBannerRepository.findAll();
    }


    public CookieBanner addNewBanner(CookieBanner cookieBanner) {
        return cookieBannerRepository.save(cookieBanner);

    }

    //make return type void
    public String deleteBanner(Long id) {
        cookieBannerRepository.deleteById(id);
        return "Deletion success";
    }


    public CookieBanner updateBanner(CookieBanner cookieBanner) {
        return cookieBannerRepository.save(cookieBanner);
    }
}
