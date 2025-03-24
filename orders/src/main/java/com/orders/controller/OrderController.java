package com.orders.controller;


import com.orders.auth.HeaderUtil;
import com.orders.model.OrderRequest;
import com.orders.model.OrderStatus;
import com.orders.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
    @GetMapping("/token")
    public String getOrder(){
        return HeaderUtil.getToken();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest){
        boolean isCreated = orderService.createOrder(orderRequest);
        return isCreated ?
                ResponseEntity.ok("Order create successfully") :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Order creation fail");

    }
    @PutMapping("{orderId}/process")
    public ResponseEntity<String> processOrder(@PathVariable int orderId){
        boolean isProcess = orderService.processOrder(orderId);
        return isProcess ?
                ResponseEntity.ok("Order processing") :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Order processing fail");
    }
    @PutMapping("{orderId}/ship")
    public ResponseEntity<String> shipOrder(@PathVariable int orderId){
        boolean isShip = orderService.shipOrder(orderId);
        return isShip ?
                ResponseEntity.ok("Order ship") :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Order ship fail");
    }
    @PutMapping("{orderId}/deliver")
    public ResponseEntity<String> deliverOrder(@PathVariable int orderId){
        boolean isDeliver = orderService.deliverOrder(orderId);
        return isDeliver ?
                ResponseEntity.ok("Order deliver") :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Order delivery fail");
    }
    @PutMapping("{orderId}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable int orderId){
        boolean isCancelOrder = orderService.cancelOrder(orderId);
        return isCancelOrder ?
                ResponseEntity.ok("Order Cancel"):
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Order cancel fail");
    }
}
