package com.sagaorchestrator.api;

import com.sagaorchestrator.model.Delivery.DeliveryDetail;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class DeliveryAPI {
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;

    public DeliveryAPI(RestTemplate restTemplate, HttpHeaders httpHeaders) {
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
    }

    public String startSagaDelivery(DeliveryDetail deliveryDetail) {
        String URL = UriComponentsBuilder.fromUriString("http://localhost:8084")
                .path("/delivery/schedule")
                .toUriString();
        System.out.println(URL);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DeliveryDetail> requestEntity = new HttpEntity<>(deliveryDetail, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, requestEntity, String.class);
        System.out.println("=================This is delivery detail=============");
        System.out.println(response.getBody());
        return response.getBody();
    }
}
