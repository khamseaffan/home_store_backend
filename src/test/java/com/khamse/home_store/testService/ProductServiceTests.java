package com.khamse.home_store.testService;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.khamse.home_store.model.Product;
import com.khamse.home_store.repository.ProductRepository;
import com.khamse.home_store.service.ProductService;

@SpringBootTest
public class ProductServiceTests {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    public void testGetProductsById() { 
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(100.0);
        product.setQuantity(10);
        product.setCategory("Electrnic");
        product.setDescription("This is a laptop");

        
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product product1 = productService.getProductById(1L);

        assert(product1.getId() == 1L);
        assert(product1.getName().equals("Laptop"));
        assert(product1.getPrice() == 100.0);
        assert(product1.getQuantity() == 10);
        assert(product1.getCategory().equals("Electrnic"));
        assert(product1.getDescription().equals("This is a laptop"));
    }


    @Test
    public void testAddProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(100.0);
        product.setQuantity(10);
        product.setCategory("Electrnic");
        product.setDescription("This is a laptop");

        Mockito.when(productRepository.save(product)).thenReturn(product);

        Product product1 = productService.addProduct(product);

        assert(product1.getId() == 1L);
        assert(product1.getName().equals("Laptop"));
        assert(product1.getPrice() == 100.0);
        assert(product1.getQuantity() == 10);
        assert(product1.getCategory().equals("Electrnic"));
        assert(product1.getDescription().equals("This is a laptop"));
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(100.0);
        product.setQuantity(10);
        product.setCategory("Electrnic");
        product.setDescription("This is a laptop");

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");
        product1.setPrice(100.0);
        product1.setQuantity(10);
        product1.setCategory("Electrnic");
        product1.setDescription("This is a laptop");

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(product)).thenReturn(product);

        Product product2 = productService.updateProduct(1L, product1);

        assert(product2.getId() == 1L);
        assert(product2.getName().equals("Laptop"));
        assert(product2.getPrice() == 100.0);
        assert(product2.getQuantity() == 10);
        assert(product2.getCategory().equals("Electrnic"));
        assert(product2.getDescription().equals("This is a laptop"));
    }


    @Test
    public void testDeleteProduct() {
        productService.deleteProduct(1L);
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(1L);

        
    }

    @Test
    public void testGetAllProducts() {
        productService.getAllProducts();
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }
    
    
}
