package com.itsol.junit.repository;

import com.itsol.junit.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findFirstByProductName(String productName);
    void deleteById(Long productId);
}
