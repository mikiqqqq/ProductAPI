package com.example.product_api.service.impl;

import com.example.product_api.dto.ProductDto;
import com.example.product_api.mapper.ProductDtoMapper;
import com.example.product_api.model.Product;
import com.example.product_api.repository.ProductRepo;
import com.example.product_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductDtoMapper productDtoMapper;

    @Override
    public Optional<Product> findProductById(Integer productId){
        return productRepo.findById(productId);
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = productDtoMapper.toEntity(productDto);
        productRepo.save(product);
        return productDto;
    }

    @Override
    public ProductDto removeProduct(Integer productId) {
        Optional<Product> product = productRepo.findById(productId);
        if (product.isPresent()) {
            productRepo.deleteById(productId);
        }
        return null;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        if (productRepo.existsById(productDto.getId())) {
            Product product = productDtoMapper.toEntity(productDto);
            productRepo.save(product);
        }
        return productDto;
    }

    @Override
    public List<ProductDto> fetchAll(){
        return productRepo.findAll()
                .stream()
                .map(item -> productDtoMapper.toDto(item))
                .collect(Collectors.toList());
    }
}
