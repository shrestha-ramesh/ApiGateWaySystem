package com.orders;

import com.orders.auth.HeaderUtil;
import com.orders.controller.OrderController;
import com.orders.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTests {

    MockMvc mockMvc;

    @Mock
    OrderService orderService;

    @InjectMocks
    OrderController orderController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(orderController).build();
        HeaderUtil.clear();
    }

    @Test
    void testGetToken() throws Exception {
        String testToken = "test-token-123";
        HeaderUtil.setToken(testToken);
        mockMvc.perform(get("/order/token"))
                .andExpect(status().isOk())
                .andExpect(content().string(testToken));
    }

    @Test
    void testNullToken() throws Exception{
        mockMvc.perform(get("/order/token"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
