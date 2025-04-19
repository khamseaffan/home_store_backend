package com.khamse.homestore.common.dto;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreProductResponseDTO {

    @Schema(description = "Unique Product id")
    private UUID id;

    @Schema(description = "Store UUID")
    private UUID storeId;

    @Schema(description = "Product name", example = "Laptop")
    private String productName;

    @Schema(description = "Product description", example = "A high-end gaming laptop")
    private String description;

    @Schema(description = "Product category", example = "Electronics")
    private String category;

    @Schema(description = "Product price", example = "3.99")
    private double price;
    
    @Schema(description = "Product quantity", example = "12")
    private int quantity;

    @Schema(description = "List of image URLs")
    private List<String> imageURLs;

    public StoreProductResponseDTO(UUID storeId, String productName, String description, String category, double price, int quantity, List<String> imageURLs) {
        this.storeId = storeId;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.imageURLs = imageURLs;
    }
}