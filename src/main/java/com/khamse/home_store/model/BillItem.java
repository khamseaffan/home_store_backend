package com.khamse.home_store.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bill_item")
@Generated(value = "com.khamse.home_store.model.BillItem")
public class BillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false) 
    private double price;

    @Column(nullable = false)
    private double totalCost;

    public BillItem() {
        
    }

    public BillItem(int quantity, double price, Product product) {
        this.quantity = quantity;
        this.product = product;
        this.price = price;
        this.totalCost = quantity * price;
    }

    // Getters and

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Product getproduct() {
        return product;
    }

    public double getTotalCost() {
        return totalCost;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setproduct(Product product) {
        this.product = product;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    
}
