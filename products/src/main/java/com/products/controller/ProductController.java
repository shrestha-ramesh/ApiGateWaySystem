package com.products.controller;


import com.products.authorization.HeaderUtil;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {



    @GetMapping("/token")
    public String getToken(){
        return HeaderUtil.getToken();
    }


}
