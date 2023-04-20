package com.websafe.ccmsbe.controller;

import com.websafe.ccmsbe.entity.CookieCategory;
import com.websafe.ccmsbe.service.CookieCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin("http://localhost:3000")
public class CookieCategoryController {

    private final CookieCategoryService cookieCategoryService;

    @Autowired
    public CookieCategoryController(CookieCategoryService cookieCategoryService) {
        this.cookieCategoryService = cookieCategoryService;
    }

    @GetMapping("/{websiteId}/getCookieCategories")
    public List<CookieCategory> getCookieCategories(
            @PathVariable(name = "websiteId") Long websiteId
    ){
        return cookieCategoryService.getCookieCategories(websiteId);
    }

    @PostMapping("/{websiteId}/addCookieCategory")
    public CookieCategory addNewCategory(@PathVariable(name = "websiteId") Long websiteId , @RequestBody CookieCategory cookieCategory) {
        return cookieCategoryService.addNewCategory(websiteId , cookieCategory);
    }

    @PostMapping("/{websiteId}/addCookieCategoryList")
    public List<CookieCategory> addCookieCategoryList(
            @PathVariable(name = "websiteId") Long websiteId,
            @RequestBody List<CookieCategory> cookieCategoryList
    ){
        return cookieCategoryService.addCookieCategoryList(websiteId,cookieCategoryList);
    }


    @DeleteMapping("/{websiteId}/{categoryId}/deleteCategory")
    public Boolean deleteCookieCategory(
            @PathVariable(name = "websiteId") Long websiteId,
            @PathVariable(name = "categoryId") Long categoryId
    ){
        return cookieCategoryService.deleteCookieCategory(websiteId,categoryId);
    }

    @PutMapping("/{categoryId}/updateCategory/{newCategoryName}")
    public CookieCategory updateCookieCategory(
            @PathVariable(name = "categoryId") Long categoryId,
            @PathVariable(name = "newCategoryName") String categoryName
    ) {
        return cookieCategoryService.updateCookieCategory(categoryId,categoryName);
    }

}
