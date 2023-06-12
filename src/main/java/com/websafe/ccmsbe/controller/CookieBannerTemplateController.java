package com.websafe.ccmsbe.controller;
import com.websafe.ccmsbe.entity.CookieBannerTemplate;
import com.websafe.ccmsbe.service.CookieBannerTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/templates")
@CrossOrigin(value = "http://localhost:3000")
public class CookieBannerTemplateController {

    @Autowired
    CookieBannerTemplateService cookieBannerTemplateService;

    @GetMapping("/{websiteID}")
    public List<CookieBannerTemplate> getAllTemplates(@PathVariable(name = "websiteID")Long websiteID){
                return cookieBannerTemplateService.getAllTemplates(websiteID);
    }

    @PutMapping("/{id}")
    public CookieBannerTemplate updateByID(@RequestBody CookieBannerTemplate cookieBannerTemplate,@PathVariable Long id){
        return cookieBannerTemplateService.updateByID(id,cookieBannerTemplate);

    }

     @PutMapping("default/{id}")
    public CookieBannerTemplate updateTemplateDefault(@PathVariable Long id, @RequestBody CookieBannerTemplate cookieBannerTemplate) {
        return cookieBannerTemplateService.updateTemplateDefault(id, cookieBannerTemplate);
    }

}
