package com.websafe.ccmsbe.controller;

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

    @GetMapping("getTemplates")
    public List<CookieBannerTemplate> getAllTemplates(){

        return cookieBannerTemplateService.getAllTemplates();
    }

    @PostMapping("saveTemplate")
    public CookieBannerTemplate saveNewTemplate(@RequestBody CookieBannerTemplate cookieBannerTemplate){
        return cookieBannerTemplateService.saveNewTemplate(cookieBannerTemplate);
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





}
