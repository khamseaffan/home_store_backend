package com.khamse.homestore.inventory.repository;

import com.khamse.homestore.inventory.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    Optional<InventoryItem> findByStoreIdAndProductId(String storeId, String productId);

    List<InventoryItem> findByStoreId(String storeId);

    List<InventoryItem> findByProductId(String productId);
}
