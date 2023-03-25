package com.davydovandrey.shop.service.impl;

import com.davydovandrey.shop.entity.Organization;
import com.davydovandrey.shop.entity.Person;
import com.davydovandrey.shop.repository.OrganizationRepository;
import com.davydovandrey.shop.repository.PersonRepository;
import com.davydovandrey.shop.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private PersonRepository personRepository;

    @Override
    public boolean addOrganization(Long id, Organization organization) {
        if (personRepository.findById(id).isPresent()){
            Person person = personRepository.findById(id).get();
            if (!person.isBlocked()){
                organizationRepository.save(organization);
                person.getListOrganization().add(organization);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean acceptOrganization(Long id) {
        if (organizationRepository.findById(id).isPresent()){
            Organization organization = organizationRepository.findById(id).get();
            organization.setActivity(true);
            organizationRepository.save(organization);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteOrganization(Long id) {
        if (organizationRepository.findById(id).isPresent()){
            Organization organization = organizationRepository.findById(id).get();
            if (organization.getProductList() == null){
                organizationRepository.deleteById(id);
            } else{
                organization.setDeleted(true);
                organizationRepository.save(organization);
            }
        }
    }
}
