package com.products;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.products.controller.ProductController;
import com.products.model.Product;
import com.products.model.Products;
import com.products.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest {
    private MockMvc mockMvc;
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    private MementoTakeCare mementoTakeCare;

    private Products products = new Products();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        Product product = new Product();
        product.setId(1); product.setName("Test Product");
        List<Product> productList = new ArrayList<>();
        productList.add(product);
       products.setProducts(productList);
    }
    @Test
    void testGetToken() throws Exception {
        mockMvc.perform(get("/product/token"))
                .andExpect(status().isOk())
                .andExpect(content().string("HeaderUtil.getToken()"));
    }
    @Test
    void testFindAllProduct() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        when(productService.findAllProduct()).thenReturn(products);

        mockMvc.perform(get("/product/findAll"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(products)));
    }

}
