package com.delivery;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @GetMapping("/token")
    public String getToken(){
        System.out.println("This is token end points");
        return "Token";
    }
}
