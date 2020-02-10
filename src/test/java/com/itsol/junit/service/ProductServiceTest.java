package com.itsol.junit.service;

import com.itsol.junit.config.AppProfileConstants;
import com.itsol.junit.entities.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(AppProfileConstants.PROFILE_ACCEPTANCE)
public class ProductServiceTest {
    private static final String DEFAULT_PRODUCT_NAME = "ProductNameTest";
    private static final String DEFAULT_PRODUCT_NAME_2 = "ProductNameTest2";

    @Autowired
    private ProductService productService;

    @Test
    public void findAll_OK(){
        Product product = new Product();
        product.setProductName(DEFAULT_PRODUCT_NAME);
        product.setQuantity(100L);
        productService.save(product);
        product = new Product();
        product.setProductName(DEFAULT_PRODUCT_NAME_2);
        product.setQuantity(100L);
        productService.save(product);
        List<Product> lstProduct = productService.findAll();
        Assert.assertEquals(2, lstProduct.size());
        lstProduct.forEach(x->productService.delete(x));
    }

    @Test
    public void insertTest() {
        Product product = new Product();
        product.setProductName(DEFAULT_PRODUCT_NAME);
        product.setQuantity(100L);
        productService.save(product);
        Assert.assertNotNull(product.getId());
        productService.delete(product);
    }

    @Test
    public void findByIdNotExist_OK() {
        productService.delete(-1L);
        Product product = productService.findById(-1L);
        Assert.assertNull(product);
    }

    @Test
    public void findByIdExist_OK() {
        Product product = new Product();
        product.setProductName(DEFAULT_PRODUCT_NAME);
        product.setQuantity(100L);
        productService.save(product);
        product = productService.findById(product.getId());
        Assert.assertNotNull(product);
        productService.delete(product);
    }

    @Test
    public void update_OK() {
        Product product = new Product();
        product.setProductName(DEFAULT_PRODUCT_NAME);
        product.setQuantity(100L);
        productService.save(product);
        product = productService.findById(product.getId());
        product.setQuantity(200L);
        product.setProductName(DEFAULT_PRODUCT_NAME_2);
        productService.update(product);
        product = productService.findById(product.getId());
        Assert.assertEquals(DEFAULT_PRODUCT_NAME_2, product.getProductName());
        Assert.assertEquals(200, product.getQuantity().longValue());
        productService.delete(product);
    }

    @Test
    public void delete_OK() {
        Product product = new Product();
        product.setProductName(DEFAULT_PRODUCT_NAME);
        product.setQuantity(100L);
        productService.save(product);
        Assert.assertNotNull(product.getId());
        productService.delete(product);
        product = productService.findById(product.getId());
        Assert.assertNull(product);
    }
}
