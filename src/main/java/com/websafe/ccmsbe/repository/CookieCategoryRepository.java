package com.websafe.ccmsbe.repository;

import com.websafe.ccmsbe.entity.CookieCategory;
import com.websafe.ccmsbe.entity.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CookieCategoryRepository extends JpaRepository<CookieCategory,Long> {

    @Query
    public List<CookieCategory> getCookieCategoriesByWebsiteEquals(Website website);

    @Query(value = "SELECT cc.categoryName FROM CookieCategory cc WHERE cc.website = ?1")
    public List<String>  getCookieCategoryNamesByWebsite(Website website);

    @Query
    public CookieCategory findByCategoryIdAndWebsite(Long categoryId , Website website);

}
