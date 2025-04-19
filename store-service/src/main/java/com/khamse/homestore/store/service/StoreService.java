package com.khamse.homestore.store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.khamse.homestore.common.dto.StoreRequestDTO;
import com.khamse.homestore.common.dto.StoreResponseDTO;
import com.khamse.homestore.common.model.Store;
import com.khamse.homestore.store.repository.StoreRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StoreService {

    
    private final StoreRepository storeRepository;

    // @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<StoreResponseDTO> getAllStores() {
        List<StoreResponseDTO> storeList = new ArrayList<>();
        for( Store store: storeRepository.findAll() ){
            storeList.add(new StoreResponseDTO(
                store.getId(), store.getName(), store.getStreetAddress(), store.getCity(), store.getState(),
                store.getZipcode(), store.getPhone(), store.getEmail(), store.getStoreType()
            ));
        }

        return storeList;
    }

    public StoreResponseDTO getStoreById(UUID storeId) {

        return storeRepository.findById(storeId)
        .map(fetchedStore -> {
                return convertToResponseDTO(fetchedStore);
            })
        .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + storeId));
    }

    public StoreResponseDTO addOrUpdateStore(UUID storeId, StoreRequestDTO storeDTO) {
        if (storeId != null) {
            return storeRepository.findById(storeId)
            .map(existingStore -> {
                existingStore.setName(storeDTO.getName());
                existingStore.setStreetAddress(storeDTO.getStreetAddress());
                existingStore.setCity(storeDTO.getCity());
                existingStore.setState(storeDTO.getState());
                existingStore.setZipcode(storeDTO.getZipcode());
                existingStore.setEmail(storeDTO.getEmail());
                existingStore.setPhone(storeDTO.getPhone());
                existingStore.setStoreType(storeDTO.getStoreType());
    
                return convertToResponseDTO(storeRepository.save(existingStore));
            })
            .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + storeId));
        }

        Store newStore = new Store(
                storeDTO.getName(), storeDTO.getStreetAddress(), storeDTO.getCity(),
                storeDTO.getState(), storeDTO.getZipcode(), storeDTO.getPhone(),
                storeDTO.getEmail(), storeDTO.getPasswordString(), storeDTO.getStoreType()
        );
        
        return convertToResponseDTO(storeRepository.save(newStore));
    }
    


    public void deleteStore(UUID id) {
        if (storeRepository.existsById(id)) {
            storeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Store not found");
        }
    }

    
    public StoreResponseDTO convertToResponseDTO(Store store){
        return new StoreResponseDTO(
                    store.getId(), store.getName(), store.getStreetAddress(), store.getCity(), store.getState(),
                    store.getZipcode(), store.getPhone(), store.getEmail(), store.getStoreType()
                );
    }
}

