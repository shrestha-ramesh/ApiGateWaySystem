package com.payment.service;

import com.payment.model.PaymentMethod;
import com.payment.model.PaymentRequest;
import com.payment.model.PaymentStatus;
import com.payment.processor.PaymentProcessor;
import com.payment.processor.PaymentProcessorFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentProcessorFactory paymentProcessorFactory;

    @Value("${queries.update-payment-status}")
    private String updatePaymentStatusQuery;

    public PaymentService(PaymentProcessorFactory paymentProcessorFactory) {
        this.paymentProcessorFactory = paymentProcessorFactory;
    }

    public String handlePaymentLifecycle(PaymentRequest paymentRequest) {
        boolean isProcessed = processPayment(paymentRequest);
        boolean isCompleted;
        if (isProcessed) {
            isCompleted = completePayment(paymentRequest.getPaymentId());
        }
        return "isCompleted && isProcessed";

    }

    public boolean processPayment(PaymentRequest paymentRequest) {
        paymentRequest.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        if (paymentRequest.getAmount() < 0 || paymentRequest.getPaymentMethod() == null) {
            return false;
        }
        PaymentProcessor paymentProcessor = paymentProcessorFactory.getProcessor(paymentRequest.getPaymentMethod());
        boolean isSuccess = paymentProcessor.processPayment(paymentRequest);
        if (!isSuccess) {
            return false;
        }
        paymentRequest.setPaymentStatus(PaymentStatus.COMPLETED);
        savePaymentData(paymentRequest);
        return true;
    }

    public boolean completePayment(int transactionId) {
        int rowsAffected = updatePaymentStatus(transactionId, PaymentStatus.COMPLETED);
        return rowsAffected > 0 ? true : false;
    }

    public boolean failPayment(int transactionId) {
        int rowsAffected = updatePaymentStatus(transactionId, PaymentStatus.FAILED);
        return rowsAffected > 0 ? true : false;
    }

    public boolean refundPayment(int transactionId) {
        PaymentMethod card = PaymentMethod.CREDIT_CARD;
        PaymentProcessor paymentProcessor = paymentProcessorFactory.getProcessor(card);
        paymentProcessor.refundPayment(transactionId, card);

        int rowsAffected = updatePaymentStatus(transactionId, PaymentStatus.REFUND);
        return rowsAffected > 0 ? true : false;
    }

    private int updatePaymentStatus(int transactionId, PaymentStatus paymentStatus) {
        SessionFactory sessionFactory = new Configuration().addAnnotatedClass(com.payment.model.PaymentRequest.class)
                .configure("payment-hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(updatePaymentStatusQuery);
        query.setParameter("status", paymentStatus);
        query.setParameter("id", transactionId);
        int rowsAffected = query.executeUpdate();
        transaction.commit();
        return rowsAffected;
    }

    public void savePaymentData(PaymentRequest paymentRequest) {
        SessionFactory sessionFactory = new Configuration().addAnnotatedClass(com.payment.model.PaymentRequest.class)
                .configure("payment-hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(paymentRequest);
        transaction.commit();
    }
}
