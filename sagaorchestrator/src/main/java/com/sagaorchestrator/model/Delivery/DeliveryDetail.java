package com.sagaorchestrator.model.Delivery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryDetail {
    private int deliveryId;
    private int orderId;
    private int customerId;
    private DeliveryAddress deliveryAddress;
    private LocalDateTime scheduleDeliveryTime;
    private DeliveryStatus deliveryStatus;
}
