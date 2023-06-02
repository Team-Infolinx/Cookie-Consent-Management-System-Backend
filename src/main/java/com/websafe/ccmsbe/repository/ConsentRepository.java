package com.websafe.ccmsbe.repository;

import com.websafe.ccmsbe.entity.Consent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentRepository extends JpaRepository<Consent , Long> {

    @Query(value = "select count(consent_id) FROM consent where website_id=?1",nativeQuery = true)
    Integer getConsentByWebsiteId(String websiteId);

    @Query(value = "select count(consent_id) FROM consent where website_id=?1 and consent=true",nativeQuery = true)
    Integer getAcceptedConsentByWebsiteId(String websiteId);
}
