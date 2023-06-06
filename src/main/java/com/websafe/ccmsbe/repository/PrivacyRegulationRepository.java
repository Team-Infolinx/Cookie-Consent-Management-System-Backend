package com.websafe.ccmsbe.repository;

import com.websafe.ccmsbe.entity.PrivacyRegulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivacyRegulationRepository extends JpaRepository<PrivacyRegulation , Long> {
}
