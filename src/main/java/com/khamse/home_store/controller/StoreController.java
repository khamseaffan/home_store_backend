package com.khamse.home_store.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khamse.home_store.model.Store;
import com.khamse.home_store.service.StoreService;


@RestController
@RequestMapping("api/v1")
public class StoreController {
    
    private final StoreService storeService;

    // @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/stores")
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/stores/{id}")
    public Store getStoreById(@RequestParam Long id) {
        return storeService.getStoreById(id);
    }

    @PostMapping("/stores")
    public Store addProduct(@RequestBody Store product) {
        return storeService.addStore(product);
    }

    @PutMapping("/stores/{id}")
    public Store updateProduct(@RequestParam Long id, @RequestBody Store product) {
        return storeService.updateStore(id, product);
    }

    @DeleteMapping("/stores/{id}")
    public void deleteProduct(@RequestParam Long id) {
        storeService.deleteStore(id);
    }
}
