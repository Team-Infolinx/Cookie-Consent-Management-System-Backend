package com.websafe.ccmsbe.controller;
import com.websafe.ccmsbe.entity.Website;
import com.websafe.ccmsbe.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{userId}/websites")
@CrossOrigin
public class WebsiteController {

    private final WebsiteService websiteService;

    @Autowired
    public WebsiteController(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @GetMapping
    public List<Website> getAllWebsitesByUserId(@PathVariable(name = "userId") Long userId) {
        return websiteService.getWebsitesByUserId(userId);
    }

    @GetMapping("/{websiteId}")
    public Website getWebsiteByUserIdAndWebsiteId(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "websiteId") Long websiteId
    ) {
        return websiteService.getWebsiteByUserIdAndWebsiteId(userId,websiteId);
    }

    @PostMapping
    public Website addWebsite(@PathVariable(name = "userId") Long userId, @RequestBody Website website){
        return websiteService.addWebsite(userId,website);
    }

    @DeleteMapping("/{websiteId}")
    public Boolean deleteWebsite(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "websiteId") Long websiteId
    ){
        return websiteService.deleteWebsite(userId,websiteId);
    }

    @PutMapping("/{websiteId}")
    public Website updateWebsite(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "websiteId") Long websiteId,
            @RequestBody Website website

    ){
        return  websiteService.updateWebsite(userId,websiteId,website);
    }

}
