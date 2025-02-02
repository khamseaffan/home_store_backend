package com.khamse.home_store.testController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.khamse.home_store.controller.ProductController;
import com.khamse.home_store.model.Product;
import com.khamse.home_store.service.ProductService;

@WebMvcTest(ProductController.class) 
public class ProductContollerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper; // For JSON serialization

    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("High-end gaming laptop");
        product.setPrice(1000.0);
        product.setQuantity(10);
        product.setCategory("Electronics");

        Mockito.when(productService.getProductById(1L)).thenReturn(product);

        mockMvc.perform(get("/api/v1/products/{id}", 1L)) // Use correct path
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"))
                .andExpect(jsonPath("$.price").value(1000.0))
                .andExpect(jsonPath("$.description").value("High-end gaming laptop"))
                .andExpect(jsonPath("$.quantity").value(10))
                .andExpect(jsonPath("$.category").value("Electronics"));
    }

    @Test
    public void testGetAllProducts() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");
        product1.setDescription("High-end gaming laptop");
        product1.setPrice(1000.0);
        product1.setQuantity(10);
        product1.setCategory("Electronics");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Mobile");
        product2.setDescription("Smartphone with 128GB storage");
        product2.setPrice(500.0);
        product2.setQuantity(20);
        product2.setCategory("Electronics");

        Mockito.when(productService.getAllProducts()).thenReturn(List.of(product1, product2));

        mockMvc.perform(get("/api/v1/products")) // Use correct path
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Laptop"))
                .andExpect(jsonPath("$[0].price").value(1000.0))
                .andExpect(jsonPath("$[1].name").value("Mobile"))
                .andExpect(jsonPath("$[1].price").value(500.0));
    }

    @Test
    public void testAddProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("High-end gaming laptop");
        product.setPrice(1000.0);
        product.setQuantity(10);
        product.setCategory("Electronics");

        Mockito.when(productService.addProduct(Mockito.any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/api/v1/products") // Use POST for adding a product
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"))
                .andExpect(jsonPath("$.price").value(1000.0));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("High-end gaming laptop");
        product.setPrice(1000.0);
        product.setQuantity(10);
        product.setCategory("Electronics");

        Mockito.when(productService.updateProduct(Mockito.eq(1L), Mockito.any(Product.class))).thenReturn(product);

        mockMvc.perform(put("/api/v1/products/{id}", 1L) // Use PUT for updating a product
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"))
                .andExpect(jsonPath("$.price").value(1000.0));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Mockito.doNothing().when(productService).deleteProduct(1L);

        mockMvc.perform(delete("/api/v1/products/{id}", 1L)) // Use DELETE for deleting a product
                .andExpect(status().isOk());

        Mockito.verify(productService, Mockito.times(1)).deleteProduct(1L);
    }
}