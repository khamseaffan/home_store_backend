package com.khamse.homestore.cart.service;

import com.khamse.homestore.cart.client.InventoryServiceClient;
import com.khamse.homestore.cart.dto.InventoryItemDto;
import com.khamse.homestore.cart.model.Cart;
import com.khamse.homestore.cart.repository.CartRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final InventoryServiceClient inventoryServiceClient;

    public CartService(CartRepository cartRepository, InventoryServiceClient inventoryServiceClient) {
        this.cartRepository = cartRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    public Cart getCartByUserIdAndStoreId(UUID userId, String storeId) {
        return cartRepository.findByUserIdAndStoreId(userId, storeId).orElseGet(() -> {
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setStoreId(storeId);
            return cartRepository.save(cart);
        });
    }

    public Cart addProduct(UUID userId, String storeId, UUID productId) {
        // Check inventory availability first
        ResponseEntity<InventoryItemDto> inventoryResponse = inventoryServiceClient.getInventoryItem(storeId, productId.toString());
        
        if (inventoryResponse.getStatusCode().is2xxSuccessful() && inventoryResponse.getBody() != null) {
            InventoryItemDto inventoryItem = inventoryResponse.getBody();
            if (inventoryItem.getQuantity() > 0) {
                Cart cart = getCartByUserIdAndStoreId(userId, storeId);
                cart.getProductIds().add(productId);
                return cartRepository.save(cart);
            } else {
                throw new RuntimeException("Product is out of stock in store: " + storeId);
            }
        } else {
            throw new RuntimeException("Product not found in store inventory: " + storeId);
        }
    }

    public Cart removeProduct(UUID userId, String storeId, UUID productId) {
        Cart cart = getCartByUserIdAndStoreId(userId, storeId);
        cart.getProductIds().remove(productId);
        return cartRepository.save(cart);
    }

    public void clearCart(UUID userId, String storeId) {
        Cart cart = getCartByUserIdAndStoreId(userId, storeId);
        cart.getProductIds().clear();
        cartRepository.save(cart);
    }
}
