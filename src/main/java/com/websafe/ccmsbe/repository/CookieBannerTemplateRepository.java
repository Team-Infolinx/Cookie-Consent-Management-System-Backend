package com.websafe.ccmsbe.repository;

import com.websafe.ccmsbe.entity.CookieBannerTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CookieBannerTemplateRepository extends JpaRepository<CookieBannerTemplate , Long> {




}
