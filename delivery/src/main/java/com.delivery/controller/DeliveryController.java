package com.delivery.controller;

import com.delivery.model.DeliveryRequest;
import com.delivery.model.DeliveryStatus;
import com.delivery.service.DeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService){
        this.deliveryService = deliveryService;
    }

    @GetMapping("/token")
    public String getToken(){
        System.out.println("This is token end points");
        return "Token";
    }

    @PostMapping("/schedule")
    public String scheduleDelivery(@RequestBody DeliveryRequest deliveryRequest){
        boolean isSchedule = true;
        return "schedule";
    }
    @PutMapping("{scheduleId}/{orderId}/transit")
    public ResponseEntity<String> inTransitDelivery(@PathVariable int scheduleId, @PathVariable int orderId){
        System.out.printf("This is transit %d, %d",scheduleId, orderId);
        deliveryService.inTransitDelivery(scheduleId, orderId);
        return ResponseEntity.ok("In transit ");
    }
    @PutMapping("{scheduleId}/{orderId}/delivered")
    public ResponseEntity<String> delivered(@PathVariable int scheduleId, @PathVariable int orderId){
        deliveryService.delivered(scheduleId, orderId);
        return ResponseEntity.ok("Delivery");
    }
    @PutMapping("{scheduleId}/{orderId}/fail")
    public ResponseEntity<String> failDelivery(@PathVariable int scheduleId, @PathVariable int orderId){
        deliveryService.failDelivery(scheduleId, orderId);
        return ResponseEntity.ok("fail");
    }
    @PutMapping("{scheduleId}/{orderId}/cancel")
    public DeliveryRequest cancelDelivery(@PathVariable int scheduleId, int orderId){
        DeliveryRequest deliveryRequest = DeliveryRequest.builder()
                .deliveryId(4)
                .deliveryStatus(DeliveryStatus.DELIVERED)
                .build();
        boolean isCancel = deliveryService.cancelDelivery(scheduleId, orderId);
        return deliveryRequest;
    }
}

