package com.khamse.home_store.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khamse.home_store.model.Store;
import com.khamse.home_store.service.StoreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("api/v1")
@Tag(name = "Stores", description = "Store management APIs")
public class StoreController {
    
    private final StoreService storeService;

    // @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/stores")
    @Operation(summary = "Get all stores", description = "Get all stores from the database")
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/stores/{id}")
    @Operation(summary = "Get store by id", description = "Get store by id from the database")
    public Store getStoreById(@PathVariable Long id) {
        return storeService.getStoreById(id);
    }

    @PostMapping("/stores")
    @Operation(summary = "Add a new store", description = "Add a new store to the database")
    public Store addProduct(@RequestBody Store product) {
        return storeService.addStore(product);
    }

    @PutMapping("/stores/{id}")
    @Operation(summary = "Update a store", description = "Update a store in the database")
    public Store updateProduct(@PathVariable Long id, @RequestBody Store product) {
        return storeService.updateStore(id, product);
    }

    @DeleteMapping("/stores/{id}")
    @Operation(summary = "Delete a store", description = "Delete a store from the database")
    public void deleteProduct(@PathVariable Long id) {
        storeService.deleteStore(id);
    }
}
