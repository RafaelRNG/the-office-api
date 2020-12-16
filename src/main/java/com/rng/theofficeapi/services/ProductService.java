package com.rng.theofficeapi.services;

import com.rng.theofficeapi.entities.Product;
import com.rng.theofficeapi.repositories.ProductRepository;
import com.rng.theofficeapi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){

        return productRepository.findAll();
    }

    public Page<Product> pagination(Integer page, Integer linesPerPage, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

        return productRepository.findAll(pageRequest);
    }

    public Product findById(Long id){

        return productRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException());
    }

    public void save(Product product){

        productRepository.save(product);
    }

    public void update(Long id, Product newProduct){
        Product oldProduct = this.findById(id);
        newProduct.setId(oldProduct.getId());

        productRepository.save(newProduct);
    }

    public void deleteById(Long id){
        try{
            productRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e){
            throw new ObjectNotFoundException();
        }
    }
}