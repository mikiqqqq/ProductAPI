package com.example.product_api.service.impl;

import com.example.product_api.model.OrderItem;
import com.example.product_api.repository.OrderItemRepo;
import com.example.product_api.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepo orderItemRepo;

    @Override
    @Transactional
    public OrderItem save(OrderItem orderItem){
        Optional<OrderItem> existsInDatabase = orderItemRepo.findByOrderIdAndItemId(orderItem.getOrderId(), orderItem.getItemId());
        if(existsInDatabase.isPresent()){
            int updatedQuantity = orderItemRepo.getOrderItemQuantity(existsInDatabase.get().getId()) + orderItem.getQuantity();
            orderItemRepo.updateOrderItemQuantity(existsInDatabase.get().getId(), updatedQuantity);
            return orderItemRepo.findById(existsInDatabase.get().getId()).orElse(null);
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
