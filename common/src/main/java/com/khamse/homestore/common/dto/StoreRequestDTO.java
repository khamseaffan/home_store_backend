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
public class StoreRequestDTO {

    @Schema(description = "Name of the store", example = "Taaza")
    private String name;

    @Schema(description = "Street address of the store", example = "123 Main St")
    private String streetAddress;

    @Schema(description = "City where the store is located", example = "New York")
    private String city;

    @Schema(description = "State where the store is located", example = "NY")
    private String state;

    @Schema(description = "Zipcode of the store location", example = "10001")
    private String zipcode;

    @Schema(description = "Store contact phone number", example = "+1 555-123-4567")
    private String phone;

    @Schema(description = "Store contact/login email address", example = "info@taaza.com")
    private String email;

    @Schema(description = "Store Password", example = "admin@432")
    private String passwordString;

    @Schema(description = "Type of store", example = "Grocery")
    private String storeType;
}