package com.khamse.homestore.cart.client;

import com.khamse.homestore.cart.dto.InventoryItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service")
public interface InventoryServiceClient {

    @GetMapping("/api/inventory/{storeId}/{productId}")
    ResponseEntity<InventoryItemDto> getInventoryItem(@PathVariable String storeId, @PathVariable String productId);

    @PostMapping("/api/inventory/{storeId}/{productId}/reduce-stock")
    ResponseEntity<Void> reduceStock(@PathVariable String storeId, 
                                     @PathVariable String productId, 
                                     @RequestParam int quantity);
}
