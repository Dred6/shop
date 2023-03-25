package com.davydovandrey.shop.service.impl;

import com.davydovandrey.shop.entity.InformationAboutDiscounts;
import com.davydovandrey.shop.repository.InformationAboutDiscountsRepository;
import com.davydovandrey.shop.service.InformationAboutDiscountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformationAboutDiscountsServiceImpl implements InformationAboutDiscountsService {
    private final InformationAboutDiscountsRepository informationAboutDiscountsRepository;

    public InformationAboutDiscountsServiceImpl(InformationAboutDiscountsRepository informationAboutDiscountsRepository) {
        this.informationAboutDiscountsRepository = informationAboutDiscountsRepository;
    }

    @Override
    public void addDiscount(InformationAboutDiscounts informationAboutDiscounts) {
        informationAboutDiscountsRepository.save(informationAboutDiscounts);
    }

    @Override
    public boolean editDiscount(InformationAboutDiscounts informationAboutDiscounts) {
        if (informationAboutDiscountsRepository.findById(informationAboutDiscounts.getId()).isPresent()){
            informationAboutDiscountsRepository.save(informationAboutDiscounts);
            return true;
        } else {
            return false;
        }
    }
}
