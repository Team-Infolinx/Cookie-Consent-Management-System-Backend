package com.websafe.ccmsbe.repository;

import com.websafe.ccmsbe.entity.Cookie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CookieRepository extends JpaRepository<Cookie , Long> {
    @Query(value = "select count(cookie_id) FROM cookie where website_id=?1",nativeQuery = true)
    Integer getCookieByWebsiteId(String websiteId);
}
