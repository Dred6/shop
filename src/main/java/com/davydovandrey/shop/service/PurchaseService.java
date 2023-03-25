package com.davydovandrey.shop.service;

import com.davydovandrey.shop.entity.Purchase;

public interface PurchaseService {
    Purchase getPurchaseById(Long id);
    void deletePurchase(Long id);
    void addPurchase(Purchase purchase);
    boolean addReview(Purchase purchase);
    boolean addEstimation(Purchase purchase);
}
