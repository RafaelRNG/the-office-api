package com.rng.theofficeapi.services;

import com.rng.theofficeapi.dto.ClientDTO;
import com.rng.theofficeapi.entities.Client;
import com.rng.theofficeapi.entities.enums.Profiles;
import com.rng.theofficeapi.repositories.ClientRepository;
import com.rng.theofficeapi.security.UserDetailsSecurity;
import com.rng.theofficeapi.services.exceptions.AuthorizationException;
import com.rng.theofficeapi.services.exceptions.ObjectNotFoundException;
import com.rng.theofficeapi.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Client> findAll(){

        return clientRepository.findAll();
    }

    public Page<Client> pagination(Integer page, Integer linesPerPage, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

        return clientRepository.findAll(pageRequest);
    }

    public ClientDTO findById(Long id){

        UserDetailsSecurity user = UserService.authenticated();
        if(user == null || !user.hasRole(Profiles.ROLE_ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado!");
        }

        Client client = clientRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException());

        ClientDTO clientDTO = new ClientDTO(client.getId(), client.getName(), client.getCnpj(), client.getEmail(), client.getCellPhone(), client.getAddress());
        clientDTO.setOrders(client.getOrders());

        return clientDTO;
    }

    public void save(Client client) {

        clientRepository.save(client);
    }

    public void update(Long id, Client newClient){
        ClientDTO oldClientDTO = this.findById(id);
        newClient.setId(oldClientDTO.getId());
        newClient.setAddress(oldClientDTO.getAddress());
        newClient.setEmail(oldClientDTO.getEmail());

        clientRepository.save(newClient);
    }

    public void deleteById(Long id){

        clientRepository.deleteById(id);
    }

    public Client fromDTO(ClientDTO clientDTO) {
        Client client = new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getCnpj(), clientDTO.getEmail(), clientDTO.getCellPhone(), bCryptPasswordEncoder.encode(clientDTO.getPassword()));
        client.setAddress(clientDTO.getAddress());

        return client;
    }
}