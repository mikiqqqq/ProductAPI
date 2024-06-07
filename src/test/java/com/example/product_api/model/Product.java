package com.example.product_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Lob
    @Column(name = "IMAGE")
    private byte[] image;

    @Column(name = "TITLE", length = 41)
    private String title;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "BRAND_ID")
    private Integer brandId;

    @Column(name = "CATEGORY_ID")
    private Integer categoryId;

    @Column(name = "PRODUCT_YEAR_ID")
    private Integer productYearId;
}