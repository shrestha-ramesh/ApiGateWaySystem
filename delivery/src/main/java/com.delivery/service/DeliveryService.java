package com.delivery.service;

import com.delivery.model.DeliveryAddress;
import com.delivery.model.DeliveryRequest;
import com.delivery.model.DeliveryStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@Service
public class DeliveryService {

    @Value("${queries.update-delivery-status}")
    private String updateDeliveryStatusQuery;
    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders;

    public DeliveryService(RestTemplate restTemplate, HttpHeaders httpHeaders) {
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
    }

    public boolean scheduleDelivery(DeliveryRequest deliveryRequest) {
        System.out.println("This is service delivery");
        deliveryRequest.setDeliveryStatus(DeliveryStatus.SCHEDULED);
        deliveryRequest.setDeliveryId(20);
        deliveryRequest.setOrderId(5);
        deliveryRequest.setDeliveryAddress(DeliveryAddress.builder().addressId(19)
                .state("Illinois")
                .city("Chicago").build());
        deliveryRequest.setScheduleDeliveryTime(LocalDateTime.now());
        if (deliveryRequest == null || deliveryRequest.getDeliveryAddress() == null
                || deliveryRequest.getScheduleDeliveryTime() == null) {
            return false;
        }
        saveDeliveryData(deliveryRequest);
        return true;
    }

    public boolean inTransitDelivery(int deliveryId, int orderId) {
        System.out.printf("This is in transit delivery %d, %d", deliveryId, orderId);
        int rowsAffected = updateDeliveryStatus(deliveryId, orderId, DeliveryStatus.IN_TRANSIT);
        return rowsAffected > 0 ? true : false;
    }

    public boolean delivered(int deliveryId, int orderId) {
        int rowsAffected = updateDeliveryStatus(deliveryId, orderId, DeliveryStatus.DELIVERED);
        return rowsAffected > 0 ? true : false;
    }

    public boolean failDelivery(int deliveryId, int orderId) {
        int rowsAffected = updateDeliveryStatus(deliveryId, orderId, DeliveryStatus.FAILED);
        return rowsAffected > 0 ? true : false;
    }

    public boolean cancelDelivery(int deliveryId, int orderId) {
        int rowsAffected = updateDeliveryStatus(deliveryId, orderId, DeliveryStatus.CANCELLED);
        return rowsAffected > 0 ? true : false;
    }

    private void saveDeliveryData(DeliveryRequest deliveryRequest) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(com.delivery.model.DeliveryRequest.class)
                .addAnnotatedClass(com.delivery.model.DeliveryAddress.class)
                .configure("delivery-hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(deliveryRequest);
        transaction.commit();
    }

    private int updateDeliveryStatus(int deliveryId, int orderId, DeliveryStatus deliveryStatus) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(com.delivery.model.DeliveryRequest.class)
                .addAnnotatedClass(com.delivery.model.DeliveryAddress.class)
                .configure("delivery-hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery(updateDeliveryStatusQuery);
        query.setParameter("status", deliveryStatus);
        query.setParameter("id", deliveryId);

        if (deliveryStatus == DeliveryStatus.IN_TRANSIT) {
            updateOrderStatus(orderId, "ship");
        } else if (deliveryStatus == DeliveryStatus.DELIVERED) {

            updateOrderStatus(orderId, "deliver");
        } else if (deliveryStatus == DeliveryStatus.CANCELLED) {
            updateOrderStatus(orderId, "cancel");
        }
        int rowsAffected = query.executeUpdate();
        transaction.commit();
        return rowsAffected;
    }

    public void updateOrderStatus(int orderId, String orderStatus) {
        String URL = UriComponentsBuilder.fromUriString("http://localhost:8083")
                .path("/order/{orderId}/{orderStatus}")
                .buildAndExpand(orderId, orderStatus).toUriString();
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, String.class);
        System.out.println(response.getBody());
    }
}
