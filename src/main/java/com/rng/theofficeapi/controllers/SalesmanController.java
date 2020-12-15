package com.rng.theofficeapi.controllers;

import com.rng.theofficeapi.entities.Salesman;
import com.rng.theofficeapi.services.SalesmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/salesmans")
public class SalesmanController {

    @Autowired
    private SalesmanService salesmanService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Salesman> findById(@PathVariable Long id){
        return ResponseEntity.ok(salesmanService.findById(id));
    }
}