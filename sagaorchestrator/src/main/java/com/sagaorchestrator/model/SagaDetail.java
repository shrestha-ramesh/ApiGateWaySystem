package com.sagaorchestrator.model;

import com.sagaorchestrator.model.Delivery.DeliveryDetail;
import com.sagaorchestrator.model.order.OrderDetail;
import com.sagaorchestrator.model.payment.PaymentDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SagaDetail {
    private OrderDetail orderDetail;
    private PaymentDetail paymentDetail;
    private DeliveryDetail deliveryDetail;
}
