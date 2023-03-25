package com.davydovandrey.shop.service.impl;

import com.davydovandrey.shop.entity.Product;
import com.davydovandrey.shop.repository.ProductRepository;
import com.davydovandrey.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getListProduct() {
        return productRepository.findAllByOrganization_BlockedAndOrganization_DeletedAndOrganization_Activity(false, false,true);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public boolean editProduct(Product product) {
        if (productRepository.findById(product.getId()).isPresent()){
            productRepository.save(product);
            return true;
        } else{
            return false;
        }
    }

    @Override
    public List<Product> getListProductNonActivity() {
        return productRepository.findAllByOrganization_BlockedAndOrganization_DeletedAndOrganization_Activity(false, false,false);
    }

    @Override
    public boolean acceptProduct(Long id) {
        if (productRepository.findById(id).isPresent()){
            Product product = productRepository.findById(id).get();
            product.setActivity(true);
            productRepository.save(product);
            return true;
        } else {
            return false;
        }
    }
}
