package com.example.product_api.controller;

import com.example.product_api.model.OrderItem;
import com.example.product_api.service.OrderItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping(value = "/api/order-item")
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;

    @PostMapping(value="/add")
    ResponseEntity<OrderItem> add(@Valid @RequestBody OrderItem orderItem) {

        return new ResponseEntity<>(
                orderItemService.save(orderItem),
                HttpStatus.CREATED
        );
    }

    @PutMapping(value="/update")
    ResponseEntity<OrderItem> update(@Valid @RequestBody OrderItem orderItem) {

        return new ResponseEntity<>(
                orderItemService.update(orderItem),
                HttpStatus.CREATED
        );
    }

    @GetMapping(value="/find-all/{orderId}")
    ResponseEntity<List<OrderItem>> findAll(@PathVariable Integer orderId) {

        return ResponseEntity.ok().body(orderItemService.findAllByOrderId(orderId));
    }

    @DeleteMapping(value="/delete/orderId={orderId}&itemId={itemId}")
    ResponseEntity<String> delete(@PathVariable Integer orderId, @PathVariable Integer itemId) {
        orderItemService.delete(orderId, itemId);

        return ResponseEntity.ok().body("deleted");
    }

    @DeleteMapping(value="/delete-all/{orderId}")
    ResponseEntity<String> deletwAll(@PathVariable Integer orderId) {
        orderItemService.deleteAll(orderId);

        return ResponseEntity.ok().body("deleted");
    }
}
