package com.khamse.homestore.common.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    @Email(message = "Invalid email format")
    private String email;

    @Column(nullable = false)
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = true)
    private String phoneNumber;

    @Column(nullable = true)
    private String streetAddress = "";

    @Column(nullable = true)
    private String city = "";

    @Column(nullable = true)
    private String zipcode = "";

    @Column(nullable = true)
    private String state = "";

    @Column(nullable = true)
    private String country = "";

    public User(String firstName, String lastName, String email, String password, UserRole role,
                String phoneNumber, String streetAddress, String city, String zipcode, String state, String country) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role != null ? role : UserRole.CUSTOMER;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress != null ? streetAddress : "";
        this.city = city != null ? city : "";
        this.zipcode = zipcode != null ? zipcode : "";
        this.state = state != null ? state : "";
        this.country = country != null ? country : "";
    }
}