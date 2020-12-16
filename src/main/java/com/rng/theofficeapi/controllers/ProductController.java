package com.rng.theofficeapi.controllers;

import com.rng.theofficeapi.entities.Product;
import com.rng.theofficeapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product){
        productService.save(product);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri()).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product){
        productService.update(id, product);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        productService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}