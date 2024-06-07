package com.example.product_api.service;

import com.example.product_api.dto.ProductDto;
import com.example.product_api.model.Product;

import java.util.Optional;

public interface ProductService {

    ProductDto addProduct(ProductDto productDto);

    ProductDto removeProduct(Integer productId);

    ProductDto updateProduct(ProductDto productDto);

    Optional<Product> findProductById(Integer productId);
}
