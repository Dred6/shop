package com.davydovandrey.shop.service;

import com.davydovandrey.shop.entity.Person;
import com.davydovandrey.shop.entity.Purchase;

import java.util.List;

public interface PersonService {
    List<Purchase> getListPurchasePerson(Long id);
    Person findPersonById(Long id);
    void topUpBalance(Person person , int amount);
    void topDownBalance(Person person ,int amount);
    List<Person> getListPersonNotDeleted(boolean deleted);
    boolean blokOrUnblockUser( Long id, boolean bool);
    boolean deleteUser(Long id);
    void addPerson(Person person);
}
