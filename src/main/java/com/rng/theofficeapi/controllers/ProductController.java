package com.rng.theofficeapi.controllers;

import com.rng.theofficeapi.dto.ProductDTO;
import com.rng.theofficeapi.entities.Product;
import com.rng.theofficeapi.services.ProductService;
import com.rng.theofficeapi.services.exceptions.LinesPerPageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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

    @GetMapping(path = "/pagination")
    public ResponseEntity<Page<Product>> pagination(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "linesPerPage", defaultValue = "20") Integer linesPerPage,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "orderBy", defaultValue = "name") String orderBy){

        try{
            return ResponseEntity.ok(productService.pagination(page, linesPerPage, direction, orderBy));
        } catch (IllegalArgumentException e){
            throw new LinesPerPageException();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){

        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ProductDTO productDTO){
        Product product = productService.fromDTO(productDTO);
        productService.save(product);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri()).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO){
        Product product = productService.fromDTO(productDTO);
        productService.update(id, product);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        productService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}