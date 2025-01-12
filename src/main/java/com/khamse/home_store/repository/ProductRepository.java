package com.khamse.home_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khamse.home_store.model.Product;


// @Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
