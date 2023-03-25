package com.davydovandrey.shop.controller;

import com.davydovandrey.shop.entity.InformationAboutDiscounts;
import com.davydovandrey.shop.entity.Notification;
import com.davydovandrey.shop.entity.Person;
import com.davydovandrey.shop.entity.Product;
import com.davydovandrey.shop.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    private final InformationAboutDiscountsServiceImpl informationAboutDiscountsService;
    private final PersonServiceImpl personService;
    private final NotificationServiceImpl notificationService;
    private final OrganizationServiceImpl organizationService;
    private final ProductServiceImpl productService;

    public AdminController(InformationAboutDiscountsServiceImpl informationAboutDiscountsService, PersonServiceImpl personService, NotificationServiceImpl notificationService, OrganizationServiceImpl organizationService, ProductServiceImpl productService) {
        this.informationAboutDiscountsService = informationAboutDiscountsService;
        this.personService = personService;
        this.notificationService = notificationService;
        this.organizationService = organizationService;
        this.productService = productService;
    }

    @PostMapping("/addDiscount")
    public void addDiscount(@RequestBody InformationAboutDiscounts informationAboutDiscounts){
        informationAboutDiscountsService.addDiscount(informationAboutDiscounts);
    }

    @PutMapping("/editDiscount")
    public boolean editDiscount(@RequestBody InformationAboutDiscounts informationAboutDiscounts){
        return informationAboutDiscountsService.editDiscount(informationAboutDiscounts);
    }

    @PutMapping("/topUpBalance/{id}")
    public boolean topUpBalance(@PathVariable Long id, @RequestBody int amount){
        if (personService.findPersonById(id) != null){
            personService.topUpBalance(personService.findPersonById(id), amount);
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/getListPersons")
    public List<Person> getListPerson(){
        return personService.getListPersonNotDeleted(false);
    }

    @PutMapping("/blockedUser/{id}")
    public boolean blokOrUnblockUser(@PathVariable Long id, boolean bool){
        return personService.blokOrUnblockUser(id,bool);
    }

    @PutMapping("/deletePerson/{id}")
    public boolean deletePerson(@PathVariable Long id){
        return personService.deleteUser(id);
    }

    @PostMapping("/addPerson")
    public void addPerson(){

    }

    @PostMapping("/addNotification")
    public void addNotification(@RequestBody Notification notification){
        notificationService.addNotification(notification);
    }

    @PutMapping("/acceptOrganization/{id}")
    public boolean acceptOrganization(@PathVariable Long id){
        return organizationService.acceptOrganization(id);
    }

    @DeleteMapping("/deleteOrganization/{id}")
    public void deleteOrganization(@PathVariable Long id){
        organizationService.deleteOrganization(id);
    }

    @GetMapping("/getListNonActivity")
    public List<Product> getListNonActivity(){
        return productService.getListProductNonActivity();
    }

    @PutMapping("/acceptProduct/{id}")
    public boolean acceptProduct(@PathVariable Long id){
        return false;
    }
}
