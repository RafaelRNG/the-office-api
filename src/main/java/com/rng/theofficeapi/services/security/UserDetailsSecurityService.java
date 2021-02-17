package com.rng.theofficeapi.services.security;

import com.rng.theofficeapi.entities.Client;
import com.rng.theofficeapi.repositories.ClientRepository;
import com.rng.theofficeapi.security.UserDetailsSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsSecurityService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(email);

        if(client == null) {
            throw new UsernameNotFoundException(email);
        }

        return new UserDetailsSecurity(client.getId(), client.getEmail(), client.getPassword(), client.getProfiles());
    }
}
