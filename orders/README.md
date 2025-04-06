# Order Service Application
This project is a Spring Boot application that provides an order service with functionalities to create, process, ship, deliver, and cancel orders. It also integrates with Eureka for service discovery.

## Overview
The Order Service manages order data and provides REST endpoints to interact with orders. It uses PostgreSQL for data persistence and includes security features.

## Project Structure
* **`src/main/java/com/orders/controller/OrderController.java`**: REST controller for order-related operations.
* **`src/main/java/com/orders/auth/HeaderUtil.java`**: Utility class for handling headers (e.g., token retrieval).
* **`src/main/java/com/orders/model/OrderRequest.java`**: Model class for order requests.
* **`src/main/java/com/orders/model/OrderStatus.java`**: Enum for order statuses.
* **`src/main/java/com/orders/service/OrderService.java`**: Service layer for order business logic.
* **`src/main/resources/application.yml`**: Configuration properties for the Order Service.
* **`build.gradle`**: Gradle build file for managing dependencies and build configuration.

## Configuration
The application is configured using `application.yml`. Key properties include:

* **`spring.application.name`**: Sets the application name to `orders`.
* **`server.port`**: Defines the port on which the Order Service will run (default: 8083).
* **`eureka.client.serviceUrl.defaultZone`**: Defines the default zone URL for Eureka service discovery.
* **`eureka.instance.preferIpAddress`**: configures Eureka to use IP address.
* **`eureka.instance.nonSecurePort`**: configures Eureka non secure port.
* **`queries.update-order-status`**: Defines the SQL query used to update order status.

API Endpoints
* `GET /order/token`: Retrieves a token from the request header.
* `POST /order/create`: Creates a new order.
* `PUT /order/{orderId}/process`: Processes an order.
* `PUT /order/{orderId}/ship`: Ships an order.
* `PUT /order/{orderId}/deliver`: Delivers an order.
* `PUT /order/{orderId}/cancel`: Cancels an order.

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