package com.example.product_api.mapper;

import com.example.product_api.dto.ProductDto;
import com.example.product_api.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {

    public ProductDto toDto(Product product) {

        if (product == null) {
            return null;
        }

            return ProductDto.builder()
                    .id(product.getId())
                    .image(product.getImage())
                    .title(product.getTitle())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .quantity(product.getQuantity())
                    .brandId(product.getBrandId())
                    .categoryId(product.getCategoryId())
                    .productYearId(product.getProductYearId())
                    .build();
    }

    public Product toEntity(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        Product product = new Product();
        product.setId(productDto.getId());
        product.setImage(productDto.getImage());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setBrandId(productDto.getBrandId());
        product.setCategoryId(productDto.getCategoryId());
        product.setProductYearId(productDto.getProductYearId());
        return product;
    }
}
