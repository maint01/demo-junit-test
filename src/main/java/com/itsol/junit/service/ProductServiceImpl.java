package com.itsol.junit.service;

import com.itsol.junit.dto.ProductDto;
import com.itsol.junit.entities.Product;
import com.itsol.junit.exception.RecordNotFoundException;
import com.itsol.junit.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> findAll() {
        List<ProductDto> productDtos = productRepository.findAll()
                .stream()
                .map(x -> modelMapper.map(x, ProductDto.class))
                .collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public List<Product> findByProductName(String productName) {
        return productRepository.findFirstByProductName(productName);
    }

    @Override
    public ProductDto findById(Long productId) {
        return productRepository.findById(productId)
                .map(x->modelMapper.map(x, ProductDto.class))
                .orElse(null);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        productRepository.save(product);
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public void update(ProductDto productDto) {
        ProductDto productDB = findById(productDto.getId());
        if (productDB == null)
            throw new RecordNotFoundException();
        Product product = modelMapper.map(productDto, Product.class);
        productRepository.save(product);
    }

    @Override
    public void delete(ProductDto productDto) {
        productRepository.delete(modelMapper.map(productDto, Product.class));
    }

    @Override
    public void delete(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        product.ifPresent(productRepository::delete);
    }
}
