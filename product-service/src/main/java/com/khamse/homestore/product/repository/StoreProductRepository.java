package com.khamse.homestore.product.repository; // Updated package

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.khamse.homestore.common.model.StoreProduct; // Updated import


// @Repository
public interface StoreProductRepository extends JpaRepository<StoreProduct, UUID> {
    
    @Query("""
        SELECT p FROM StoreProduct p
        WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :search_query, '%'))
    """)
    List<StoreProduct> findByProductName(@Param("search_query") String search_query);

}
