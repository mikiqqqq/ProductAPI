package com.example.product_api.service.impl;

import com.example.product_api.model.OrderItem;
import com.example.product_api.repository.OrderItemRepo;
import com.example.product_api.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepo orderItemRepo;

    @Override
    @Transactional
    public OrderItem save(OrderItem orderItem){
        if(orderItemRepo.existsById(orderItem.getId())){
            int updatedQuantity = orderItemRepo.getOrderItemQuantity(orderItem.getId()) + orderItem.getQuantity();
            orderItemRepo.updateOrderItemQuantity(orderItem.getId(), updatedQuantity);
            return orderItemRepo.findById(orderItem.getId()).orElse(null);
        } else {
            return orderItemRepo.save(orderItem);
        }
    }

    @Override
    @Transactional
    public OrderItem update(OrderItem orderItem) {
        orderItemRepo.updateOrderItemQuantity(orderItem.getId(), orderItem.getQuantity());
        return orderItemRepo.findById(orderItem.getId()).orElse(null);
    }

    @Override
    public List<OrderItem> findAllByOrderId(Integer orderId){
        return orderItemRepo.findAllByOrderId(orderId);
    }

    @Override
    @Transactional
    public void deleteAll(Integer orderId) {
        orderItemRepo.deleteByOrderId(orderId);
    }

    @Override
    @Transactional
    public void delete(Integer orderId, Integer itemId) {
        orderItemRepo.deleteByOrderIdAndItemId(orderId, itemId);
    }
}
