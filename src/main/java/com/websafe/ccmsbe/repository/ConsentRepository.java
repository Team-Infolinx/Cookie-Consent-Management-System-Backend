package com.websafe.ccmsbe.repository;

import com.websafe.ccmsbe.entity.Consent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentRepository extends JpaRepository<Consent , Long> {
}
