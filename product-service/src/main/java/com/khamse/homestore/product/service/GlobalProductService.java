package com.khamse.homestore.product.service;

import com.khamse.homestore.product.util.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.khamse.homestore.common.dto.GlobalProductRequestDTO;
import com.khamse.homestore.common.dto.GlobalProductResponseDTO;
import com.khamse.homestore.common.model.GlobalProduct;
import com.khamse.homestore.product.repository.GlobalProductRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GlobalProductService {

    private final GlobalProductRepository globalProductRepository;

    private final StorageService storageService;

    // @Autowired
    public GlobalProductService(GlobalProductRepository globalProductRepository, StorageService storageService) {
        this.globalProductRepository = globalProductRepository;
        this.storageService = storageService;
    }

    public List<GlobalProductResponseDTO> getAllProducts() {
        List<GlobalProductResponseDTO> productList = new ArrayList<>();
        for (GlobalProduct product : globalProductRepository.findAll()) {
            productList.add(convertToResponseDTO(product));
        }

        return productList;
    }

    public List<GlobalProductResponseDTO> searchProducts(String search_query) {
        List<GlobalProductResponseDTO> searchedProduct = new ArrayList<>();
        for (GlobalProduct product : globalProductRepository.findByProductName(search_query)) {
            searchedProduct.add(convertToResponseDTO(product));
        }

        return searchedProduct;
    }

    public GlobalProductResponseDTO getProductById(UUID id) {
        return convertToResponseDTO(globalProductRepository.findById(id).orElse(null));
    }

    @Transactional
    public GlobalProductResponseDTO saveProduct(UUID productId, GlobalProductRequestDTO globalProductDTO, List<MultipartFile> images) throws IOException {
        GlobalProduct globalProduct;

        if (productId != null) {
            globalProduct = globalProductRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

            globalProduct.setProductName(globalProductDTO.getProductName());
            globalProduct.setDescription(globalProductDTO.getDescription());
            globalProduct.setCategory(globalProductDTO.getCategory());

            if (images != null && !images.isEmpty()) {
                List<String> imageUrls = storageService.uploadFiles(images);
                globalProduct.getImageURList().clear();
                globalProduct.getImageURList().addAll(imageUrls);
            }

        } else {
            // New entity → must save
            globalProduct = new GlobalProduct(
                    globalProductDTO.getProductName(),
                    globalProductDTO.getDescription(),
                    globalProductDTO.getCategory()
            );

            if (images != null && !images.isEmpty()) {
                List<String> imageUrls = storageService.uploadFiles(images);
                globalProduct.getImageURList().addAll(imageUrls);
            }

            globalProduct = globalProductRepository.save(globalProduct);
        }

        return convertToResponseDTO(globalProduct);
    }

    @Transactional
    public boolean deleteProduct(UUID id) {
        if (globalProductRepository.existsById(id)) {
            
            storageService.deleteImages(globalProductRepository.findById(id).get().getImageURList());
            
            globalProductRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public GlobalProductResponseDTO convertToResponseDTO(GlobalProduct globalProduct) {
        if (globalProduct == null) {
            return null;
        }
        return new GlobalProductResponseDTO(
                globalProduct.getId(), globalProduct.getProductName(), globalProduct.getDescription(),
                globalProduct.getCategory(), globalProduct.getImageURList()
        );
    }
}
