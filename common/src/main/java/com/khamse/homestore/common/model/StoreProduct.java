package com.khamse.homestore.common.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "store_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID storeId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String category;

    @ElementCollection
    @CollectionTable(name = "store_product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url", nullable = true)
    private List<String> imageURList = new ArrayList<>();

    @OneToOne(mappedBy = "storeProduct", cascade = CascadeType.ALL)
    private Inventory inventory;

    public StoreProduct(UUID storeId, String productName, String description, String category, double price, int quantity, List<String> imageURList)  {
        this.storeId = storeId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageURList = imageURList;
        this.inventory = new Inventory(quantity);
        this.inventory.setStoreProduct(this);
    }

    public int updateAvailableQuantity(int new_stock_quantity){
        return this.inventory.updateAvailableQuantity(new_stock_quantity);
    }
    public int updateReserveQuantity(int new_reservation){
        return this.inventory.updateReserveQuantity(new_reservation);
    }
    public void updateSoldQuantity(int soldQuantity){
        this.inventory.updateSoldQuantity(soldQuantity);
    }
}
