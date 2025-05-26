package com.khamse.homestore.order.controller;

import com.khamse.homestore.order.model.Order;
import com.khamse.homestore.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/user/{userId}/store/{storeId}")
    public ResponseEntity<Order> createOrder(@PathVariable UUID userId, 
                                             @PathVariable String storeId, 
                                             @RequestBody List<UUID> productIds) {
        try {
            return ResponseEntity.ok(orderService.createOrder(userId, storeId, productIds));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    @GetMapping("/user/{userId}/store/{storeId}")
    public ResponseEntity<List<Order>> getOrdersByUserAndStore(@PathVariable UUID userId, @PathVariable String storeId) {
        return ResponseEntity.ok(orderService.getOrdersByUserIdAndStoreId(userId, storeId));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable UUID orderId) {
        return orderService.getOrderById(orderId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
