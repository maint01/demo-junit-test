package com.itsol.junit.service;

import com.itsol.junit.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    List<Product> findByProductName(String productName);
    Product findById(Long productId);
    Product save(Product product);
    void update(Product product);
    void delete(Product product);
    void delete(Long productId);

}
