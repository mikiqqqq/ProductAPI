package com.example.product_api.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDto {

    private Integer id;
    private byte[] image;
    private String title;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Integer brandId;
    private Integer categoryId;
    private Integer productYearId;
}