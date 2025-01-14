package com.khamse.home_store.controller;

import org.springframework.web.bind.annotation.RestController;

import com.khamse.home_store.model.Product;
import com.khamse.home_store.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/v1")
@Tag(name = "Products", description = "Product management APIs")
public class ProductController {
    
    private final ProductService productService;

    // @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    @Operation(summary = "Get all products", description = "Get all products from the database")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    @Operation(summary = "Get product by id", description = "Get product by id from the database")
    public Product getProductById(@RequestParam Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    @Operation(summary = "Add a new product", description = "Add a new product to the database")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/products/{id}")
    @Operation(summary = "Update a product", description = "Update a product in the database")
    public Product updateProduct(@RequestParam Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/products/{id}")
    @Operation(summary = "Delete a product", description = "Delete a product from the database")
    public void deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
    }
    
    
}
