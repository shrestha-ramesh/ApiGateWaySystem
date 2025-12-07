package com.sagaorchestrator.model.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDetail {
    private int paymentId;
    private int orderId;
    private int customerId;
    private double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
}
