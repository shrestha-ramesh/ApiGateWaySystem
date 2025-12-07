package com.sagaorchestrator.api;

import com.sagaorchestrator.model.payment.PaymentDetail;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PaymentAPI {

    private final HttpHeaders httpHeaders;

    private final RestTemplate restTemplate;

    public PaymentAPI(HttpHeaders httpHeaders, RestTemplate restTemplate) {
        this.httpHeaders = httpHeaders;
        this.restTemplate = restTemplate;
    }

    public String startSagaPayment(PaymentDetail paymentDetail) {
        String URL = UriComponentsBuilder.fromUriString("http://localhost:8082")
                .path("/payment/process")
                .toUriString();
        System.out.println(URL);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentDetail> requestEntity = new HttpEntity<>(paymentDetail,httpHeaders);

        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(URL, HttpMethod.POST, requestEntity, String.class);
        }catch (Exception e){
            System.out.println("This is exception");
        }
        System.out.println("===========response from payment======");
        System.out.println(response.getBody());
        return response == null ? "Null" : response.getBody();
    }

    public String rollbackPayment(int transactionId) {
        String URL = UriComponentsBuilder.fromUriString("http://localhost:8082")
                .path("{transactionId}/refund")
                .buildAndExpand(transactionId)
                .toUriString();
        System.out.println(URL);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentDetail> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, String.class);
        }catch (Exception e){
            System.out.println("This is exception");
        }
        System.out.println("===========response from payment======");
        System.out.println(response.getBody());
        return response == null ? "Null" : response.getBody();
    }
}
