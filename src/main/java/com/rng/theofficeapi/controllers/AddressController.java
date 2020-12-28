package com.rng.theofficeapi.controllers;

import com.rng.theofficeapi.dto.AddressDTO;
import com.rng.theofficeapi.entities.Address;
import com.rng.theofficeapi.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/adresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> findAll(){
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Address> findById(@PathVariable Long id){
        return ResponseEntity.ok(addressService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody AddressDTO addressDTO){
        Address address = addressService.fromDTO(addressDTO);
        addressService.save(address);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(address.getId()).toUri()).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody AddressDTO addressDTO){
        Address address = addressService.fromDTO(addressDTO);
        addressService.update(id, address);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        addressService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
