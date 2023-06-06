package com.websafe.ccmsbe.controller;
import com.websafe.ccmsbe.entity.Cookie;
import com.websafe.ccmsbe.service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/websites/{websiteId}/cookies")
@CrossOrigin
public class CookieController {
    private final CookieService cookieService;

    @Autowired
    public CookieController(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    @GetMapping
    public List<Cookie> getCookiesToWebsite(@PathVariable(name = "websiteId") Long websiteId) {
        return this.cookieService.getCookiesToWebsite(websiteId);
    }

    @PostMapping
    public Cookie addCookieManually(@PathVariable(name="websiteId") Long websiteId, @RequestBody Cookie cookie) {
        return cookieService.addCookieManually(websiteId, cookie);
    }

    @DeleteMapping("/{cookieId}")
    public Boolean deleteCookie(
            @PathVariable(name = "websiteId") Long websiteId,
            @PathVariable(name = "cookieId") Long cookieId
    ) {
        return cookieService.deleteCookie(websiteId,cookieId);
    }

    @PutMapping("{cookieId}/cookie-categories/{cookieCategoryId}")
    public Cookie updateCategoryInCookie(
            @PathVariable(name = "websiteId") Long websiteId,
            @PathVariable(name = "cookieId") Long cookieId,
            @PathVariable(name = "cookieCategoryId") Long cookieCategoryId
    ) {
        return cookieService.updateCategoryInCookie(websiteId, cookieId, cookieCategoryId);
    }

    @PutMapping("/{cookieId}")
    public Cookie updateCookie(
            @PathVariable(name = "websiteId") Long websiteId,
            @PathVariable(name = "cookieId") Long cookieId,
            @RequestBody Cookie cookie
    ) {
        return  cookieService.updateCookie(websiteId, cookieId, cookie);
    }
}
