package com.apigateway.health;

import com.apigateway.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ServiceAvailabilityFilter extends AbstractGatewayFilterFactory<ServiceAvailabilityFilter.Config> {

    private static final Logger logger = LoggerFactory.getLogger(ServiceAvailabilityFilter.class);

    private final WebClient.Builder webClientBuilder;

    public ServiceAvailabilityFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String token = extractToken(exchange);
            return checkServiceAvailability(exchange, chain, token, config.getUri());
        };
    }
    private String extractToken(ServerWebExchange exchange) {
        return exchange.getRequest().getHeaders().getFirst("Authorization");
    }

    private Mono<Void> checkServiceAvailability(ServerWebExchange exchange, GatewayFilterChain chain, String token, String uri) {
        return webClientBuilder.build().method(HttpMethod.GET)
                .uri(uri+"/actuator/health")
                .headers(headers -> headers.set("Authorization", token))
                .retrieve()
                .bodyToMono(Void.class)
                .then(chain.filter(exchange))
                .onErrorResume(throwable -> {
                    logger.error("Down stream service is not running. Error");
                    return Mono.error(new ApiException());
                });
    }

    public static class Config {
        // Configuration properties if any
        private String uri;

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }
    }
}
