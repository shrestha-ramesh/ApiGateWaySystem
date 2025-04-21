package com.payment.processor;

import com.payment.model.PaymentMethod;
import com.payment.model.PaymentRequest;

public interface PaymentProcessor {
    boolean processPayment(PaymentRequest paymentRequest);
    boolean refundPayment(int transactionId, PaymentMethod paymentMethod);
}
