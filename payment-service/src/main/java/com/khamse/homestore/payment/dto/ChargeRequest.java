package com.khamse.homestore.payment.dto;

import lombok.Data;

@Data
public class ChargeRequest {
    private String description;
    private int amount; // Amount in cents
    private String currency;
    private String stripeEmail;
    private String stripeToken;
    private String orderId; // To associate the charge with an order in your system
}
