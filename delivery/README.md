# Delivery Service Application
This project is a Spring Boot application that provides a delivery service with functionalities to schedule, track, and manage deliveries.

## Overview
The Delivery Service manages delivery requests and provides REST endpoints to interact with delivery operations. It uses PostgreSQL for data persistence.

## Project Structure
* **`src/main/java/com/delivery/controller/DeliveryController.java`**: REST controller for delivery-related operations.
* **`src/main/java/com/delivery/model/DeliveryRequest.java`**: Model class for delivery requests.
* **`src/main/java/com/delivery/model/DeliveryStatus.java`**: Enum for delivery statuses.
* **`src/main/java/com/delivery/service/DeliveryService.java`**: Service layer for delivery business logic.
* **`src/main/resources/application.yml`**: Configuration properties for the Delivery Service.
* **`build.gradle`**: Gradle build file for managing dependencies and build configuration.
* **`src/main/resources/banner.txt`**: Banner file.

## Configuration
The application is configured using `application.yml`. Key properties include:

* **`spring.banner.location`**: Specifies the location of the banner file.
* **`spring.application.name`**: Sets the application name to `delivery`.
* **`server.port`**: Defines the port on which the Delivery Service will run (default: 8084).
* **`queries.update-delivery-status`**: Defines the SQL query used to update delivery status.

API Endpoints
* `GET /delivery/token`: Provides a dummy token.
* `POST /delivery/schedule`: Schedules a delivery.
* `PUT /delivery/{scheduleId}/{orderId}/transit`: Marks a delivery as in transit.
* `PUT /delivery/{scheduleId}/{orderId}/delivered`: Marks a delivery as delivered.
* `PUT /delivery/{scheduleId}/{orderId}/fail`: Marks a delivery as failed.
* `PUT /delivery/{scheduleId}/{orderId}/cancel`: Cancels a delivery.

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