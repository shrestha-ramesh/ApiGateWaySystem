package com.sagaorchestrator.orchestrator;

import com.sagaorchestrator.api.DeliveryAPI;
import com.sagaorchestrator.api.OrderAPI;
import com.sagaorchestrator.api.PaymentAPI;
import com.sagaorchestrator.model.SagaDetail;
import org.springframework.stereotype.Component;

@Component
public class SagaOrchestrator {
    
    private final DeliveryAPI deliveryAPI;
    private final OrderAPI orderAPI;
    private final PaymentAPI paymentAPI;

    public SagaOrchestrator(DeliveryAPI deliveryAPI, OrderAPI orderAPI, PaymentAPI paymentAPI) {
        this.deliveryAPI = deliveryAPI;
        this.orderAPI = orderAPI;
        this.paymentAPI = paymentAPI;
    }

    public String startSaga(SagaDetail sagaDetail) {
        String statusMessage = "Saga completed successfully";
        String order = orderAPI.startSagaOrder(sagaDetail.getOrderDetail());
        if (!order.equals("Order create successfully")) {
            return "order fail";
        }
        String payment = paymentAPI.startSagaPayment(sagaDetail.getPaymentDetail());
        if (!payment.equals("Payment Service Successfully")) {
            System.out.println("order rollback");
            orderAPI.rollbackOrder(sagaDetail.getOrderDetail().getOrderId());
            return "payment fail";
        }
        String delivery = deliveryAPI.startSagaDelivery(sagaDetail.getDeliveryDetail());
        if (!delivery.equals("Delivery Scheduled Successfully")) {
            System.out.println("payment and order rollback");
            paymentAPI.rollbackPayment(sagaDetail.getPaymentDetail().getPaymentId());
            orderAPI.rollbackOrder(sagaDetail.getOrderDetail().getOrderId());
            return "delivery fail";
        }

        return statusMessage;
    }
}
