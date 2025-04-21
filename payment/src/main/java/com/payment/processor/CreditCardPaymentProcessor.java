package com.payment.processor;

import com.payment.model.PaymentMethod;
import com.payment.model.PaymentRequest;
import org.springframework.stereotype.Component;

@Component
public class CreditCardPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(PaymentRequest paymentRequest) {
        try {
            System.out.println("Credit Card Processing");
            System.out.println("Calling api to make a payment "+paymentRequest.getAmount());
            String gateWayEndpoint = "https://api.paymentgateway.com/process ";
            //This gateWayEndpoint call to process payment for example purpose only;
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean refundPayment(int transactionId, PaymentMethod paymentMethod) {
        try {
            System.out.println("Credit Card Processing");
            System.out.println("Calling api to make a payment "+transactionId);
            String gateWayEndpoint = "https://api.paymentgateway.com/refund ";
            //This gateWayEndpoint call to process payment for example purpose only;
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
