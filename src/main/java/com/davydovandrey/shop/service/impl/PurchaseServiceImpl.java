package com.davydovandrey.shop.service.impl;

import com.davydovandrey.shop.entity.Purchase;
import com.davydovandrey.shop.exception.NoSuchEntityException;
import com.davydovandrey.shop.repository.ProductRepository;
import com.davydovandrey.shop.repository.PurchaseRepository;
import com.davydovandrey.shop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, ProductRepository productRepository) {
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Purchase getPurchaseById(Long id){
        if (purchaseRepository.findById(id).isPresent()){
            return purchaseRepository.findById(id).get();
        } else {
            throw new NoSuchEntityException("Purchase not found");
        }
    }

    @Override
    public void deletePurchase(Long id) {
        purchaseRepository.deleteById(id);
    }

    @Override
    public void addPurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    @Override
    public boolean addReview(Purchase purchase) {
        if (purchaseRepository.findById(purchase.getId()).isPresent() && purchase.getReview() == null){
            purchaseRepository.save(purchase);
            purchase.getProduct().getListReview().add(purchase.getReview());
            productRepository.save(purchase.getProduct());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addEstimation(Purchase purchase) {
        if (purchaseRepository.findById(purchase.getId()).isPresent() && purchase.getEstimation() == null){
            purchaseRepository.save(purchase);
            List<Purchase> purchaseList = purchaseRepository.findAllByProduct(purchase.getProduct());
            ArrayList<Integer> rating = new ArrayList<>();
            for (Purchase p: purchaseList) {
                rating.add(p.getEstimation());
            }
            OptionalDouble average = rating.stream().mapToInt(e -> e).average();
            double averageRating = 0;
            if (average.isPresent()){
                averageRating = average.getAsDouble();
            }
            purchase.getProduct().setAverageRating(averageRating);
            productRepository.save(purchase.getProduct());
            return true;
        } else {
            return false;
        }
    }
}
