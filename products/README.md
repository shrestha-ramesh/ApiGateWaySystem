# Product Service Application
This project is a Spring Boot application that provides a product service with functionalities to save and retrieve product information. It also implements a Memento pattern for state management and integrates with Eureka for service discovery.

## Overview
The Product Service manages product data and provides REST endpoints to interact with products. It uses PostgreSQL for data persistence and includes security features.

## Project Structure
* **`src/main/java/com/products/controller/ProductController.java`**: REST controller for product-related operations.
* **`src/main/java/com/products/auth/HeaderUtil.java`**: Utility class for handling headers (e.g., token retrieval).
* **`src/main/java/com/products/model/Memento.java`**: Model class for Memento pattern.
* **`src/main/java/com/products/model/MementoTakeCare.java`**: Class for managing Memento states.
* **`src/main/java/com/products/model/Product.java`**: Model class for a single product.
* **`src/main/java/com/products/model/Products.java`**: Model class for a list of products.
* **`src/main/java/com/products/service/ProductService.java`**: Service layer for product business logic.
* **`src/main/resources/application.yml`**: Configuration properties for the Product Service.
* **`build.gradle`**: Gradle build file for managing dependencies and build configuration.

## Configuration
The application is configured using `application.yml`. Key properties include:

* **`spring.application.name`**: Sets the application name to `product`.
* **`server.port`**: Defines the port on which the Product Service will run (default: 8081).
* **`eureka.client.serviceUrl.defaultZone`**: Defines the default zone URL for Eureka service discovery.

API Endpoints
* `GET /product/token`: Retrieves a token from the request header.
* `POST /product/save`: Saves a list of products.
* `GET /product/findAll`: Retrieves all products.
* `POST /product/saveState`: Saves the current state as a Memento.

## Dependencies
The project uses the following dependencies:

* **`spring-boot-starter-web`**: Provides Spring MVC for web-related functionality.
* **`spring-boot-starter-actuator`**: Provides production-ready features.
* **`spring-cloud-starter-netflix-eureka-client`**: Enables Eureka client for service discovery.
* **`spring-boot-starter-test`**: Provides testing utilities for Spring Boot applications.
* **`junit-platform-launcher`**: JUnit 5 test runtime.
* **`spring-boot-starter-security`**: Provides Spring Security features.
* **`hibernate-core`**: Enables Hibernate ORM for database interaction.
* **`postgresql`**: PostgreSQL database driver.
* **`lombok`**: Reduces boilerplate code.
* **`spring-cloud-dependencies`**: Manages Spring Cloud dependencies using a BOM (Bill of Materials).

## Build and Run
### Prerequisites
* Java 17 or later
* Gradle
* PostgreSQL database
* Hibernate

### Build
To build the application, run the following command in the project root directory:
```
./gradlew build
```
To run the application, use:
```
./gradlew bootRun
```
Alternatively, you can run the generated JAR file:
``` 
java -jar build/libs/eureka-server-0.0.1-SNAPSHOT.jar
```
#### Testing
To run the unit tests, use:
```
./gradlew test
```
#### Spring Cloud Version
This project uses Spring Cloud version ```2024.0.0.```

#### Deployment
This Eureka Server application can be deployed to various environments, including:
* Cloud platforms (AWS, Azure, GCP)
* Container orchestration platforms (Kubernetes, Docker Swarm)
* Virtual machines

#### Notes
* Ensure that Eureka Server is running and accessible at `http://localhost:8761/eureka/`.
* Ensure that the PostgreSQL database is configured correctly.
* Configure security settings as needed for your environment.