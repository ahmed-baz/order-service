# Order Service

This is a **Spring Boot** application that demonstrates how to build a RESTful service with **CRUD** operations, using
the power of **Spring Security** and OAuth server **Keycloak** for authentication and authorization, and
use **PostgreSQL** for data persistence.

The service manages CRUD operations for `Order` and `Product`, and exposing the following CRUD APIs:

- **get products**: to get products list.
- **create order**: to create new order.
- **update order**: to update existing order (update the PENDING order only).
- **delete order**: to delete existing order (delete the PENDING order only).
- **get orders**: to get user orders list (depending on the user email from the token).
- **get order by ID**: to get order by ID.

## Features

- Using **Spring Security** and **Keycloak** OAuth server for authentication and authorization
- **CRUD operations** using Spring boot
- **PostgreSQL** as the database
- **RabbitMQ** for messaging
- Using **liquibase**  for database migration
- Using **spring cache** and **hazelcast** for caching
- Using **OpenAPI** for API documentation
- Enabling **database auditing**
- Using **Lombok** for code generation
- Using **Mapstruct** for object mapping
- Using **Docker** for containerization

## Prerequisites

Before running the application, ensure you have the following installed:

- **Java 17+** (Recommended)
- **Maven 3.8+**
- **PostgreSQL** (either locally or via Docker)
- **Docker** (optional, for PostgreSQL container)

## Setup Instructions

#### Follow these steps to get the service running:

- clone the repository, and run docker compose:

    ```bash
    git clone https://github.com/ahmed-baz/order-service.git
    cd order-service/docker
    docker compose up -d
    ```

- login to keycloak http://localhost:9696 with admin/admin
- create realm order-service
- create client order-api
- create new user to test the service