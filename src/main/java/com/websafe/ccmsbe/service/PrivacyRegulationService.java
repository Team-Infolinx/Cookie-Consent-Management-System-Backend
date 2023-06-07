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
    private final PrivacyRegulationRepository privacyRegulationRepository;

    private final WebsiteRepository websiteRepository;

    //User define exception for 'PrivacyRegulationNotFound' error
    public static class PrivacyRegulationNotFoundException extends RuntimeException {
        public PrivacyRegulationNotFoundException(String message) {
            super(message);
        }
    }

    //User define exception for 'WebsiteNotFound' error
    public static class WebsiteNotFoundException extends RuntimeException {
        public WebsiteNotFoundException(String message) {
            super(message);
        }
    }

    //User define exception for PrivacyRegulationUpdate errors
    public static class PrivacyRegulationUpdateException extends RuntimeException {
        public PrivacyRegulationUpdateException(String message) {
            super(message);
        }
    }

    //User define exception for PrivacyRegulationCreation errors
    public static class PrivacyRegulationCreationException extends RuntimeException {
        public PrivacyRegulationCreationException(String message) {
            super(message);
        }
    }


    //User define exception for PrivacyRegulationRetrieval errors
    public static class PrivacyRegulationRetrievalException extends RuntimeException {
        public PrivacyRegulationRetrievalException(String message) {
            super(message);
        }
    }

    @Autowired
    public PrivacyRegulationService(PrivacyRegulationRepository privacyRegulationRepository, WebsiteRepository websiteRepository) {
        this.privacyRegulationRepository = privacyRegulationRepository;
        this.websiteRepository = websiteRepository;
    }

    public PrivacyRegulation createPrivacyRegulation(PrivacyRegulation privacyRegulation) {
        try {
            if (privacyRegulation == null) {
                throw new IllegalArgumentException("Privacy regulation cannot be null");
            }

            return privacyRegulationRepository.save(privacyRegulation);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new PrivacyRegulationCreationException("An error occurred while creating the privacy regulation");
        }
    }

    public List<PrivacyRegulation> getAllPrivacyRegulations() {
        try {
            return privacyRegulationRepository.findAll();
        } catch (Exception e) {
            throw new PrivacyRegulationRetrievalException("An error occurred while retrieving all privacy regulations");
        }
    }

    @Transactional
    public String addPrivacyRegulationToWebsite(Long websiteId, Long regulationId) {
        try {
            Website website = websiteRepository.findById(websiteId).orElse(null);

            PrivacyRegulation privacyRegulation = privacyRegulationRepository.findById(regulationId).orElse(null);

            if (website == null || privacyRegulation == null) {
                throw new PrivacyRegulationNotFoundException("Invalid website ID or regulation ID");
            }

            if (website.getPrivacyRegulations().contains(privacyRegulation)) {
                throw new PrivacyRegulationNotFoundException("Regulation already added to the website");
            }

            website.getPrivacyRegulations().add(privacyRegulation);

            websiteRepository.save(website);

            return "Successfully added";

        } catch (PrivacyRegulationNotFoundException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "An error occurred while adding the privacy regulation";
        }
    }

    @Transactional
    public String deletePrivacyRegulationFromWebsite(Long websiteId, Long regulationId) {
        try {
            Website website = websiteRepository.findById(websiteId).orElseThrow(
                    () -> new WebsiteNotFoundException("Website not found with ID: " + websiteId)
            );

            PrivacyRegulation privacyRegulation = privacyRegulationRepository.findById(regulationId).orElseThrow(
                    () -> new PrivacyRegulationNotFoundException("Privacy regulation not found with ID: " + regulationId)
            );

            List<PrivacyRegulation> privacyRegulations = website.getPrivacyRegulations();

            boolean removed = privacyRegulations.removeIf(privacyRegulation1 -> privacyRegulation1.getRegulationId().equals(regulationId));

            if (removed) {
                website.setPrivacyRegulations(privacyRegulations);
                websiteRepository.save(website);

                return ("Privacy regulation with ID:" + regulationId + " is deleted successfully");
            } else {
                throw new PrivacyRegulationNotFoundException("Privacy regulation with ID: " + regulationId +
                        " is not associated with the website with ID: " + websiteId);
            }
        } catch (WebsiteNotFoundException | PrivacyRegulationNotFoundException e) {
            return e.getMessage();

        } catch (Exception e) {
            return "An error occurred while deleting the privacy regulation";
        }
    }

    public PrivacyRegulation updatePrivacyRegulation(Long regulationId, PrivacyRegulation privacyRegulation) {
        try {
            PrivacyRegulation existingRegulation = privacyRegulationRepository.findById(regulationId)
                    .orElseThrow(() -> new PrivacyRegulationNotFoundException("Privacy regulation not found with ID: " + regulationId));

            existingRegulation.setRegulationId(regulationId);

            existingRegulation.setRegulationName(privacyRegulation.getRegulationName());

            //existingRegulation.setRegulationDescription(privacyRegulation.getRegulationDescription());

            return privacyRegulationRepository.save(existingRegulation);

        } catch (PrivacyRegulationNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new PrivacyRegulationUpdateException("An error occurred while updating the privacy regulation");
        }
    }
}
