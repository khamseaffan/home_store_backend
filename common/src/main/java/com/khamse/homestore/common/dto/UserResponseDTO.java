package com.khamse.homestore.common.dto;

import java.util.UUID;

import com.khamse.homestore.common.model.UserRole;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    @Schema(description = "Unique Id of the user")
    private UUID id;

    @Schema(description = "First name of the user", example = "John")
    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @Schema(description = "Last name of the user", example = "Doe")
    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @Schema(description = "Email address of the user", example = "johndoe@example.com")
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Schema(description = "Phone number (optional)", example = "+15551234567")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    private String phoneNumber;

    @Schema(description = "StreetAddress (optional)", example = "123 Main St")
    private String streetAddress;

    @Schema(description = "City (optional)", example = "New York")
    private String city;

    @Schema(description = "State (optional)", example = "NY")
    private String state;

    @Schema(description = "Zip code (optional)", example = "10001")
    private String zipcode;

    @Schema(description = "Country (optional)", example = "USA")
    private String country;

    @Schema(description = "Share type of user (Default=CUSTOMER)", example = "STORE_OWNER")
    private UserRole userRole;

    public UserResponseDTO(UUID userId, String firstName, String lastName, String email, 
                         UserRole role, String phoneNumber) {
        this.id = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userRole = role;
        this.phoneNumber = phoneNumber;
    }

    public UserResponseDTO(UUID userId, String firstName, String lastName, String email, UserRole role, 
                String phoneNumber, String zipcode, String country) {
        this.id = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userRole = role;
        this.phoneNumber = phoneNumber;
        this.zipcode = zipcode;
        this.country = country;
    }

    public UserResponseDTO(UUID userId, String firstName, String lastName, String email, UserRole role, 
                   String phoneNumber, String streetAddress, String city, String state, String zipcode, String country) {
        this.id = userId;            
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userRole = role;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
    }
}