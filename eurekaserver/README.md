# Eureka Server Application

This project is a Spring Boot application that sets up a Eureka Server for service discovery in a microservices architecture.
## Overview

The Eureka Server acts as a registry for microservices, allowing them to register themselves and discover other services. This enables dynamic service location and communication within a distributed system.

## Project Structure

* **`src/main/java/com/eurekaserver/EurekaServerApplication.java`**: Main Spring Boot application class with `@EnableEurekaServer` annotation.
* **`src/main/resources/application.properties`**: Configuration properties for the Eureka Server.
* **`build.gradle`**: Gradle build file for managing dependencies and build configuration.

## Configuration

The application is configured using `application.properties`. Key properties include:

* **`spring.application.name`**: Sets the application name to `eureka-server`.
* **`server.port`**: Defines the port on which the Eureka Server will run (default: 8761).
* **`eureka.instance.hostname`**: Specifies the hostname of the Eureka Server instance (default: `localhost`).
* **`eureka.client.registerWithEureka`**: Set to `false` as this is the server itself.
* **`eureka.client.fetchRegistry`**: Set to `false` as this is the server itself.
* **`eureka.client.serviceUrl.defaultZone`**: Defines the default zone URL for clients to register with and discover services.

## Dependencies

The project uses the following dependencies:

* **`spring-boot-starter-web`**: Provides Spring MVC for web-related functionality.
* **`spring-cloud-starter-netflix-eureka-server`**: Enables Eureka Server capabilities.
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
Alternatively, you can run the generated JAR file:
``` 
java -jar build/libs/eureka-server-0.0.1-SNAPSHOT.jar
```
#### Accessing the Eureka Dashboard
Once the application is running, you can access the Eureka Dashboard at:
```
http://localhost:8761/
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
* Ensure that the hostname and port are correctly configured for your environment.
* For High Availability, deploy multiple Eureka Server instances.