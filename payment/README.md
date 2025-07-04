# Payment Service Application
This project is a Spring Boot application that provides a payment service with functionalities to process, complete, fail, and refund payments. It also integrates with Eureka for service discovery.

## Overview
The Payment Service manages payment transactions and provides REST endpoints to interact with payments. It uses PostgresSQL for data persistence and includes security features.

## Project Structure
* **`src/main/java/com/payment/controller/PaymentController.java`**: REST controller for payment-related operations.
* **`src/main/java/com/payment/auth/HeaderUtil.java`**: Utility class for handling headers (e.g., token retrieval).
* **`src/main/java/com/payment/model/PaymentRequest.java`**: Model class for payment requests.
* **`src/main/java/com/payment/service/PaymentService.java`**: Service layer for payment business logic.
* **`src/main/resources/application.yml`**: Configuration properties for the Payment Service.
* **`build.gradle`**: Gradle build file for managing dependencies and build configuration.

## Configuration
The application is configured using `application.yml`. Key properties include:

* **`spring.application.name`**: Sets the application name to `payment`.
* **`server.port`**: Defines the port on which the Payment Service will run (default: 8082).
* **`eureka.client.serviceUrl.defaultZone`**: Defines the default zone URL for Eureka service discovery.
* **`queries.update-payment-status`**: Defines the SQL query used to update payment status.

API Endpoints
* `GET /payment/token`: Retrieves a token from the request header.
* `POST /payment/process`: Processes a payment.
* `PUT /payment/{transactionId}/complete`: Completes a payment.
* `PUT /payment/{transactionId}/fail`: Marks a payment as failed.
* `POST /payment/{transactionId}/refund`: Refunds a payment.

## Dependencies
The project uses the following dependencies:

* **`spring-boot-starter-web`**: Provides Spring MVC for web-related functionality.
* **`spring-cloud-starter-netflix-eureka-client`**: Enables a Eureka client for service discovery.
* **`spring-boot-starter-test`**: Provides testing utilities for Spring Boot applications.
* **`junit-platform-launcher`**: JUnit 5 test runtime.
* **`spring-boot-starter-security`**: Provides Spring Security features.
* **`hibernate-core`**: Enables Hibernate ORM for database interaction.
* **`postgresql`**: PostgresSQL database driver.
* **`lombok`**: Reduces boilerplate code.
* **`spring-cloud-dependencies`**: Manages Spring Cloud dependencies using a BOM (Bill of Materials).

## Build and Run
### Prerequisites
* Java 17 or later
* Gradle
* PostgresSQL database
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
* Ensure that the PostgresSQL database is configured correctly.
* Configure security settings as needed for your environment.