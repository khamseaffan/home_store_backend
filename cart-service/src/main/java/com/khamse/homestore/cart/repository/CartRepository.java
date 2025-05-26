package com.khamse.homestore.cart.repository;

import com.khamse.homestore.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> findByUserId(UUID userId);
    Optional<Cart> findByUserIdAndStoreId(UUID userId, String storeId);
}
