package com.khamse.homestore.product.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.khamse.homestore.common.dto.GlobalProductRequestDTO;
import com.khamse.homestore.common.dto.GlobalProductResponseDTO;
import com.khamse.homestore.product.service.GlobalProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping("api/v1/products/global")
@Tag(name = "GlobalProducts", description = "Global Product catalog management APIs - It will help in easy onboarding")
public class GlobalProductController {
    
    private final GlobalProductService globalProductService;

    // @Autowired
    public GlobalProductController(GlobalProductService globalProductService) {
        this.globalProductService = globalProductService;
    }

    @GetMapping("/products")
    @Operation(summary = "Get all products", description = "Get all products from the database")
    public ResponseEntity<List<GlobalProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(globalProductService.getAllProducts());
    }

    @GetMapping("/products/{id}")
    @Operation(summary = "Get product by id", description = "Get product by id from the database")
    public ResponseEntity<GlobalProductResponseDTO> getProductById(@PathVariable UUID id) {
        return ResponseEntity.ok(globalProductService.getProductById(id));
    }

    @DeleteMapping("/products/{id}")
    @Operation(summary = "Delete a product", description = "Delete a product from the database")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        boolean isDeleted = globalProductService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = {"/", "/{id}"}, consumes = {"multipart/form-data"})
    @Operation(summary = "Create or Update Product", description = "Create a new product if no ID is provided. Update an existing product if an ID is provided.")
    public ResponseEntity<GlobalProductResponseDTO> saveProduct(
        @PathVariable(value = "id", required = false) UUID productId, 
        @RequestParam("productName") String productName,
        @RequestParam("description") String description,
        @RequestParam("category") String category,
        @RequestParam(value = "images", required = false) List<MultipartFile> images
    ) throws IOException {
        
        GlobalProductRequestDTO productDTO = new GlobalProductRequestDTO(productName, description, category);
        GlobalProductResponseDTO savedProduct = globalProductService.saveProduct(productId, productDTO, images);
        return ResponseEntity.ok(savedProduct);
    }


    @GetMapping("/products/search/{search_query}") 
    @Operation(summary = "Get product/s matching Search query", 
              description = "Get products matching the search query")
    public ResponseEntity<List<GlobalProductResponseDTO>> searchProducts(
            @PathVariable String search_query) {
        return ResponseEntity.ok(globalProductService.searchProducts(search_query));
     
    }

}
