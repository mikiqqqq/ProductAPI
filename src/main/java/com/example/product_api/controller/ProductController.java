package com.example.product_api.controller;

import com.example.product_api.mapper.ProductDtoMapper;
import com.example.product_api.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.product_api.service.ProductService;
import com.example.product_api.dto.ProductDto;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping(value = "/api/product")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDtoMapper productDtoMapper;

    @PostMapping(value = "/add")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
        ProductDto addedProduct = productService.addProduct(productDto);
        return ResponseEntity.ok(addedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> removeProduct(@PathVariable Integer id) {
        ProductDto removedProduct = productService.removeProduct(id);
        if (removedProduct != null) {
            return ResponseEntity.ok(removedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        ProductDto updatedProduct = productService.updateProduct(productDto);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Integer id) {
        Optional<Product> product = productService.findProductById(id);
        return product.map(value -> ResponseEntity.ok(productDtoMapper.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/fetch-all")
    ResponseEntity<List<ProductDto>> fetchAll() {
        return ResponseEntity
                .ok()
                .body(productService.fetchAll());
    }
}
