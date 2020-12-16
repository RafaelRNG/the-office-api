package com.rng.theofficeapi.services;

import com.rng.theofficeapi.entities.Payment;
import com.rng.theofficeapi.repositories.PaymentRepository;
import com.rng.theofficeapi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment findById(Long id){
        return paymentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found, ID: " + id));
    }
}