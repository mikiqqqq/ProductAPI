package com.example.product_api.repository;

import com.example.product_api.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {
    void deleteByOrderId(Integer orderId);  // Add this method
    void deleteByOrderIdAndItemId(Integer orderId, Integer itemId);
    List<OrderItem> findAllByOrderId(Integer orderId);
    Optional<OrderItem> findByOrderIdAndItemId(Integer orderId, Integer itemId);

    // Custom method to update the quantity of an OrderItem
    @Modifying
    @Query("UPDATE OrderItem oi SET oi.quantity = :quantity WHERE oi.id = :id")
    void updateOrderItemQuantity(@Param("id") Integer id, @Param("quantity") Integer quantity);

    // Custom method to get the quantity of an OrderItem
    @Query("SELECT oi.quantity FROM OrderItem oi WHERE oi.id = :id")
    Integer getOrderItemQuantity(@Param("id") Integer id);
}
