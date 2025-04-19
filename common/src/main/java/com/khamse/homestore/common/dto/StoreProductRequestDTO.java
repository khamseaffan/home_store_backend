package com.khamse.homestore.common.dto;

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
public class StoreProductRequestDTO {

    @Schema(description = "Store UUID")
    private UUID storeID;

    @Schema(description = "Global Product UUID (if applicable)")
    private UUID globalProductId;

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
}