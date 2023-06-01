package com.websafe.ccmsbe.controller;

import com.websafe.ccmsbe.entity.CookieBanner;
import com.websafe.ccmsbe.entity.CookieBannerTemplate;
import com.websafe.ccmsbe.service.CookieBannerTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/template")
@CrossOrigin(value = "http://localhost:3000")
public class CookieBannerTemplateController {

    @Autowired
    CookieBannerTemplateService cookieBannerTemplateService;

    @GetMapping("getAllTemplates/{websiteID}")
    public List<CookieBannerTemplate> getAllTemplates(@PathVariable(name = "websiteID")Long websiteID){
                return cookieBannerTemplateService.getAllTemplates(websiteID);
    }


    @GetMapping("getTemplate/{bannerId}")
    public List<CookieBannerTemplate> getTemplateByID(@PathVariable(name = "bannerId") Long bannerId){

        return cookieBannerTemplateService.getTemplateByID(bannerId);
    }


    @PostMapping("saveTemplate/{bannerId}")
    public CookieBannerTemplate saveNewTemplate(
            @PathVariable(name = "bannerId") Long bannerId,
            @RequestBody CookieBannerTemplate cookieBannerTemplate)
    {
        return cookieBannerTemplateService.saveNewTemplate(bannerId, cookieBannerTemplate);
    }

    @DeleteMapping("deleteTemplate/{id}")
    public String deleteTemplate(@PathVariable Long id){
        return cookieBannerTemplateService.deleteTemplate(id);
    }

    @PutMapping("updateTemplate")
    public CookieBannerTemplate updateTemplate(@RequestBody CookieBannerTemplate cookieBannerTemplate){
        return cookieBannerTemplateService.updateTemplate(cookieBannerTemplate);
    }


    @PutMapping("updateTemplateID/{id}")
    public CookieBannerTemplate updateByID(@RequestBody CookieBannerTemplate cookieBannerTemplate,@PathVariable Long id){
        return cookieBannerTemplateService.updateByID(id,cookieBannerTemplate);

    }

    @PutMapping("templateDefault/{id}")
    public CookieBannerTemplate updateTemplateDefault(@PathVariable Long id, @RequestBody CookieBannerTemplate cookieBannerTemplate) {
        return cookieBannerTemplateService.updateTemplateDefault(id, cookieBannerTemplate);
    }





}
