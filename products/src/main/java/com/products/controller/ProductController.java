package com.products.controller;


import com.products.model.Products;
import com.products.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService =productService;
    }
    @GetMapping("/token")
    public String getToken(){
        System.out.println("This is token");
        return "HeaderUtil.getToken()";
    }
    @PostMapping("/save")
    public Products saveProduct(@RequestBody Products products){
        return productService.saveProduct(products);
    }

    @GetMapping("/findAll")
    public Products findAllProduct(){
        return productService.findAllProduct();
    }
}
