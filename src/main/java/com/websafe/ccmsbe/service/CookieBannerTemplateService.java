package com.websafe.ccmsbe.service;
import com.websafe.ccmsbe.entity.CookieBanner;
import com.websafe.ccmsbe.entity.CookieBannerTemplate;
import com.websafe.ccmsbe.entity.PrivacyRegulation;
import com.websafe.ccmsbe.entity.Website;
import com.websafe.ccmsbe.repository.CookieBannerRepository;
import com.websafe.ccmsbe.repository.CookieBannerTemplateRepository;
import com.websafe.ccmsbe.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CookieBannerTemplateService {

    @Autowired
    CookieBannerTemplateRepository cookieBannerTemplateRepository;

    @Autowired
    CookieBannerRepository cookieBannerRepository;

    @Autowired
    WebsiteRepository websiteRepository;


    public List<CookieBannerTemplate> getAllTemplates(Long websiteID) {
        Website website = websiteRepository.findById(websiteID).orElse(null);

        /*Checking whether the websiteID is avialable.*/
        if (website != null){
            CookieBanner cookieBanner = website.getCookieBanner();
            /*Checking whether the websiteID is available with a cookie banner.*/
            if( cookieBanner == null) {
                cookieBanner = new CookieBanner();
                cookieBanner.setWebsite(website);
                cookieBanner.setBannerAlignment("Right");
                cookieBanner.setBannerColor("#263347");
                cookieBanner.setBannerPosition("Center");
                cookieBanner.setBannerTextColor("#ffffff");
                cookieBannerRepository.save(cookieBanner);


                List<PrivacyRegulation> regulationList = new ArrayList<>();
                regulationList.addAll(website.getPrivacyRegulations());

                List<CookieBannerTemplate> templateList = new ArrayList<>();

                /*Adding new default template regarding to the added privacy regulations*/
                for (PrivacyRegulation regulation : regulationList) {
                    CookieBannerTemplate t1 = new CookieBannerTemplate();
                    t1.setCookieBanner(cookieBanner);
                    t1.setPrivacyRegulation(regulation);
                    t1.setTemplatePrivacyPolicyLink("add your policy link");
                    t1.setTemplateContent("Add your banner content");
                    t1.setTemplateRegulation(regulation.getRegulationName());
                    t1.setTemplateName("Websafe-Default");
                    cookieBannerTemplateRepository.save(t1);
                    templateList.add(t1);
                }

                return templateList;

            }
            else {
                return website.getCookieBanner().getCookieBannerTemplates();
            }
        }
        return null;
    }

   /* public List<CookieBannerTemplate> getTemplateByID(Long bannerId) {
        CookieBanner cookieBanner = cookieBannerRepository.findById(bannerId).orElse(null);
        if (cookieBanner != null) {
            return cookieBanner.getCookieBannerTemplates();
        }
        return null;
    }*/


    /*public CookieBannerTemplate saveNewTemplate(Long bannerId, CookieBannerTemplate cookieBannerTemplate) {
        CookieBanner cookieBanner = cookieBannerRepository.findById(bannerId).orElse(null);
        if (cookieBanner != null) {
            cookieBannerTemplate.setCookieBanner(cookieBanner);
            return cookieBannerTemplateRepository.save(cookieBannerTemplate);
        }
        return null;
    }*/

   /* public String deleteTemplate(Long id) {
        cookieBannerTemplateRepository.deleteById(id);
        return "Deletion success";
    }

    public CookieBannerTemplate updateTemplate(CookieBannerTemplate cookieBannerTemplate) {
        return cookieBannerTemplateRepository.save(cookieBannerTemplate);
    }
*/

    public CookieBannerTemplate updateByID(Long id, CookieBannerTemplate cookieBannerTemplate) {
        // Find the template with the given ID
        Optional<CookieBannerTemplate> optionalTemplate = cookieBannerTemplateRepository.findById(id);

        // If the template is found, update its properties and save it
        if (optionalTemplate.isPresent()) {
            CookieBannerTemplate template = optionalTemplate.get();
            template.setTemplateName(cookieBannerTemplate.getTemplateName());
            template.setTemplateRegulation(cookieBannerTemplate.getTemplateRegulation());
            template.setTemplatePrivacyPolicyLink(cookieBannerTemplate.getTemplatePrivacyPolicyLink());
            template.setTemplateContent(cookieBannerTemplate.getTemplateContent());
            return cookieBannerTemplateRepository.save(template);
        }

        // If the template is not found, return null
        return null;
    }






}
