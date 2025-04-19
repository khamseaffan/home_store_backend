package com.khamse.homestore.product.repository; // Updated package

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.khamse.homestore.common.model.GlobalProduct; // Updated import


// @Repository
public interface GlobalProductRepository extends JpaRepository<GlobalProduct, UUID> {
    
    @Query("SELECT p FROM GlobalProduct p WHERE LOWER(p.productName) LIKE LOWER('%' || :search_query || '%')")
    public List<GlobalProduct> findByProductName(@Param("search_query") String search_query);
}
