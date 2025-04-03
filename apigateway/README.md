# API Gateway Application

This project is a Spring Boot-based API Gateway application designed to route requests to microservices while applying rate limiting and token validation.

## Overview

The API Gateway acts as a single entry point for client requests, routing them to the appropriate microservices based on the request path. It also implements rate limiting using Resilience4j and token validation for security.

## Project Structure

* **`src/main/java/com/apigateway/ApiGatewayApplication.java`**: Main Spring Boot application class with route configuration.
* **`src/main/java/com/apigateway/auth/TokenValidationFilter.java`**: Custom filter for token validation.
* **`src/main/java/com/apigateway/ratelimiter/RateLimitingFilter.java`**: Custom filter for rate limiting.
* **`src/main/resources/application.yml`**: Configuration properties for the API Gateway.
* **`build.gradle`**: Gradle build file for managing dependencies and build configuration.

## Configuration

The application is configured using `application.yml`. Key properties include:

* **`spring.application.name`**: Sets the application name to `api-gateway`.
* **`spring.main.web-application-type`**: Sets the web application type to `reactive`.
* **`server.port`**: Defines the port on which the API Gateway will run (default: 8080).
* **`eureka.client.serviceUrl.defaultZone`**: Defines the default zone URL for Eureka service discovery.

## Dependencies
The project uses the following dependencies:

* **`spring-boot-starter-web`**: Provides Spring MVC for web-related functionality.
* **`spring-cloud-starter-gateway`**: Enables Spring Cloud Gateway capabilities.
* **`spring-cloud-starter-netflix-eureka-client`**: Enables Eureka client for service discovery.
* **`lombok`**: Reduces boilerplate code.
* **`resilience4j-ratelimiter`**: Implements rate limiting.
* **`spring-boot-starter-test`**: Provides testing utilities for Spring Boot applications.
* **`junit-platform-launcher`**: JUnit 5 test runtime.
* **`spring-cloud-dependencies`**: Manages Spring Cloud dependencies using a BOM (Bill of Materials).

## Build and Run

### Prerequisites

* Java 17 or later
* Gradle

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
* Ensure that the microservices (order, product, payment) are running and accessible at the specified URLs.
* Configure the TokenValidationFilter and RateLimitingFilter as needed for your environment.