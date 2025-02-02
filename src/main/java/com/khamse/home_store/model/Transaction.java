package com.khamse.home_store.model;

import javax.annotation.processing.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "transaction")
@Generated(value = "com.khamse.home_store.model.Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int total;

    @Column(nullable = false)
    private java.time.LocalDateTime dateTime;

    public Transaction(Bill bill, User user, int total) {
            this.bill = bill;
        this.user = user;
        this.total = total; 

        this.dateTime = java.time.LocalDateTime.now();
    }

    public java.time.LocalDateTime getDateTime() {
        return dateTime;
    }   
    
    public Bill getbill() {
        return bill;
    }

    public User getuser() {
        return user;
    }

    public int getTotal() {
        return total;
    }

    public void setDateTime(java.time.LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setbill(Bill bill) {
        this.bill = bill;
    }

    public void setuser(User user) {
        this.user = user;
    }

    public void setTotal(int total) {
        this.total = total;
    }




    
}
