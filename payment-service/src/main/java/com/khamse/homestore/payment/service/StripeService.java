package com.khamse.homestore.payment.service;

import com.khamse.homestore.payment.dto.ChargeRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Value("${stripe.api.key.secret}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Charge charge(ChargeRequest chargeRequest) throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken()); // Obtained with Stripe.js
        // Optionally, add customer email to the charge for Stripe's records
        // chargeParams.put("receipt_email", chargeRequest.getStripeEmail());

        Map<String, String> metadata = new HashMap<>();
        metadata.put("order_id", chargeRequest.getOrderId());
        // Add any other metadata you want to store with the charge
        chargeParams.put("metadata", metadata);

        return Charge.create(chargeParams);
    }
}
