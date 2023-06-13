package com.websafe.ccmsbe.controller;

import com.websafe.ccmsbe.entity.Cookie;
import com.websafe.ccmsbe.service.CookieSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin("http://localhost:3000")
public class CookieSearchController {

    private CookieSearchService cookieSearchService;

    @Autowired
    public CookieSearchController(CookieSearchService cookieSearchService) {
        this.cookieSearchService = cookieSearchService;
    }

    @GetMapping("/search-cookies/{websiteId}")
    public List<Cookie> scanCookies(@PathVariable(name = "websiteId") Long websiteId) {
        return cookieSearchService.searchAndSaveCookies(websiteId);
    }
}
