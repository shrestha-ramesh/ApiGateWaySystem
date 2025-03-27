package com.delivery;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @GetMapping("/token")
    public String getToken(){
        System.out.println("This is token end points");
        return "Token";
    }

    @PostMapping("/schedule")
    public String scheduleDelivery(){
        boolean isSchedule = DeliveryService.scheduleDelivery();
        return "schedule";
    }
    @PostMapping("/cancel/{scheduleId}")
    public DeliveryRequest cancelDelivery(){
        String deliveryId = IdGenerator.generateDeliveryId();
        DeliveryRequest deliveryRequest = DeliveryRequest.builder()
                .deliveryId(deliveryId)
                .deliveryStatus(DeliveryStatus.DELIVERED)
                .build();
        boolean isCancel = DeliveryService.cancelDelivery();
        return deliveryRequest;
    }
}

