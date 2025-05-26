package com.khamse.homestore.cart.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String storeId; // Add storeId field

    @ElementCollection
    private List<UUID> productIds = new ArrayList<>();

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public String getStoreId() { return storeId; }
    public void setStoreId(String storeId) { this.storeId = storeId; }
    public List<UUID> getProductIds() { return productIds; }
    public void setProductIds(List<UUID> productIds) { this.productIds = productIds; }
}
