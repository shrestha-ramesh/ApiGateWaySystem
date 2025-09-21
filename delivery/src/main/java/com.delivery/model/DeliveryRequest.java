package com.delivery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class DeliveryRequest {

    @Id
    private int deliveryId;
    private int orderId;
    private int customerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private DeliveryAddress deliveryAddress;

    private LocalDateTime scheduleDeliveryTime;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
}
