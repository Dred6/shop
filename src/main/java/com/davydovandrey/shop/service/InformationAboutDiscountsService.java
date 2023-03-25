package com.davydovandrey.shop.service;

import com.davydovandrey.shop.entity.InformationAboutDiscounts;

public interface InformationAboutDiscountsService {

    void addDiscount(InformationAboutDiscounts informationAboutDiscounts);

    boolean editDiscount(InformationAboutDiscounts informationAboutDiscounts);
}
