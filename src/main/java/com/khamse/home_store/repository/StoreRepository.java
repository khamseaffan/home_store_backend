package com.khamse.home_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khamse.home_store.model.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}