package com.khamse.homestore.common.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "store_product_id", referencedColumnName = "id")
    private StoreProduct storeProduct;

    @Column(nullable = false)
    private int availableQuantity;

    @Column(nullable = false)
    private int reservedQuantity; // For pending orders

    @Column(nullable = false)
    private int soldQuantity;

    @Column(nullable = true)
    private LocalDateTime lastUpdated;

    public Inventory(int quantity){
        this.availableQuantity = quantity;
        this.reservedQuantity = 0;
        this.soldQuantity = 0;
        this.lastUpdated = LocalDateTime.now();
    }

    public int updateAvailableQuantity(int new_stock_quantity){
        this.availableQuantity += new_stock_quantity;
        return availableQuantity;
    }
    public int updateReserveQuantity(int new_reservation){
        this.reservedQuantity += new_reservation;
        return  reservedQuantity;
    }

    public void updateSoldQuantity(int soldQuantity){
        this.soldQuantity += soldQuantity;
    }
}
