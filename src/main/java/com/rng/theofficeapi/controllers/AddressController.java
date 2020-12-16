package com.rng.theofficeapi.controllers;

import com.rng.theofficeapi.entities.Address;
import com.rng.theofficeapi.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/adresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Address> findById(@PathVariable Long id){
        return ResponseEntity.ok(addressService.findById(id));
    }
}
