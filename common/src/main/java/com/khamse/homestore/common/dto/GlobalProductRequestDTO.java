package com.khamse.homestore.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GlobalProductRequestDTO {

    @Schema(description = "Product name", example = "Laptop")
    private String productName;

    @Schema(description = "Product description", example = "A high-end gaming laptop")
    private String description;

    @Schema(description = "Product category", example = "Electronics")
    private String category;
}