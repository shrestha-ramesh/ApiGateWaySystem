package com.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;


@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Autowired
	private TokenValidationFilter tokenValidationFilter;

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/order/**")
						.filters(f -> f
								.filter( tokenValidationFilter.apply(new TokenValidationFilter.Config()))
						)
						.uri("http://localhost:8083"))
				.route(p -> p
						.path("/product/**")
						.uri("http://localhost:8081"))
				.route(p -> p
						.path("/payment/**")
						.uri("http://localhost:8082"))
				.build();
	}
}
