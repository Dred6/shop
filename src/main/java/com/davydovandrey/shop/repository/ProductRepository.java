package com.davydovandrey.shop.repository;

import com.davydovandrey.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrganization_BlockedAndOrganization_DeletedAndOrganization_Activity(boolean blocked ,boolean deleted, boolean activity);
}
