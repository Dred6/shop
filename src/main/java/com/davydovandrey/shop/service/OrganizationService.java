package com.davydovandrey.shop.service;

import com.davydovandrey.shop.entity.Organization;

public interface OrganizationService {
    boolean addOrganization(Long id ,Organization organization);
    boolean acceptOrganization(Long id);
    void deleteOrganization(Long id);
}
