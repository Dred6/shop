package com.davydovandrey.shop.service;

import com.davydovandrey.shop.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getListProduct();

    void addProduct(Product product);

    boolean editProduct(Product product);

    List<Product> getListProductNonActivity();

    boolean acceptProduct(Long id);
}
