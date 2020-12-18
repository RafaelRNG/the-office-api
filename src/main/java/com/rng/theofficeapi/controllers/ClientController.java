package com.rng.theofficeapi.controllers;

import com.rng.theofficeapi.entities.Client;
import com.rng.theofficeapi.services.ClientService;
import com.rng.theofficeapi.services.exceptions.LinesPerPageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> findAll(){

        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping(path = "/pagination")
    public ResponseEntity<Page<Client>> pagination(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "linesPerPage", defaultValue = "20")Integer linesPerPage,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "orderBy", defaultValue = "name") String orderBy){

        try {
            return ResponseEntity.ok(clientService.pagination(page, linesPerPage, direction, orderBy));
        } catch(IllegalArgumentException e) {
            throw new LinesPerPageException();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Client client){
        clientService.save(client);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri()).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Client client){
        clientService.update(id, client);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        clientService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}