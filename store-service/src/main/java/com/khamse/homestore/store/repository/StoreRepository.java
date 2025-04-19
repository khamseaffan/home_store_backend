package com.khamse.homestore.store.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khamse.homestore.common.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, UUID> {
    // Additional custom queries can be added here
}