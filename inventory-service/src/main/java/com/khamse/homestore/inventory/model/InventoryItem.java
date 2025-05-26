package com.khamse.homestore.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId; // Refers to the global product ID
    private String storeId;   // Refers to the store this inventory belongs to

    private Integer quantity;
    private BigDecimal price; // Store-specific price, if applicable

    private LocalDateTime lastStockUpdate;
    // Add other relevant fields like SKU, supplier info, etc. if needed
}
