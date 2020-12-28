package com.rng.theofficeapi.services;

import com.rng.theofficeapi.entities.Address;
import com.rng.theofficeapi.repositories.AddressRepository;
import com.rng.theofficeapi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public Address findById(Long id){
        return addressRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found, ID: " + id));
    }

    public void save(Address address){
        addressRepository.save(address);
    }

    public void update(Long id, Address newAddress){
        Address oldAddress = this.findById(id);
        newAddress.setId(oldAddress.getId());

        addressRepository.save(newAddress);
    }

    public void deleteById(Long id){
        try{
            addressRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ObjectNotFoundException();
        }
    }
}