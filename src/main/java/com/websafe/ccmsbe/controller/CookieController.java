package com.websafe.ccmsbe.controller;

import com.websafe.ccmsbe.entity.Cookie;
import com.websafe.ccmsbe.entity.CookieCategory;
import com.websafe.ccmsbe.service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin("http://localhost:3000")
public class CookieController {

    private final CookieService cookieService;

    @Autowired
    public CookieController(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    @GetMapping("{websiteId}/getCookies")
    public List<Cookie> getCookiesToWebsite(@PathVariable(name = "websiteId") Long websiteId) {
        return this.cookieService.getCookiesToWebsite(websiteId);
    }
//    Not in use -> to remove.
    @PutMapping("/{websiteId}/setCategories")
    public List<Cookie> addCookieCategoryToCookies(@PathVariable(name = "websiteId") Long websiteId, @RequestBody List<Cookie> cookies){
        return cookieService.addCookieCategoryToCookies(websiteId , cookies);
    }

    @PostMapping("/{websiteId}/addCookie")
    public Cookie addCookieManually(@PathVariable(name="websiteId") Long websiteId, @RequestBody Cookie cookie) {
        return cookieService.addCookieManually(websiteId, cookie);
    }
//  Not in use -> remove.
    @PutMapping("/{websiteId}/{cookieId}/setCategoryToCookie")
    public Cookie addCookieCategoryToCookie(
            @PathVariable(name = "websiteId") Long websiteId,
            @PathVariable(name = "cookieId") Long cookieId,
            @RequestBody CookieCategory cookieCategory
    ){
        return cookieService.addCookieCategoryToCookie(cookieId,websiteId,cookieCategory);
    }

    @DeleteMapping("/{websiteId}/{cookieId}/deleteCookie")
    public Boolean deleteCookie(
            @PathVariable(name = "websiteId") Long websiteId,
            @PathVariable(name = "cookieId") Long cookieId
    ) {
        return cookieService.deleteCookie(websiteId,cookieId);
    }

    @PutMapping("/{cookieId}/{cookieCategoryId}/updateCategoryInCookie")
    public Cookie updateCategoryInCookie(
            @PathVariable(name = "cookieId") Long cookieId,
            @PathVariable(name = "cookieCategoryId") Long cookieCategoryId
    ) {
        return cookieService.updateCategoryInCookie(cookieId, cookieCategoryId);
    }


}
