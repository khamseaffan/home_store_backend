# Home Store Backend 

---

[![Coverage Status](https://coveralls.io/repos/github/khamseaffan/home_store_backend/badge.svg?branch=main)](https://coveralls.io/github/khamseaffan/home_store_backend?branch=main)
[![MIT](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/MIT)
<!--[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.khamse.home_store%3Ahome_store_backend&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.khamse.home_store%3Ahome_store_backend)-->
<!--[![Build Status](https://github.com/khamseaffan/home_store_backend/actions/workflows/maven.yml/badge.svg)](https://github.com/khamseaffan/home_store_backend/actions)-->




---

The backend of the Home Store App, built using **Spring Boot** and following a **microservices architecture**, provides RESTful APIs for managing products, stores, users, bills, and transactions. It uses **MySQL** as the database, with automatic schema generation via JPA.

## Features

- **Microservices Architecture**: Modular design for scalability and maintainability.
- **RESTful APIs**: Handles CRUD operations for various entities.
- **Automatic Schema Generation**: Spring JPA automatically creates database tables based on annotated models.
- **Secure and Extensible**: Designed for future integration with authentication and authorization mechanisms.

## Tech Stack

- **Framework**: Spring Boot
- **Database**: MySQL (via Hibernate and JPA)
- **Tools**: Maven, Postman (for API testing)
- **Language**: Java 11+

## Models and Schema

1. **Product**
    - Fields: `id`, `name`, `description`, `price`, `stock`
    - API Endpoints:
      - `GET /products`
      - `POST /products`
      - `PUT /products/{id}`
      - `DELETE /products/{id}`

2. **Store**
    - Fields: `id`, `name`, `location`, `contact`
    - API Endpoints:
      - `GET /stores`
      - `POST /stores`
      - `PUT /stores/{id}`
      - `DELETE /stores/{id}`

3. **User**
    - Fields: `id`, `name`, `email`, `password`
    - API Endpoints:
      - `GET /users`
      - `POST /users`
      - `PUT /users/{id}`
      - `DELETE /users/{id}`

4. **Bill**
    - Fields: `id`, `list_of_products` (to be normalized), `sub_total`, `store_id`
    - API Endpoints:
      - `GET /bills`
      - `POST /bills`

5. **Transactions**
    - Fields: `id`, `bill_id`, `user_id`, `date_time`, `total_bill`
    - API Endpoints:
      - `GET /transactions`
      - `POST /transactions`

## Installation

1. Clone the repository:
    ```bash
    git clone <backend-repo-url>
    cd home_store_backend
    ```

2. Ensure that the MySQL database `home_store` exists. If not, create it using the following command:
```sql
CREATE DATABASE home_store;
```

3. Update the `application.properties` file with your MySQL database credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/home_store
    spring.datasource.username=<your-username>
    spring.datasource.password=<your-password>
    ```

3. Build and run the application:
    ```bash
    mvn spring-boot:run
    ```

4. Test the APIs using Postman or your preferred API testing tool.
