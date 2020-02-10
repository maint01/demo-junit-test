package com.itsol.junit.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsol.junit.entities.Product;
import com.itsol.junit.service.ProductService;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;

public class ProductResourceTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ProductResource productResource;

    @Mock
    private ProductService productService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productResource).build();
    }

    @Test
    public void findAll_OK() throws Exception {
        Product product1 = new Product(1L, "Product1", 1234L);
        Product product2 = new Product(2L, "Product2", 1234L);
        List<Product> products = Arrays.asList(product1, product2);
        Mockito.when(productService.findAll()).thenReturn(products);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].productName").value("Product1"));
    }

    @Test
    public void findProductById_OK() throws Exception {
        Product product1 = new Product(1L, "Product1", 1234L);
        Mockito.when(productService.findById(1L)).thenReturn(product1);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/{id}", 1L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.productName").value("Product1"))
                .andExpect(jsonPath("$.quantity").value(1234L));
        Mockito.verify(productService, Mockito.times(1)).findById(1L);
    }

    @Test
    public void findProductById_404_not_found() throws Exception {
//        Product product1 = new Product(1L, "Product1", 1234L);
        Mockito.when(productService.findById(1L)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/{id}", 1L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
        Mockito.verify(productService, Mockito.times(1)).findById(1L);
    }

    @Test
    public void createProduct_OK() throws Exception {
        Product product1 = new Product(null, "Product1", 1234L);
        Product product2 = new Product(1L, "Product1", 1234L);
        Mockito.when(productService.save(product1)).thenReturn(product2);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJson(product1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty());
        Mockito.verify(productService, Mockito.times(1)).save(product1);
    }

    @Test
    public void createProduct_internal_server_error() throws Exception {
        Product product1 = new Product(null, "product", 1234L);
        Mockito.doThrow(ConstraintViolationException.class).when(productService).save(product1);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJson(product1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isInternalServerError());
        Mockito.verify(productService, Mockito.times(1)).save(product1);
    }

    private String toJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
