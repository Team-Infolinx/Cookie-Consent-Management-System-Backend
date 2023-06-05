package com.websafe.ccmsbe.controller;

import com.websafe.ccmsbe.entity.CookieBanner;
import com.websafe.ccmsbe.service.CookieBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="api/banner")
@CrossOrigin(value = "http://localhost:3000")
public class CookieBannerController {

    @Autowired
    private CookieBannerService cookieBannerService;


    @GetMapping("/getBanners/{websiteId}")
    public CookieBanner getCookieBanner(@PathVariable(name="websiteId") Long websiteId){
        return cookieBannerService.getCookieBanner(websiteId);
    }

    @PostMapping("/addBanner/{websiteId}")
    public CookieBanner addNewBanner(@PathVariable(name = "websiteId") Long websiteId, @RequestBody CookieBanner cookieBanner){
        return cookieBannerService.addNewBanner(websiteId,cookieBanner);
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

    @PutMapping("/updateBanner/{websiteId}")
    public CookieBanner updateById(@PathVariable Long websiteId,@RequestBody CookieBanner cookieBanner){
        return cookieBannerService.updateById(websiteId,cookieBanner);
    }

}
