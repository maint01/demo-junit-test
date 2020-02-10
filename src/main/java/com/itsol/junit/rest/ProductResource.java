package com.itsol.junit.rest;

import com.itsol.junit.entities.Product;
import com.itsol.junit.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductResource {
    private Logger log = LoggerFactory.getLogger(getClass());

    private final ProductService productService;

    public ProductResource(ProductService  productService){
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProduct(){
        log.info("REST to get all product");
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        log.info("REST to get one product");
        Product product = productService.findById(id);
        if(product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Product> create(@RequestBody Product product){
        log.info("REST to get create product: {}", product);
        try {
            product = productService.save(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
