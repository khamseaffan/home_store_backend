package com.khamse.homestore.store.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khamse.homestore.common.dto.StoreRequestDTO;
import com.khamse.homestore.common.dto.StoreResponseDTO;
import com.khamse.homestore.store.service.StoreService;

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
    public List<StoreResponseDTO> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/stores/{id}")
    @Operation(summary = "Get store by id", description = "Get store by id from the database")
    public StoreResponseDTO getStoreById(@PathVariable UUID id) {
        return storeService.getStoreById(id);
    }

    @PostMapping(path = {"/stores", "/stores/{id}"} )
    @Operation(summary = "Add a new store", description = "Add a new store to the database")
    public StoreResponseDTO saveProduct(
        @PathVariable(value = "id", required = false) UUID storeId, 
        @RequestBody StoreRequestDTO storeDTO) {
        return storeService.addOrUpdateStore(storeId, storeDTO);
    }

    @DeleteMapping("/stores/{id}")
    @Operation(summary = "Delete a store", description = "Delete a store from the database")
    public void deleteProduct(@PathVariable UUID id) {
        storeService.deleteStore(id);
    }
}
