package com.rng.theofficeapi.controllers;

import com.rng.theofficeapi.entities.Category;
import com.rng.theofficeapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){

        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){

        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Category category){
        categoryService.save(category);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri()).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Category category){
        categoryService.update(id, category);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        categoryService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}