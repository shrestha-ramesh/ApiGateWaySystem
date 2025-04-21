package com.payment.processor;

import com.payment.model.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PaymentProcessorFactory {
    private final Map<PaymentMethod, PaymentProcessor> processorMap;
    public PaymentProcessorFactory(List<PaymentProcessor> processors){
        processorMap = new HashMap<>();
        processors.forEach(processor->{
            System.out.println("This is processorMap");
            if(processor instanceof CreditCardPaymentProcessor){
                processorMap.put(PaymentMethod.CREDIT_CARD, processor);
            } else if (processor instanceof GooglePayPaymentProcessor) {
                processorMap.put(PaymentMethod.GOOGLE_PAY, processor);
            }else if (processor instanceof PayPalPaymentProcessor){
                processorMap.put(PaymentMethod.PAYPAL, processor);
            }
        });
    }
    public PaymentProcessor getProcessor(PaymentMethod paymentMethod){
        System.out.println("This is getProcessor "+paymentMethod);
        return processorMap.get(paymentMethod);
    }
}
