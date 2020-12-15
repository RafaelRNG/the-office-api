package com.rng.theofficeapi.service;

import com.rng.theofficeapi.entities.Category;
import com.rng.theofficeapi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findById(Long id){
        return categoryRepository.findById(id).get();
    }
}