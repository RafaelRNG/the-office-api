package com.rng.theofficeapi.controllers;

import com.rng.theofficeapi.entities.Category;
import com.rng.theofficeapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findById(id));
    }
}