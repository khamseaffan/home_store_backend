package com.khamse.home_store.model;

import javax.annotation.processing.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;



@Entity
@Table(name = "bills")
@Generated(value = "com.khamse.home_store.model.Bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bill_id")
    private List<BillItem> items;

    @Column(nullable = false)
    private double subTotal;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    // Constructors
    public Bill() {
    }

    public Bill(List<BillItem> items, double subTotal, Store store) {
        this.items = items;
        this.subTotal = subTotal;
        this.store = store;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BillItem> getItems() {
        return items;
    }

    public void setItems(List<BillItem> items) {
        this.items = items;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
    
}
