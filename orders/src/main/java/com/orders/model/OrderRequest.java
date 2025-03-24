package com.orders.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class OrderRequest {
    @Id
    private int orderId;
    private String customerId;
    private String customerFullName;
    private List<Integer> productIds;
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
