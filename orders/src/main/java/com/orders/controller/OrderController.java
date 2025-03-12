package com.orders.controller;


import com.orders.auth.HeaderUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/token")
    public String getOrder(){
        return HeaderUtil.getToken();
    }
}
