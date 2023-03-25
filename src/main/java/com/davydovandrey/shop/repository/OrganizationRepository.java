package com.davydovandrey.shop.repository;

import com.davydovandrey.shop.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
