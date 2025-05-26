package com.khamse.homestore.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("product-service", r -> r.path("/api/v1/products/**")
                .uri("lb://product-service"))
            .route("store-service", r -> r.path("/api/v1/stores/**")
                .uri("lb://store-service"))
            .route("user-service", r -> r.path("/api/v1/users/**")
                .uri("lb://user-service"))
            .build();
    }
}
