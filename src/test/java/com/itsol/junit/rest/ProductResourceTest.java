package com.itsol.junit.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsol.junit.dto.ProductDto;
import com.itsol.junit.exception.RecordNotFoundException;
import com.itsol.junit.service.ProductService;
import org.hamcrest.Matchers;
import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        ProductDto product1 = new ProductDto(1L, "Product1", 1234L);
        ProductDto product2 = new ProductDto(3L, "Product2", 1234L);
        List<ProductDto> products = Arrays.asList(product1, product2);
        Mockito.when(productService.findAll()).thenReturn(products);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].productName").value("Product1"))
                .andExpect(jsonPath("$[0].quantity").value(1234))
                .andExpect(jsonPath("$[1].id").value(3))
                .andExpect(jsonPath("$[1].productName").value("Product2"))
                .andExpect(jsonPath("$[1].quantity").value(1234));
    }

    @Test
    public void findProductById_OK() throws Exception {
        ProductDto product1 = new ProductDto(1L, "Product2", 1234L);
        Mockito.when(productService.findById(1L)).thenReturn(product1);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/{id}", 1L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.productName").value("Product2"))
                .andExpect(jsonPath("$.quantity").value(1234L));
        Mockito.verify(productService, Mockito.times(1)).findById(1L);
    }

    @Test
    public void findProductById_404_not_found() throws Exception {
        Mockito.when(productService.findById(1L)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/{id}", 1L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
        Mockito.verify(productService, Mockito.times(1)).findById(1L);
    }

    @Test
    public void createProduct_OK() throws Exception {
        ProductDto product1 = new ProductDto(null, "Product1", 1234L);
        ProductDto productReturn = new ProductDto(1L, "Product1", 1234L);
        Mockito.when(productService.save(product1)).thenReturn(productReturn);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(product1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.productName").value("Product1"))
                .andExpect(jsonPath("$.quantity").value(1234L));
        Mockito.verify(productService, Mockito.times(1)).save(product1);
    }

    @Test
    public void createProduct_internal_server_error() throws Exception {
        ProductDto product1 = new ProductDto(null, "product", 1234L);
        Mockito.doThrow(ConstraintViolationException.class).when(productService).save(product1);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(product1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isInternalServerError());
        Mockito.verify(productService, Mockito.times(1)).save(product1);
    }

    @Test
    public void updateProduct_OK() throws Exception {
        ProductDto product1 = new ProductDto(1L, "product", 1234L);
        ProductDto productDto = new ProductDto(1L, "product", 1234L);
        Mockito.when(productService.findById(1L)).thenReturn(product1);
        Mockito.doNothing().when(productService).update(productDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(product1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        Mockito.verify(productService, Mockito.times(1)).update(productDto);
    }

    @Test
    public void updateProduct_Bad_request() throws Exception {
        ProductDto product1 = new ProductDto(null, "product", 1234L);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(product1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateProduct_record_not_found() throws Exception {
        ProductDto product1 = new ProductDto(1L, "product", 1234L);
        Mockito.doThrow(RecordNotFoundException.class).when(productService).update(product1);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(product1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNoContent());
        Mockito.verify(productService, Mockito.times(1)).update(product1);
    }

    @Test
    public void updateProduct_intern_server_error() throws Exception {
        ProductDto product1 = new ProductDto(1L, "product", 1234L);
        Mockito.doThrow(JpaSystemException.class).when(productService).update(product1);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(product1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isInternalServerError());
        Mockito.verify(productService, Mockito.times(1)).update(product1);
    }

    private String toJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
