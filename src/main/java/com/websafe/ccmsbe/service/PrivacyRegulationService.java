package com.websafe.ccmsbe.service;

import com.websafe.ccmsbe.entity.PrivacyRegulation;
import com.websafe.ccmsbe.entity.Website;
import com.websafe.ccmsbe.repository.PrivacyRegulationRepository;
import com.websafe.ccmsbe.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PrivacyRegulationService {
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
    private final PrivacyRegulationRepository privacyRegulationRepository;

    private final WebsiteRepository websiteRepository;

    @Autowired
    public PrivacyRegulationService(PrivacyRegulationRepository privacyRegulationRepository, WebsiteRepository websiteRepository) {
        this.privacyRegulationRepository = privacyRegulationRepository;
        this.websiteRepository = websiteRepository;
    }


    public PrivacyRegulation createPrivacyRegulation(PrivacyRegulation privacyRegulation) {

        return privacyRegulationRepository.save(privacyRegulation);
    }

    public List<PrivacyRegulation> getAllPrivacyRegulations() {
        return privacyRegulationRepository.findAll();
    }

    public Optional<PrivacyRegulation> getPrivacyRegulationsFromWebsite(Long websiteId) {
        return privacyRegulationRepository.findById(websiteId);
    }

    @Transactional
    public String addPrivacyRegulationToWebsite(Long websiteId, Long regulationId) {
        Website website = websiteRepository.findById(websiteId).orElse(null);
        PrivacyRegulation privacyRegulation = privacyRegulationRepository.findById(regulationId).orElse(null);

        //check for duplications

        if (website == null || privacyRegulation == null) {
            return "Unsuccessfully";
        }

        if (website.getPrivacyRegulations().contains(privacyRegulation)) {
            return "Regulation already added to the website";
        }

        website.getPrivacyRegulations().add(privacyRegulation);
        websiteRepository.save(website);
        return "Successfully added";
    }
    @Transactional
    public String deletePrivacyRegulationFromWebsite(Long websiteId, Long regulationId) {
        Website website = websiteRepository.findById(websiteId).orElse(null);
        PrivacyRegulation privacyRegulation = privacyRegulationRepository.findById(regulationId).orElse(null);

        //check for existence of regulations to the website

        if (website != null && privacyRegulation != null) {
            List<PrivacyRegulation> privacyRegulations = website.getPrivacyRegulations();
            privacyRegulations.removeIf(privacyRegulation1 -> privacyRegulation1.getRegulationId().equals(regulationId));
            website.setPrivacyRegulations(privacyRegulations);
            websiteRepository.save(website);
            return "deleted successfully";
        }

        return "Unsuccessful deletion";
    }

    public PrivacyRegulation updatePrivacyRegulation(Long regulationId, PrivacyRegulation privacyRegulation) {
        privacyRegulation.setRegulationId(regulationId);
        return privacyRegulationRepository.save(privacyRegulation);
    }


}
