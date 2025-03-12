package com.payment.controller;


import com.payment.auth.HeaderUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @GetMapping("/payment")
    public String getPayment(){
        return HeaderUtil.getToken();
    }
}
