# Microservices Overview (SERVICES.md)

This document provides a high-level overview of each microservice in the Home Store backend. It describes the responsibilities, main endpoints, and current status (WIP or stable) for each service.

---

## config-service
- **Purpose:** Centralized configuration management for all microservices (Spring Cloud Config). Reads configuration files (e.g., `product-service-dev.yml`) from the `HomeStore_config/` directory (located at the project root).
- **Main Endpoints:**
  - `http://localhost:8888/service-name/profile` (serves configuration properties(dev and prod))
- **Status:** Stable

## discovery-service
- **Purpose:** Service registry for dynamic discovery of microservices (Eureka).
- **Main Endpoints:**
  - `/eureka/**` (service registration and discovery)
- **Status:** Not Started

## gateway-service
- **Purpose:** API Gateway for routing and securing requests to backend services (Spring Cloud Gateway).
- **Main Endpoints:**
  - `/api/**` (routes to backend services)
- **Status:** Not Started

## product-service
- **Purpose:** Manages product catalog (CRUD operations for products).
- **Main Endpoints:**
  - `GET /products` — List products
  - `POST /products` — Create product
  - `GET /products/{id}` — Get product details
  - `PUT /products/{id}` — Update product
  - `DELETE /products/{id}` — Delete product
- **Status:** Stable

## store-service
- **Purpose:** Manages store information and related operations.
- **Main Endpoints:**
  - `GET /stores` — List stores
  - `POST /stores` — Create store
  - `GET /stores/{id}` — Get store details
  - `PUT /stores/{id}` — Update store
  - `DELETE /stores/{id}` — Delete store
- **Status:** Stable

## user-service
- **Purpose:** Handles user registration, authentication, and management.
- **Main Endpoints:**
  - `POST /users/register` — Register user
  - `POST /users/login` — Authenticate user
  - `GET /users/{id}` — Get user profile
- **Status:** Stable

## cart-service
- **Purpose:** Manages shopping cart operations for users.
- **Main Endpoints:**
  - `GET /cart/{userId}` — Get user's cart
  - `POST /cart/{userId}/add` — Add item to cart
  - `POST /cart/{userId}/remove` — Remove item from cart
- **Status:** Work in progress (WIP)

## order-service
- **Purpose:** Handles order creation, management, and history.
- **Main Endpoints:**
  - `POST /orders` — Create order
  - `GET /orders/{userId}` — List user's orders
  - `GET /orders/{orderId}` — Get order details
- **Status:** Work in progress (WIP)

---

## Notes
- Each service manages its own database (PostgreSQL).
- Services communicate via REST APIs (and may use messaging in the future).
- Configuration is centralized via `config-service` which reads from the root `HomeStore_config/` directory.
- For detailed API documentation, see each service's README or source code.

---

_Last updated: April 19, 2025_
