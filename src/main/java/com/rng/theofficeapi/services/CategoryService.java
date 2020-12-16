package com.rng.theofficeapi.service;

import com.rng.theofficeapi.entities.Category;
import com.rng.theofficeapi.repositories.CategoryRepository;
import com.rng.theofficeapi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(Long id){
        return categoryRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException());
    }

    public void save(Category category){
        categoryRepository.save(category);
    }

    public void update(Long id, Category newCategory){
        Category oldCategory = this.findById(id);
        newCategory.setId(oldCategory.getId());

        categoryRepository.save(newCategory);
    }

    public void deleteById(Long id){
        try{
            categoryRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e){
            throw new ObjectNotFoundException();
        }
    }
}