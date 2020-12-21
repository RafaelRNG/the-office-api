package com.rng.theofficeapi.controllers;

import com.rng.theofficeapi.entities.Salesman;
import com.rng.theofficeapi.services.SalesmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(path = "/salesmans")
public class SalesmanController {

    @Autowired
    private SalesmanService salesmanService;

    @GetMapping
    public ResponseEntity<List<Salesman>> findAll(){

        return ResponseEntity.ok(salesmanService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Salesman> findById(@PathVariable Long id){
        return ResponseEntity.ok(salesmanService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Salesman salesman){
        salesmanService.save(salesman);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(salesman.getId()).toUri()).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Salesman salesman){
        salesmanService.update(id, salesman);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        salesmanService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}