package com.rng.theofficeapi.services;

import com.rng.theofficeapi.dto.SalesmanDTO;
import com.rng.theofficeapi.entities.Salesman;
import com.rng.theofficeapi.repositories.OrderRepository;
import com.rng.theofficeapi.repositories.SalesmanRepository;
import com.rng.theofficeapi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesmanService {

    @Autowired
    private SalesmanRepository salesmanRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<SalesmanDTO> findAll(){
        List<Salesman> salesmen = salesmanRepository.findAll();

        return salesmen.stream().map(salesman -> {
            return new SalesmanDTO(salesman.getId(), salesman.getName(), salesman.getIdentification(), salesman.getOrders());
        }).collect(Collectors.toList());
    }

    public SalesmanDTO findById(Long id){

        Salesman salesman = salesmanRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException());

        return new SalesmanDTO(salesman.getId(), salesman.getName(), salesman.getIdentification(), salesman.getOrders());
    }

    public void save(Salesman salesman){

        salesmanRepository.save(salesman);
    }

    public void update(Long id, Salesman newSalesman){
        Salesman oldSalesman = salesmanRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException());
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

    public Salesman fromDTO(SalesmanDTO salesmanDTO){
        return new Salesman(salesmanDTO.getId(), salesmanDTO.getName(), salesmanDTO.getIdentification());
    }
}