package com.khamse.home_store.service;

import org.springframework.web.bind.annotation.RestController;

import com.khamse.home_store.model.Product;
import com.khamse.home_store.repository.ProductRepository;

import java.util.List;


@RestController
public class ProductService {
    
    private final ProductRepository productRepository;

    // @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setQuantity(product.getQuantity());
            return productRepository.save(existingProduct);
        }).orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
}
