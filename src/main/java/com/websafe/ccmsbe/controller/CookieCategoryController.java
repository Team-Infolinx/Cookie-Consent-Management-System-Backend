package com.websafe.ccmsbe.controller;

import com.websafe.ccmsbe.entity.CookieCategory;
import com.websafe.ccmsbe.service.CookieCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/websites/{websiteId}/cookie-categories")
@CrossOrigin
public class CookieCategoryController {
    private final CookieCategoryService cookieCategoryService;

    @Autowired
    public CookieCategoryController(CookieCategoryService cookieCategoryService) {
        this.cookieCategoryService = cookieCategoryService;
    }

    @CrossOrigin("*")
    @GetMapping
    public List<CookieCategory> getCookieCategories(
            @PathVariable(name = "websiteId") Long websiteId
    ){
        return cookieCategoryService.getCookieCategories(websiteId);
    }

    @PostMapping
    public CookieCategory addNewCategory(@PathVariable(name = "websiteId") Long websiteId , @RequestBody CookieCategory cookieCategory) {
        return cookieCategoryService.addNewCategory(websiteId , cookieCategory);
    }

    @PostMapping("/list")
    public List<CookieCategory> addCookieCategoryList(
            @PathVariable(name = "websiteId") Long websiteId,
            @RequestBody List<CookieCategory> cookieCategoryList
    ){
        return cookieCategoryService.addCookieCategoryList(websiteId,cookieCategoryList);
    }

    @DeleteMapping("/{categoryId}")
    public Boolean deleteCookieCategory(
            @PathVariable(name = "websiteId") Long websiteId,
            @PathVariable(name = "categoryId") Long categoryId
    ){
        return cookieCategoryService.deleteCookieCategory(websiteId,categoryId);
    }

    @PutMapping("/{categoryId}")
    public CookieCategory updateCookieCategory(
            @PathVariable (name = "websiteId") Long websiteId,
            @PathVariable (name = "categoryId") Long categoryId,
            @RequestBody CookieCategory cookieCategory
    ) {
        return cookieCategoryService.updateCookieCategory(websiteId, categoryId, cookieCategory);
    }
}
