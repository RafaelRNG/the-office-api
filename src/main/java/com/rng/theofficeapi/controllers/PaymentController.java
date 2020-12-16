package com.rng.theofficeapi.controllers;

import com.rng.theofficeapi.entities.Payment;
import com.rng.theofficeapi.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Payment> findById(@PathVariable Long id){
        return ResponseEntity.ok(paymentService.findById(id));
    }
}