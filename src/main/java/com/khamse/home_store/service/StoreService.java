package com.khamse.home_store.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khamse.home_store.model.Store;
import com.khamse.home_store.repository.StoreRepository;

@Service
public class StoreService {
    
    private final StoreRepository storeRepository;

    // @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Store getStoreById(Long id) {

        return storeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Store not found"));
    }

    public Store addStore(Store store) {
        return storeRepository.save(store);
    }

    public Store updateStore(Long id, Store store) {
        return storeRepository.findById(id).map(currentStoreDetails -> {
            currentStoreDetails.setName(store.getName());
            currentStoreDetails.setAddress(store.getAddress());
            currentStoreDetails.setEmail(store.getEmail());
            currentStoreDetails.setPhone(store.getPhone());
            currentStoreDetails.setStoreType(store.getStoreType());
            return storeRepository.save(currentStoreDetails);
        }).orElseThrow(() -> new RuntimeException("Store not found"));
    }

    public void deleteStore(Long id) {
        if (storeRepository.existsById(id)) {
            storeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Store not found");
        }
    }

}

