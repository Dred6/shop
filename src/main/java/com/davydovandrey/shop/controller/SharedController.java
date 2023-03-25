package com.davydovandrey.shop.controller;

import com.davydovandrey.shop.entity.Notification;
import com.davydovandrey.shop.entity.Organization;
import com.davydovandrey.shop.entity.Product;
import com.davydovandrey.shop.entity.Purchase;
import com.davydovandrey.shop.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class SharedController {

    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private PersonServiceImpl personService;
    @Autowired
    private PurchaseServiceImpl purchaseService;
    @Autowired
    private NotificationServiceImpl notificationService;
    @Autowired
    private OrganizationServiceImpl organizationService;

    @PostMapping("/addProduct")
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

    @PutMapping("/editProduct")
    public boolean editProduct(@RequestBody Product product){
        return productService.editProduct(product);
    }

    @GetMapping("/getListPurchase/{id}")
    public List<Purchase> getListPurchasePeronById(@PathVariable Long id){
        return personService.getListPurchasePerson(id);
    }
    @PutMapping("/returnPurchase/{id}")
    public boolean returnPurchase(@PathVariable Long id){
        Purchase purchase = purchaseService.getPurchaseById(id);
        if ((purchase.getDate().getTime() >= new Date().getTime() - 2) && (purchase.getDate().getTime() <= new Date().getTime() )){
            personService.topUpBalance(purchase.getBuyer(), purchase.getProduct().getPrice());
            purchaseService.deletePurchase(id);
            return true;
        } else{
            return false;
        }
    }
    @PostMapping("/buyProduct")
    public boolean buyProduct(@RequestBody Purchase purchase){
        if (purchase.getBuyer().getAccountBalance() >= purchase.getProduct().getPrice()){
            if (purchase.getProduct().getOrganization().isActivity() && !purchase.getProduct().getOrganization().isBlocked()
                    && !purchase.getProduct().getOrganization().isDeleted() && purchase.getProduct().getQuantityStock() > 0){
                purchaseService.addPurchase(purchase);
                personService.topDownBalance(purchase.getBuyer(), purchase.getProduct().getPrice());
                personService.topUpBalance(purchase.getProduct().getOrganization().getOwner(), (int) (purchase.getProduct().getPrice()*0.95));

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @PutMapping("/addReview")
    public boolean addReview(@RequestBody Purchase purchase) {
        return purchaseService.addReview(purchase);
    }

    @PutMapping("/addEstimation")
    public boolean addEstimation(@RequestBody Purchase purchase) {
        return purchaseService.addReview(purchase);
    }

    @GetMapping("/getListNotification")
    public List<Notification> getListNotificationSortByDataDesc(){
        return notificationService.getListNotification();
    }

    @PostMapping("/addOrganization/{id}")
    public boolean addOrganization(@PathVariable Long id, @RequestBody Organization organization){
        return organizationService.addOrganization(id, organization);
    }

    @GetMapping("/getListProducts")
    public List<Product> getListProductsNonDeletedAndBlockedOrganization(){
        return null;
    }
}
