package com.payment.controller;


import com.payment.auth.HeaderUtil;
import com.payment.model.PaymentRequest;
import com.payment.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping("/token")
    public String getPayment(){
        return HeaderUtil.getToken();
    }

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest paymentRequest){
        boolean isProcess = true;
//        String s = paymentService.handlePaymentLifecycle(paymentRequest);
//        System.out.println(s);
        System.out.println("This is processPayment");
        return isProcess ?
                ResponseEntity.ok("Payment Service Successfully"):
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Order Failed");
    }
    @PutMapping("{transactionId}/complete")
    public ResponseEntity<String> completePayment(@PathVariable int transactionId){
        boolean isComplete = paymentService.completePayment(transactionId);
        return ResponseEntity.ok("Complete payment");
    }
    @PutMapping("{transactionId}/fail")
    public ResponseEntity<String> failPayment(@PathVariable int transactionId){
        boolean isFail = paymentService.failPayment(transactionId);
        return ResponseEntity.ok("Fail payment");
    }

    @PostMapping("{transactionId}/refund")
    public ResponseEntity<String> refundPayment(@PathVariable int transactionId){
        boolean isRefund = paymentService.refundPayment(transactionId);
        return isRefund ?
                ResponseEntity.ok("Payment Refund Successful") :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment Refund Failed");
    }
}
