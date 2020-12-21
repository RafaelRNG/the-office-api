package com.rng.theofficeapi.services;

import com.rng.theofficeapi.entities.Salesman;
import com.rng.theofficeapi.repositories.SalesmanRepository;
import com.rng.theofficeapi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesmanService {

    @Autowired
    private SalesmanRepository salesmanRepository;

    public List<Salesman> findAll(){

        return salesmanRepository.findAll();
    }

    public Salesman findById(Long id){
        return salesmanRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException());
    }

    public void save(Salesman salesman){

        salesmanRepository.save(salesman);
    }

    public void update(Long id, Salesman newSalesman){
        Salesman oldSalesman = this.findById(id);
        newSalesman.setId(oldSalesman.getId());

        salesmanRepository.save(newSalesman);
    }

    public void deleteById(Long id){
        try{
            salesmanRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ObjectNotFoundException();
        }
    }
}