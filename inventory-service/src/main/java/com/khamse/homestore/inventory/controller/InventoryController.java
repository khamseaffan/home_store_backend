package com.khamse.homestore.inventory.controller;

import com.khamse.homestore.inventory.model.InventoryItem;
import com.khamse.homestore.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{storeId}/{productId}")
    public ResponseEntity<InventoryItem> getInventoryItem(@PathVariable String storeId, @PathVariable String productId) {
        return inventoryService.getInventoryItem(storeId, productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<List<InventoryItem>> getInventoryForStore(@PathVariable String storeId) {
        List<InventoryItem> items = inventoryService.getInventoryForStore(storeId);
        if (items.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<InventoryItem> addOrUpdateInventoryItem(@RequestBody InventoryItem item) {
        InventoryItem savedItem = inventoryService.addOrUpdateInventoryItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    @PostMapping("/{storeId}/{productId}/reduce-stock")
    public ResponseEntity<Void> reduceStock(@PathVariable String storeId, 
                                            @PathVariable String productId, 
                                            @RequestParam int quantity) {
        boolean success = inventoryService.reduceStock(storeId, productId, quantity);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // Or bad request, depending on exact reason
        }
    }
    
    // Add other endpoints as needed
}
