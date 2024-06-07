package com.example.product_api.mapper;

import com.spring_boot.webshop_app.dto.OrderItemAndAmountDto;
import org.springframework.stereotype.Component;

@Component
public class OrderItemAndAmountDtoMapper {
    public OrderItemAndAmountDto map(Integer itemId, Long itemAmount) {

        if (itemId == null || itemAmount == null) {
            return null;
        }

        return OrderItemAndAmountDto.builder()
                .id(itemId)
                .quantity(itemAmount)
                .build();
    }
}
