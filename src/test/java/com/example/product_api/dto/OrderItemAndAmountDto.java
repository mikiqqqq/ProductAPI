package com.example.product_api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderItemAndAmountDto {

    private Integer id;
    private Long quantity;
}
