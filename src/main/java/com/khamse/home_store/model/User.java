package com.khamse.home_store.model;


import javax.annotation.processing.Generated;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@Generated(value = "com.khamse.home_store.model.User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = true)
    private String phoneNumber;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String street;

    @Column(nullable = true)
    private String city;

    @Column(nullable = true)
    private String zipcode;

    @Column(nullable = true)
    private String state;

    @Column(nullable = true)
    private String country;

    // Default Constructor
    public User() {
    }

    // Constructors
    public User(long l, String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, String string11, String string12) {
        this.id = l;
        this.firstName = string;
        this.lastName = string2;
        this.email = string3;
        this.password = string4;
        this.role = string5;
        this.phoneNumber = string6;
        this.address = string7;
        this.street = string8;
        this.city = string9;
        this.zipcode = string10;
        this.state = string11;
        this.country = string12;
        
    }

    public User(String firstName, String lastName, String email, String password, String role, String phoneNumber, String address, String street, String city, String zipcode, String state, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
        this.state = state;
        this.country = country;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}