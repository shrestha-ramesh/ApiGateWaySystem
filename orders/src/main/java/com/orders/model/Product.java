package com.orders.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.Immutable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Getter
public class Product {
    @Id
    private int id;
    private String name;
    private String category;
    private double price;
    private int stock;
}
