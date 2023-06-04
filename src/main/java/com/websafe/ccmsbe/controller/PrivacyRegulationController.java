package com.websafe.ccmsbe.controller;

import com.websafe.ccmsbe.entity.PrivacyRegulation;
import com.websafe.ccmsbe.service.PrivacyRegulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/v1/website-management")
public class PrivacyRegulationController {
    private final PrivacyRegulationService privacyRegulationService;
    @Autowired
    public PrivacyRegulationController(PrivacyRegulationService privacyRegulationService) {
        this.privacyRegulationService = privacyRegulationService;
    }
    @PostMapping("/privacy-regulation")
    public PrivacyRegulation createPrivacyRegulation(@RequestBody PrivacyRegulation privacyRegulation){
        return privacyRegulationService.createPrivacyRegulation(privacyRegulation);
    }
    @GetMapping("/all-privacy-regulations")
    public List<PrivacyRegulation> getAllPrivacyRegulations(){
        return privacyRegulationService.getAllPrivacyRegulations();
    }
    @PutMapping("/privacy-regulation/{regulationId}")
    public PrivacyRegulation updatePrivacyRegulation(
            @PathVariable(name = "regulationId") Long regulationId,
            @RequestBody PrivacyRegulation privacyRegulation
    ){
        return privacyRegulationService.updatePrivacyRegulation(regulationId, privacyRegulation);
    }
    @PutMapping("/{websiteId}/privacy-regulation-management/{regulationId}")
    public String addPrivacyRegulationToWebsite(
            @PathVariable(name = "websiteId") Long websiteId,
            @PathVariable(name = "regulationId") Long regulationId
    ) {
        return privacyRegulationService.addPrivacyRegulationToWebsite(websiteId,regulationId);
    }
    @DeleteMapping("/{websiteId}/privacy-regulation-management/{regulationId}")
    public String deletePrivacyRegulationFromWebsite(
            @PathVariable(name = "websiteId") Long websiteId,
            @PathVariable(name = "regulationId") Long regulationId
    ){
        return privacyRegulationService.deletePrivacyRegulationFromWebsite(websiteId, regulationId);
    }
}

