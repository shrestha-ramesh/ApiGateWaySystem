package com.products.service;

import com.products.model.Product;
import com.products.model.Products;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    public Products saveProduct(Products products) {
        String test1 = "test one first";
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(com.products.model.Product.class)
                .configure().buildSessionFactory();

        Session session = sessionFactory.openSession();

        products.getProducts().forEach(product -> {
            Transaction transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        });

        return products;
    }

    public Products findAllProduct() {
        String produceCheck = "product check";
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(com.products.model.Product.class)
                .configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Product> products = null;

        try {
            transaction = session.beginTransaction();
            // Query to fetch all data
            Query<Product> query = session.createQuery("FROM Product", com.products.model.Product.class);
            products = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Products.builder().products(products).build();
    }
}
