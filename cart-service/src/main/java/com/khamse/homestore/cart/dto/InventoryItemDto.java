package com.khamse.homestore.cart.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InventoryItemDto {
    private Long id;
    private String productId;
    private String storeId;
    private Integer quantity;
    private BigDecimal price;
    private LocalDateTime lastStockUpdate;

    // Default constructor
    public InventoryItemDto() {}

    // Constructor with all fields
    public InventoryItemDto(Long id, String productId, String storeId, Integer quantity, BigDecimal price, LocalDateTime lastStockUpdate) {
        this.id = id;
        this.productId = productId;
        this.storeId = storeId;
        this.quantity = quantity;
        this.price = price;
        this.lastStockUpdate = lastStockUpdate;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getStoreId() { return storeId; }
    public void setStoreId(String storeId) { this.storeId = storeId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public LocalDateTime getLastStockUpdate() { return lastStockUpdate; }
    public void setLastStockUpdate(LocalDateTime lastStockUpdate) { this.lastStockUpdate = lastStockUpdate; }
}
