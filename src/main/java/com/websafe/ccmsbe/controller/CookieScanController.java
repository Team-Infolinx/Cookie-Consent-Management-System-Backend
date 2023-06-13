package com.websafe.ccmsbe.controller;

import com.websafe.ccmsbe.service.CookieScanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin("http://localhost:3000")
public class CookieScanController {

    private final CookieScanService cookieScanService;

    public CookieScanController(CookieScanService cookieScanService) {
        this.cookieScanService = cookieScanService;
    }

    @GetMapping("/scan-cookies")
    public List<String> scanCookies(@RequestParam("websiteUrl") String websiteUrl) {
        return cookieScanService.scanCookies(websiteUrl);
    }
}
