package com.khamse.homestore.order.service;

import com.khamse.homestore.order.client.InventoryServiceClient;
import com.khamse.homestore.order.model.Order;
import com.khamse.homestore.order.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryServiceClient inventoryServiceClient;

    public OrderService(OrderRepository orderRepository, InventoryServiceClient inventoryServiceClient) {
        this.orderRepository = orderRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    @Transactional
    public Order createOrder(UUID userId, String storeId, List<UUID> productIds) {
        // Validate and reduce stock for each product
        for (UUID productId : productIds) {
            ResponseEntity<Void> response = inventoryServiceClient.reduceStock(storeId, productId.toString(), 1);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Failed to reduce stock for product: " + productId + " in store: " + storeId);
            }
        }

        // Create the order after successful stock reduction
        Order order = new Order();
        order.setUserId(userId);
        order.setStoreId(storeId);
        order.setProductIds(productIds);
        order.setCreatedAt(new Date());
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUserId(UUID userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> getOrdersByUserIdAndStoreId(UUID userId, String storeId) {
        return orderRepository.findByUserIdAndStoreId(userId, storeId);
    }

    public Optional<Order> getOrderById(UUID orderId) {
        return orderRepository.findById(orderId);
    }
}
