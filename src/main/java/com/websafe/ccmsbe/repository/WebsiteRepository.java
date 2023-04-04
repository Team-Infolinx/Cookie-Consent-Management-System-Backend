package com.websafe.ccmsbe.repository;

import com.websafe.ccmsbe.entity.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsiteRepository extends JpaRepository<Website , Long> {
}
