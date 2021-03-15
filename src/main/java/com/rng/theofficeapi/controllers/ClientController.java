package com.rng.theofficeapi.controllers;

import com.rng.theofficeapi.dto.ClientDTO;
import com.rng.theofficeapi.entities.Client;
import com.rng.theofficeapi.services.ClientService;
import com.rng.theofficeapi.services.exceptions.LinesPerPageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Client>> findAll(){

        return ResponseEntity.ok(clientService.findAll());
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
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
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ClientDTO clientDTO){
        Client client = clientService.fromDTO(clientDTO);
        clientService.save(client);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri()).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
        Client client = clientService.fromDTO(clientDTO);
        clientService.update(id, client);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        clientService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}