package com.sagaorchestrator.model.Delivery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryAddress {
    private int addressId;
    private String streetAddress;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String recipientName;
    private String recipientPhone;
    private String apartmentNumber;
    private String deliveryInstructions;
    private String email;
}
