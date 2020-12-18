package com.rng.theofficeapi.services;

import com.rng.theofficeapi.entities.Client;
import com.rng.theofficeapi.repositories.ClientRepository;
import com.rng.theofficeapi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll(){

        return clientRepository.findAll();
    }

    public Page<Client> pagination(Integer page, Integer linesPerPage, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

        return clientRepository.findAll(pageRequest);
    }

    public Client findById(Long id){

        return clientRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found, ID: " + id));
    }

    public void save(Client client) {

        clientRepository.save(client);
    }

    public void update(Long id, Client newClient){
        Client oldClient = this.findById(id);
        newClient.setId(oldClient.getId());

        clientRepository.save(newClient);
    }

    public void deleteById(Long id){

        clientRepository.deleteById(id);
    }
}