package com.websafe.ccmsbe.repository;

import com.websafe.ccmsbe.entity.Consent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ConsentRepository extends JpaRepository<Consent , Long> {

    @Query(value = "select count(consent_id) FROM consent where website_id=?1",nativeQuery = true)
    Integer getConsentByWebsiteId(String websiteId);

    @Query(value = "select count(consent_id) FROM consent where website_id=?1 and is_given='true'",nativeQuery = true)
    Integer getAcceptedConsentByWebsiteId(String websiteId);

    @Query(value = "select count(consent_id) FROM consent where website_id=?1 and is_given='true' group by created_date",nativeQuery = true)
    List<Integer> getConsentCountGroupByDate(String websiteId);

    @Query(value = "select created_date FROM consent where website_id=?1 and is_given='true' group by created_date",nativeQuery = true)
    List<Date> getConsentDatesByWebsiteId(String websiteId);
}
