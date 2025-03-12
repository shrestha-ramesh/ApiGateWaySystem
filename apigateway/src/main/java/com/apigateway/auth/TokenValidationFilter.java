package com.apigateway.auth;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TokenValidationFilter extends AbstractGatewayFilterFactory<TokenValidationFilter.Config> {
    public TokenValidationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            HttpHeaders httpHeaders = request.getHeaders();
            String authorizationHeader = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION);

            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
                return unauthorizedResponse(exchange.getResponse(), "Missing or invalid Authorization header");
            }
            String token = authorizationHeader.substring(7);
            System.out.println("This is token api-gateway "+token);
            boolean isValid = validateToken(token);

            if (!isValid) {
                return unauthorizedResponse(exchange.getResponse(), "Invalid token");
            }

            return chain.filter(exchange);
        });
    }

    private boolean validateToken(String token) {
        return "eyJhbGciOiJIUzI1NiJ9.e30.BvaJ0jo-ls2qAtVpacHDm1QT10jf821YtQ-AZ3tTYVU".equals(token);
    }

    private Mono<Void> unauthorizedResponse(ServerHttpResponse response, String missingOrInvalidAuthorizationHeader) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json");
        byte[] bytes = ("{\"message\":\"" + missingOrInvalidAuthorizationHeader + "\"}").getBytes();
        return response.writeWith(Mono.just(response.bufferFactory().wrap(bytes)));
    }

    public static class Config {
        // Configuration properties if needed
    }
}
