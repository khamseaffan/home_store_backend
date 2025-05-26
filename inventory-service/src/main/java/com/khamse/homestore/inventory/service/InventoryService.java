package com.khamse.homestore.inventory.service;

import com.khamse.homestore.inventory.model.InventoryItem;
import com.khamse.homestore.inventory.repository.InventoryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryItemRepository inventoryItemRepository;

    @Transactional(readOnly = true)
    public Optional<InventoryItem> getInventoryItem(String storeId, String productId) {
        return inventoryItemRepository.findByStoreIdAndProductId(storeId, productId);
    }

    @Transactional(readOnly = true)
    public List<InventoryItem> getInventoryForStore(String storeId) {
        return inventoryItemRepository.findByStoreId(storeId);
    }

    @Transactional
    public InventoryItem addOrUpdateInventoryItem(InventoryItem item) {
        item.setLastStockUpdate(LocalDateTime.now());
        // Potential for more complex logic: e.g., if item exists, update quantity, otherwise create new
        Optional<InventoryItem> existingItemOpt = inventoryItemRepository.findByStoreIdAndProductId(item.getStoreId(), item.getProductId());
        if (existingItemOpt.isPresent()) {
            InventoryItem existingItem = existingItemOpt.get();
            existingItem.setQuantity(item.getQuantity());
            existingItem.setPrice(item.getPrice());
            existingItem.setLastStockUpdate(LocalDateTime.now());
            return inventoryItemRepository.save(existingItem);
        } else {
            return inventoryItemRepository.save(item);
        }
    }

    @Transactional
    public boolean reduceStock(String storeId, String productId, int quantityToReduce) {
        Optional<InventoryItem> itemOpt = inventoryItemRepository.findByStoreIdAndProductId(storeId, productId);
        if (itemOpt.isPresent()) {
            InventoryItem item = itemOpt.get();
            if (item.getQuantity() >= quantityToReduce) {
                item.setQuantity(item.getQuantity() - quantityToReduce);
                item.setLastStockUpdate(LocalDateTime.now());
                inventoryItemRepository.save(item);
                return true;
            }
        }
        return false; // Stock not sufficient or item not found
    }

    // Add more methods as needed, e.g., increaseStock, deleteInventoryItem
}
