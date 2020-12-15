package com.rng.theofficeapi.services;

import com.rng.theofficeapi.entities.Salesman;
import com.rng.theofficeapi.repositories.SalesmanRepository;
import com.rng.theofficeapi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesmanService {

    @Autowired
    private SalesmanRepository salesmanRepository;

    public Salesman findById(Long id){
        return salesmanRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found, ID: " + id));
    }
}