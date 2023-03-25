package com.davydovandrey.shop.repository;

import com.davydovandrey.shop.entity.Product;
import com.davydovandrey.shop.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByProduct(Product product);
}
