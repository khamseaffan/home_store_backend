# Microservices Overview (SERVICES.md)

This document provides a high-level overview of each microservice in the Home Store backend. It describes the responsibilities, main endpoints, and current status for each service.

---

## Core Infrastructure Services

### config-service
- **Purpose:** Centralized configuration management for all microservices (Spring Cloud Config). Reads configuration files (e.g., `product-service-dev.yml`) from the `HomeStore_config/` directory (located at the project root).
- **Port:** `8888`
- **Main Endpoints:**
  - `http://localhost:8888/{service-name}/{profile}` (serves configuration properties)
- **Status:** Stable

### discovery-service
- **Purpose:** Service registry for dynamic discovery of microservices (Eureka).
- **Port:** `8761`
- **Main Endpoints:**
  - `/eureka/**` (service registration and discovery dashboard at `http://localhost:8761`)
- **Status:** Stable

### gateway-service
- **Purpose:** API Gateway for routing, security (e.g., JWT validation), and cross-cutting concerns for requests to backend services (Spring Cloud Gateway).
- **Port:** `8080`
- **Main Endpoints:**
  - `/api/v1/{service-route}/**` (routes to backend services)
- **Status:** Stable (basic routing implemented)

---

## Business Logic Services

### user-service
- **Purpose:** Handles user registration, authentication (e.g., JWT generation), and management.
- **Port:** `8081`
- **Main Endpoints:**
  - `POST /api/v1/users/register`
  - `POST /api/v1/users/login`
  - `GET /api/v1/users/{id}`
- **Status:** Stable

### product-service
- **Purpose:** Manages the global product catalog and product details (CRUD operations for products). Integrates with Firebase for image storage.
- **Port:** `8082`
- **Main Endpoints:**
  - `GET /api/v1/products`
  - `POST /api/v1/products`
  - `GET /api/v1/products/{id}`
- **Status:** Stable (Firebase integration for images added)

### store-service
- **Purpose:** Manages store information, including store-specific products and inventory (potentially linking to `inventory-service`).
- **Port:** `8083`
- **Main Endpoints:**
  - `GET /api/v1/stores`
  - `POST /api/v1/stores`
  - `GET /api/v1/stores/{id}`
- **Status:** Stable

### inventory-service
- **Purpose:** Manages stock levels for products within specific stores. Provides APIs to check and update inventory.
- **Port:** `8085`
- **Main Endpoints:** (Example)
  - `GET /api/v1/inventory/{storeId}/{productId}`
  - `POST /api/v1/inventory/update`
- **Status:** Recently Added / In Development

### cart-service
- **Purpose:** Manages shopping cart operations for users (add, remove, view items).
- **Port:** `8084`
- **Main Endpoints:**
  - `GET /api/v1/cart/{userId}`
  - `POST /api/v1/cart/{userId}/items`
  - `DELETE /api/v1/cart/{userId}/items/{itemId}`
- **Status:** Stable (basic functionality)

### order-service
- **Purpose:** Handles order creation, processing, and history. May interact with `payment-service` and `notification-service`.
- **Port:** `8086`
- **Main Endpoints:** (Example)
  - `POST /api/v1/orders`
  - `GET /api/v1/orders/user/{userId}`
  - `GET /api/v1/orders/{orderId}`
- **Status:** Stable (basic functionality)

### payment-service
- **Purpose:** Integrates with payment gateways (e.g., Stripe) to process payments for orders.
- **Port:** `8087`
- **Main Endpoints:** (Example)
  - `POST /api/v1/payment/charge`
- **Status:** Recently Added / In Development (Stripe integration initiated)

### notification-service
- **Purpose:** Manages and sends notifications (e.g., order confirmations, shipping updates) via various channels (e.g., email, SMS). Uses Kafka for receiving notification requests.
- **Port:** `8088`
- **Kafka Topics:**
  - `notification-topic` (for consuming notification requests)
- **Status:** Recently Added / In Development (Kafka listener setup)

---

## Notes
- Each service manages its own PostgreSQL database (see `init-multi-db.sh`).
- Services communicate via REST APIs and Kafka for asynchronous events.
- Configuration is centralized via `config-service`.
- For detailed API documentation, refer to individual service Swagger/OpenAPI specs (if available) or source code.

---

_Last updated: May 25, 2025_
