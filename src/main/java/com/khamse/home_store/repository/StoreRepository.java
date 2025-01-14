package com.khamse.home_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khamse.home_store.model.Store;

// @Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}