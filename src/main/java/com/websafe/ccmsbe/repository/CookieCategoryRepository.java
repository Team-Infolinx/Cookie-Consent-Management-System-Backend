package com.websafe.ccmsbe.repository;

import com.websafe.ccmsbe.entity.CookieCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookieCategoryRepository extends JpaRepository<CookieCategory,Long> {
}
