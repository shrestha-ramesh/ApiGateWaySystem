package com.products.controller;


import com.products.auth.HeaderUtil;
import com.products.model.Memento;
import com.products.model.MementoTakeCare;
import com.products.model.Product;
import com.products.model.Products;
import com.products.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    private MementoTakeCare mementoTakeCare;

    public ProductController(ProductService productService, MementoTakeCare mementoTakeCare){
        this.productService =productService;
        this.mementoTakeCare = mementoTakeCare;
    }
    @GetMapping("/token")
    public String getToken(){
        return HeaderUtil.getToken();
    }
    @PostMapping("/save")
    public Products saveProduct(@RequestBody Products products){
        return productService.saveProduct(products);
    }

    @GetMapping("/findAll")
    public Products findAllProduct(){
        return productService.findAllProduct();
    }
    @PostMapping("/saveState")
    public List<Memento> saveState(@RequestBody Memento memento){
        mementoTakeCare.addMemento(memento);
        return mementoTakeCare.getMementoList();
    }
}
