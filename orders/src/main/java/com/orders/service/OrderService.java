package com.orders.service;

import com.orders.model.OrderRequest;
import com.orders.model.OrderStatus;
import com.orders.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    @Value("${queries.update-order-status}")
    private String updateOrderStatusQuery;
    public boolean createOrder(OrderRequest orderRequest){
        orderRequest.setOrderStatus(OrderStatus.PENDING);
        getTotalAmount(orderRequest);
        System.out.println(orderRequest);
        saveOrderData(orderRequest);
        return true;
    }
    public boolean processOrder(int orderId) {
        int rowsAffected = updateOrderStatus(orderId, OrderStatus.PROCESSING);
        return rowsAffected > 0 ? true : false;
    }

    public boolean shipOrder(int orderId) {
        int rowsAffected = updateOrderStatus(orderId, OrderStatus.SHIPPED);
        return rowsAffected > 0 ? true : false;
    }

    public boolean deliverOrder(int orderId) {
        int rowsAffected =  updateOrderStatus(orderId, OrderStatus.DELIVERED);
        return rowsAffected > 0 ? true : false;
    }
    public boolean cancelOrder(int orderId){
        int rowsAffected = updateOrderStatus(orderId, OrderStatus.CANCELLED);
        return rowsAffected > 0 ? true : false;
    }
    public void saveOrderData(OrderRequest orderRequest){
        SessionFactory sessionFactory = new Configuration().addAnnotatedClass(com.orders.model.OrderRequest.class)
                .configure("product-hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(orderRequest);
        transaction.commit();
        System.out.println("This data is saved");
    }
    public int updateOrderStatus(int orderId, OrderStatus orderStatus){
        SessionFactory sessionFactory = new Configuration().addAnnotatedClass(com.orders.model.OrderRequest.class)
                .configure("product-hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(updateOrderStatusQuery);
        query.setParameter("status", orderStatus);
        query.setParameter("id", orderId);
        int rowsAffected = query.executeUpdate();
        transaction.commit();
        return rowsAffected;
    }
    public double getTotalAmount(OrderRequest orderRequest){
        List<Product> products = new ArrayList<>();
        SessionFactory sessionFactory = new Configuration().addAnnotatedClass(com.orders.model.Product.class)
                .configure("product-hibernate.cfg.xml").buildSessionFactory();
        Session session= sessionFactory.openSession();

        if(orderRequest.getProductIds() == null){
            return 0;
        }else {
            orderRequest.getProductIds().forEach(id->{
                Product product = session.get(Product.class, id);
                products.add(product);
            });
            double totalAmount = products.stream().mapToDouble(p -> p.getPrice()).sum();
            orderRequest.setTotalAmount(totalAmount);
            return totalAmount;
        }
    }
}
