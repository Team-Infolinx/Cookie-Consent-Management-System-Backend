package com.websafe.ccmsbe.repository;

import com.websafe.ccmsbe.entity.Cookie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CookieRepository extends JpaRepository<Cookie , Long> {

}
