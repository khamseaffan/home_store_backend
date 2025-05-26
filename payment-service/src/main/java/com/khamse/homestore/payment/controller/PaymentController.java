package com.khamse.homestore.payment.controller;

import com.khamse.homestore.payment.dto.ChargeRequest;
import com.khamse.homestore.payment.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final StripeService stripeService;

    @Autowired
    public PaymentController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/charge")
    public ResponseEntity<String> chargeCard(@RequestBody ChargeRequest chargeRequest) {
        try {
            Charge charge = stripeService.charge(chargeRequest);
            // You might want to return more charge details or a custom success message
            return ResponseEntity.ok("Payment successful! Charge ID: " + charge.getId());
        } catch (StripeException e) {
            // Handle Stripe-specific exceptions
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed: " + e.getMessage());
        }
    }

    // Optional: Endpoint to get your Stripe public key if needed by the frontend
    // and not hardcoded or fetched from another config source on the frontend.
    // @Value("${stripe.api.key.public}")
    // private String stripePublicKey;

    // @GetMapping("/stripe-public-key")
    // public ResponseEntity<String> getStripePublicKey() {
    //     return ResponseEntity.ok(stripePublicKey);
    // }
}
