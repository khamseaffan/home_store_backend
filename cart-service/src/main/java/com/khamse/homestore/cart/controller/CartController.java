package com.khamse.homestore.cart.controller;

import com.khamse.homestore.cart.model.Cart;
import com.khamse.homestore.cart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}/store/{storeId}")
    public ResponseEntity<Cart> getCart(@PathVariable UUID userId, @PathVariable String storeId) {
        return ResponseEntity.ok(cartService.getCartByUserIdAndStoreId(userId, storeId));
    }

    @PostMapping("/{userId}/store/{storeId}/add")
    public ResponseEntity<Cart> addProduct(@PathVariable UUID userId, 
                                           @PathVariable String storeId, 
                                           @RequestParam UUID productId) {
        try {
            return ResponseEntity.ok(cartService.addProduct(userId, storeId, productId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/{userId}/store/{storeId}/remove")
    public ResponseEntity<Cart> removeProduct(@PathVariable UUID userId, 
                                              @PathVariable String storeId, 
                                              @RequestParam UUID productId) {
        return ResponseEntity.ok(cartService.removeProduct(userId, storeId, productId));
    }

    @DeleteMapping("/{userId}/store/{storeId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable UUID userId, @PathVariable String storeId) {
        cartService.clearCart(userId, storeId);
        return ResponseEntity.ok().build();
    }
}
