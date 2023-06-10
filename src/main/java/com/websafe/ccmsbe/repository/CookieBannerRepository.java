package com.websafe.ccmsbe.repository;

import com.websafe.ccmsbe.entity.CookieBanner;
import com.websafe.ccmsbe.entity.CookieBannerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface CookieBannerRepository extends JpaRepository<CookieBanner,Long> {



}