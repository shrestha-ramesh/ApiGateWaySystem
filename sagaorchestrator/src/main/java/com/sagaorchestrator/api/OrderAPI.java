package com.sagaorchestrator.api;

import com.sagaorchestrator.model.order.OrderDetail;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OrderAPI {
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;

    public OrderAPI(RestTemplate restTemplate, HttpHeaders httpHeaders) {
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
    }

    public String startSagaOrder(OrderDetail orderDetail) {
        String URL = UriComponentsBuilder.fromUriString("http://localhost:8083")
                .path("/order/create")
                .toUriString();
        System.out.println(URL);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OrderDetail> requestEntity = new HttpEntity<>(orderDetail, httpHeaders);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(URL, HttpMethod.POST, requestEntity, String.class);
        }catch (Exception e){
            System.out.println("this is Exception");
        }
        System.out.println("===========response from order======");
        return response == null ? "Null" : response.getBody();
    }

    public String rollbackOrder(int orderId) {

        String URL = UriComponentsBuilder.fromUriString("http://localhost:8083")
                .path("{orderId}/cancel")
                .buildAndExpand(orderId)
                .toUriString();
        System.out.println(URL);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, String.class);
        }catch (Exception e){
            System.out.println("this is Exception");
        }
        System.out.println("===========response from order======");
        return response == null ? "Null" : response.getBody();
    }
}
