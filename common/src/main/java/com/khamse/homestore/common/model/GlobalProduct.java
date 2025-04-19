package com.khamse.homestore.common.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "global_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GlobalProduct {

    @Id
    @GeneratedValue(strategy =GenerationType.UUID)
    private UUID id;

    @Version
    private int version;


    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    @ElementCollection // Enables storage of lists in a separate table
    @CollectionTable(name = "global_product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url", nullable = true)
    private List<String> imageURList = new ArrayList<>();



    public GlobalProduct(String productName, String description, String category) {
        this.productName = productName;
        this.description = description;
        this.category = category;
    }




    // public void setImageURLList(List<String> imageURList) {
    //     this.imageURList.clear(); // Clear old values before updating
    //     if (imageURList != null) {
    //         this.imageURList.addAll(imageURList);
    //     }
    // }  
}