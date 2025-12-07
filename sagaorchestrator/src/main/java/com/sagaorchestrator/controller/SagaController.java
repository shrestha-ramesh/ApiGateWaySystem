package com.sagaorchestrator.controller;

import com.sagaorchestrator.model.Delivery.DeliveryDetail;
import com.sagaorchestrator.model.SagaDetail;
import com.sagaorchestrator.model.order.OrderDetail;
import com.sagaorchestrator.model.payment.PaymentDetail;
import com.sagaorchestrator.service.SagaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saga")
public class SagaController {

    private final SagaService sagaService;

    public SagaController(SagaService sagaService) {
        this.sagaService = sagaService;
    }

    @GetMapping("/token")
    public String getToken(){
        System.out.println("This is token");
        return "HeaderUtil.getToken()";
    }
    @PostMapping("/start")
    public ResponseEntity<SagaDetail> startSaga(@RequestBody SagaDetail sagaDetail){
        sagaDetail.setPaymentDetail(PaymentDetail.builder().amount(sagaDetail.getOrderDetail().getTotalAmount()).build());
        sagaDetail.setDeliveryDetail(DeliveryDetail.builder().deliveryId(100).build());
        SagaDetail sagaDetail1 = sagaService.startSaga(sagaDetail);
        return new ResponseEntity<>(sagaDetail1, HttpStatus.ACCEPTED);
    }
}
