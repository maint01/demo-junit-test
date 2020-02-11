package com.itsol.junit.service;

import com.itsol.junit.dto.ProductDto;
import com.itsol.junit.entities.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    List<Product> findByProductName(String productName);
    ProductDto findById(Long productId);
    ProductDto save(ProductDto productDto);
    void update(ProductDto productDto);
    void delete(ProductDto productDto);
    void delete(Long productId);

}
