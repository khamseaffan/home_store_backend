package com.khamse.homestore.product.service;

import com.khamse.homestore.common.model.GlobalProduct;
import com.khamse.homestore.common.model.Inventory;
import com.khamse.homestore.product.repository.GlobalProductRepository;
import com.khamse.homestore.product.util.FirebaseStorageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.khamse.homestore.common.dto.StoreProductRequestDTO;
import com.khamse.homestore.common.dto.StoreProductResponseDTO;
import com.khamse.homestore.common.model.StoreProduct;
import com.khamse.homestore.product.repository.StoreProductRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StoreProductService {

    private final StoreProductRepository storeProductRepository;
    private final GlobalProductRepository globalProductRepository;
    private final FirebaseStorageService firebaseStorageService;

    public StoreProductService(
        StoreProductRepository productRepository,
        GlobalProductRepository globalProductRepository,
        FirebaseStorageService firebaseStorageService) {
        this.storeProductRepository = productRepository;
        this.firebaseStorageService = firebaseStorageService;
        this.globalProductRepository = globalProductRepository;
    }

    public List<StoreProductResponseDTO> getAllProducts() {
        List<StoreProductResponseDTO> productList = new ArrayList<>();
        for (StoreProduct product : storeProductRepository.findAll()) {
            productList.add(convertToResponseDTO(product));
        }
        return productList;
    }

    public List<StoreProductResponseDTO> searchProducts(String search_query) {
        List<StoreProductResponseDTO> searchedProduct = new ArrayList<>();
        for (StoreProduct product : storeProductRepository.findByProductName(search_query)) {
            searchedProduct.add(convertToResponseDTO(product));
        }
        return searchedProduct;
    }

    public StoreProductResponseDTO getProductById(UUID id) {
        return storeProductRepository.findById(id)
               .map(this::convertToResponseDTO)
               .orElse(null);
    }

    @Transactional
    public StoreProductResponseDTO saveProduct(UUID productId, StoreProductRequestDTO requestDTO, List<MultipartFile> images) throws IOException {
        if (requestDTO.getGlobalProductId() != null) {
            GlobalProduct globalProduct = globalProductRepository.findById(requestDTO.getGlobalProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Global Product not found"));

            requestDTO.setProductName(globalProduct.getProductName());
            requestDTO.setDescription(globalProduct.getDescription());
            requestDTO.setCategory(globalProduct.getCategory());
        }

        StoreProduct storeProduct;

        if (productId != null) {
            storeProduct = storeProductRepository.findById(productId)
                    .orElseThrow(() -> new EntityNotFoundException("Store Product not found"));
        } else {
            storeProduct = new StoreProduct();
            storeProduct.setStoreId(requestDTO.getStoreID());
            storeProduct.setInventory(new Inventory(requestDTO.getQuantity()));
            storeProduct.getInventory().setStoreProduct(storeProduct);
        }

        storeProduct.setProductName(requestDTO.getProductName());
        storeProduct.setDescription(requestDTO.getDescription());
        storeProduct.setCategory(requestDTO.getCategory());
        storeProduct.setPrice(requestDTO.getPrice());
        storeProduct.setQuantity(requestDTO.getQuantity());

        if (images != null && !images.isEmpty()) {
            List<String> imageUrls = firebaseStorageService.uploadFiles(images);
            storeProduct.setImageURList(imageUrls);
        }

        storeProduct = storeProductRepository.save(storeProduct);

        return convertToResponseDTO(storeProduct);
    }

    @Transactional
    public boolean deleteProduct(UUID id) {
        if (storeProductRepository.existsById(id)) {
            firebaseStorageService.deleteImages(storeProductRepository.findById(id).get().getImageURList());
            storeProductRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public int updateInventory(UUID productId, int quantity, String type) {
        Optional<StoreProduct> optionalStoreProduct = storeProductRepository.findById(productId);

        if (optionalStoreProduct.isPresent()) {
            StoreProduct storeProduct = optionalStoreProduct.get();
            int availableQuantity;

            switch (type.toUpperCase()) {
                case "SOLD":
                    availableQuantity = storeProduct.updateAvailableQuantity(-quantity);
                    storeProduct.updateSoldQuantity(quantity);
                    break;
                case "RESERVE":
                    availableQuantity = storeProduct.updateAvailableQuantity(-quantity);
                    storeProduct.updateReserveQuantity(quantity);
                    break;
                case "UNRESERVE":
                    availableQuantity = storeProduct.updateAvailableQuantity(quantity);
                    storeProduct.updateReserveQuantity(-quantity);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid inventory update type: " + type);
            }
            return availableQuantity;
        } else {
            throw new EntityNotFoundException("Product not found for ID: " + productId);
        }
    }

    public StoreProductResponseDTO convertToResponseDTO(StoreProduct storeProduct) {
        if (storeProduct == null) {
            return null;
        }
        return new StoreProductResponseDTO(
                storeProduct.getId(),
                storeProduct.getStoreId(),
                storeProduct.getProductName(),
                storeProduct.getDescription(),
                storeProduct.getCategory(),
                storeProduct.getPrice(),
                storeProduct.getInventory().getAvailableQuantity(),
                storeProduct.getImageURList());
    }
}
