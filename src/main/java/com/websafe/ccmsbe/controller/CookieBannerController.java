package com.websafe.ccmsbe.controller;

import com.websafe.ccmsbe.entity.CookieBanner;
import com.websafe.ccmsbe.service.CookieBannerService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/banner")
@CrossOrigin(value = "http://localhost:3000")
public class CookieBannerController {

    @Autowired
    private CookieBannerService cookieBannerService;



    @GetMapping("/getBanners")
    public List<CookieBanner> getAllBanners(){
        return cookieBannerService.getAllBanners();
    }

    @PostMapping("/addBanner")
    public CookieBanner addNewBanner(@RequestBody CookieBanner cookieBanner){
        return cookieBannerService.addNewBanner(cookieBanner);

    }

    //make return type void
    @DeleteMapping("/deleteBanner/{id}")
    public String deleteBanner(@PathVariable Long id){
        return cookieBannerService.deleteBanner(id);
    }

    @PutMapping("/updateBanner")
    public CookieBanner updateBanner(@RequestBody CookieBanner cookieBanner){
        return cookieBannerService.updateBanner(cookieBanner);
    }
}
