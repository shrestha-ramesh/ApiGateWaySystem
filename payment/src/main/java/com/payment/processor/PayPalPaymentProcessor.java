package com.payment.processor;

import com.payment.model.PaymentMethod;
import com.payment.model.PaymentRequest;

public class PayPalPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(PaymentRequest paymentRequest) {
        return false;
    }

    @Override
    public boolean refundPayment(int transactionId, PaymentMethod paymentMethod) {
        return false;
    }
}
