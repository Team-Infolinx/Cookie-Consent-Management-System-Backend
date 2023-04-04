package com.websafe.ccmsbe.repository;

import com.websafe.ccmsbe.entity.CookieBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookieBannerRepository extends JpaRepository<CookieBanner,Long> {
}
