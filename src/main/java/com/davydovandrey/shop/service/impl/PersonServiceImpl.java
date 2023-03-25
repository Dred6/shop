package com.davydovandrey.shop.service.impl;

import com.davydovandrey.shop.entity.Person;
import com.davydovandrey.shop.entity.Purchase;
import com.davydovandrey.shop.exception.NoSuchEntityException;
import com.davydovandrey.shop.repository.PersonRepository;
import com.davydovandrey.shop.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService, UserDetailsService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person findPersonById(Long id){
        return personRepository.findById(id).orElse(null);
    }
    @Override
    public List<Purchase> getListPurchasePerson(Long id) {
        if (personRepository.findById(id).isPresent()){
            Person personFromDB = personRepository.findById(id).get();
            return personFromDB.getListPurchases();
        } else {
            new NoSuchEntityException("Person not found");
            return null;
        }
    }
    @Override
    public void topUpBalance(Person person ,int amount){
        person.setAccountBalance(person.getAccountBalance()+amount);
    }
    @Override
    public void topDownBalance(Person person ,int amount){
        person.setAccountBalance(person.getAccountBalance()-amount);
    }

    @Override
    public List<Person> getListPersonNotDeleted(boolean deleted) {
        return personRepository.findAllByDeleted(deleted);
    }

    @Override
    public boolean blokOrUnblockUser(Long id, boolean bool) {
        if (personRepository.findById(id).isPresent()){
            Person person = personRepository.findById(id).get();
            person.setBlocked(bool);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        if (personRepository.findById(id).isPresent()){
            Person person = personRepository.findById(id).get();
            person.setDeleted(true);
            personRepository.save(person);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addPerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);

        if (person == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return person;
    }
}
