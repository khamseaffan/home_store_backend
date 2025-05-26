package com.khamse.homestore.order.repository;

import com.khamse.homestore.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByUserId(UUID userId);
    List<Order> findByUserIdAndStoreId(UUID userId, String storeId);
}
