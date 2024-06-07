package com.example.product_api.service;

import com.example.product_api.model.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItem save (OrderItem orderItem);
    OrderItem update (OrderItem orderItem);
    List<OrderItem> findAllByOrderId(Integer orderId);
    void deleteAll(Integer orderId);
    void delete(Integer orderId, Integer itemId);
}
