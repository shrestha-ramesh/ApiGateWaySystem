package com.sagaorchestrator.model.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail {
    private int orderId;
    private String customerId;
    private String customerFullName;
    private List<Integer> productIds;
    private double totalAmount;
    private OrderStatus orderStatus;
}
