package com.davydovandrey.shop.repository;

import com.davydovandrey.shop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
