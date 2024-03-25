package com.prajwal.FoodApp.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentTestController {

    @Autowired
    PaymentService paymentService;
    @GetMapping("/{amount}")
    public ResponseEntity<TransactionDetails> doPayment(@PathVariable Long amount) throws Exception {

        TransactionDetails transactionDetails= paymentService.createTransaction(amount);

        return new ResponseEntity<>(transactionDetails, HttpStatus.CREATED);


    }
}
