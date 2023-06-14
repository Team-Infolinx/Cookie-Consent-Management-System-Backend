package com.websafe.ccmsbe.controller;
import com.websafe.ccmsbe.entity.CookieBanner;
import com.websafe.ccmsbe.service.CookieBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="api/v1/banners")
@CrossOrigin(value = "http://localhost:3000")
public class CookieBannerController {

    @Autowired
    private CookieBannerService cookieBannerService;

    @CrossOrigin("*")
    @GetMapping("/{websiteId}")
    public CookieBanner getCookieBanner(@PathVariable(name="websiteId") Long websiteId){
        return cookieBannerService.getCookieBanner(websiteId);
    }

    @PutMapping("/{websiteId}")
    public CookieBanner updateById(@PathVariable Long websiteId,@RequestBody CookieBanner cookieBanner){
        return cookieBannerService.updateById(websiteId,cookieBanner);
    }

}
