package com.khamse.homestore.product.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.khamse.homestore.common.dto.StoreProductRequestDTO;
import com.khamse.homestore.common.dto.StoreProductResponseDTO;
import com.khamse.homestore.product.service.StoreProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("api/v1/products/store")
@Tag(name = "StoreProduct", description = "Store Product catalog management APIs - handles custom input product")
public class StoreProductController {
    
    private final StoreProductService storeProductService;

    public StoreProductController(StoreProductService storeProductService) {
        this.storeProductService = storeProductService;
    }

    @GetMapping("/")
    @Operation(summary = "Get all store products", description = "Get all store products from the database")
    public ResponseEntity<List<StoreProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(storeProductService.getAllProducts());
    }

    @GetMapping("/{product_id}")
    @Operation(summary = "Get store product by id", description = "Get store product by id from the database")
    public ResponseEntity<StoreProductResponseDTO> getProductById(
        @PathVariable UUID product_id) {
        StoreProductResponseDTO product = storeProductService.getProductById(product_id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{product_id}")
    @Operation(summary = "Delete a store product", description = "Delete a store product from the database")
    public ResponseEntity<Void> deleteProduct(
        @PathVariable UUID product_id) {
        boolean isDeleted = storeProductService.deleteProduct(product_id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = {"/"}, consumes = {"multipart/form-data"})
    @Operation(summary = "Create Store Product", description = "Create a store-specific product")
    public ResponseEntity<StoreProductResponseDTO> createProduct(
            @RequestParam("storeId") UUID storeId,
            @RequestParam(value = "globalProductId", required = false) UUID globalProductId,
            @RequestParam("productName") String productName,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("price") double price,
            @RequestParam("quantity") int quantity,
            @RequestParam(value = "images", required = false) List<MultipartFile> images
    ) throws IOException {

        StoreProductRequestDTO productDTO = new StoreProductRequestDTO(storeId, globalProductId, productName, description, category, price, quantity);
        StoreProductResponseDTO savedProduct = storeProductService.saveProduct(null, productDTO, images);

        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("/search/{search_query}") 
    @Operation(summary = "Search store products", description = "Get store products matching the search query")
    public ResponseEntity<List<StoreProductResponseDTO>> searchProducts(
        @PathVariable String search_query) {
        return ResponseEntity.ok(storeProductService.searchProducts(search_query));
    }

    @PatchMapping("/{product_id}/inventory")
    @Operation(summary = "Update store product inventory", description = "Updates available, reserved, or sold quantity for a store product")
    public ResponseEntity<String> updateInventory(
            @PathVariable UUID product_id,
            @RequestParam int quantity,
            @RequestParam String type) {

        int availableQuantity = storeProductService.updateInventory(product_id, quantity, type);
        return ResponseEntity.ok("Inventory updated. New available quantity: " + availableQuantity);
    }

}
