package com.example.product_api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Column(name = "PRODUCT_ID")
    private Integer itemId;
}
