package com.websafe.ccmsbe.service;

import com.websafe.ccmsbe.entity.CookieBannerTemplate;
import com.websafe.ccmsbe.repository.CookieBannerRepository;
import com.websafe.ccmsbe.repository.CookieBannerTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CookieBannerTemplateService {

    @Autowired
    CookieBannerTemplateRepository cookieBannerTemplateRepository;


    public List<CookieBannerTemplate> getAllTemplates() {
        return cookieBannerTemplateRepository.findAll();
    }

    public CookieBannerTemplate saveNewTemplate(CookieBannerTemplate cookieBannerTemplate) {
        return cookieBannerTemplateRepository.save(cookieBannerTemplate);
    }

    public String deleteTemplate(Long id) {
        cookieBannerTemplateRepository.deleteById(id);
        return "Deletion success";
    }

    public CookieBannerTemplate updateTemplate(CookieBannerTemplate cookieBannerTemplate) {
        return cookieBannerTemplateRepository.save(cookieBannerTemplate);
    }





    public CookieBannerTemplate updateByID(Long id, CookieBannerTemplate cookieBannerTemplate) {
        // Find the template with the given ID
        Optional<CookieBannerTemplate> optionalTemplate = cookieBannerTemplateRepository.findById(id);

        // If the template is found, update its properties and save it
        if (optionalTemplate.isPresent()) {
            CookieBannerTemplate template = optionalTemplate.get();
            template.setTemplateName(cookieBannerTemplate.getTemplateName());
            template.setTemplateRegulation(cookieBannerTemplate.getTemplateRegulation());
            template.setTemplateContent(cookieBannerTemplate.getTemplateContent());
            return cookieBannerTemplateRepository.save(template);
        }

        // If the template is not found, return null
        return null;
    }
}
