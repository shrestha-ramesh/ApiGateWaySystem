package com.apigateway;

import com.apigateway.auth.TokenValidationFilter;
import com.apigateway.ratelimiter.RateLimitingFilter;
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


	@Autowired
	private RateLimitingFilter rateLimitingFilter;

	@Bean
	public RouteLocator apiRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/order/**")
						.filters(f -> f
								.filter(rateLimitingFilter.apply(new RateLimitingFilter.Config()))
								.filter(tokenValidationFilter.apply(new TokenValidationFilter.Config()))
						)
						.uri("http://localhost:8083"))
				.route(p -> p
						.path("/product/**")
						.filters(f->f
								.filter(rateLimitingFilter.apply(new RateLimitingFilter.Config()))
								.filter(tokenValidationFilter.apply(new TokenValidationFilter.Config()))
						)
						.uri("http://localhost:8081"))
				.route(p -> p
						.path("/payment/**")
						.filters(f -> f
								.filter(rateLimitingFilter.apply(new RateLimitingFilter.Config()))
								.filter(tokenValidationFilter.apply(new TokenValidationFilter.Config()))
						)
						.uri("http://localhost:8082"))
				.build();
	}
}
