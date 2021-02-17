package com.rng.theofficeapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rng.theofficeapi.dto.CredentialsDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JWTUtil jwtUtil;

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManagerm, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManagerm;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            CredentialsDTO credentialsDTO = new ObjectMapper().readValue(request.getInputStream(), CredentialsDTO.class);

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentialsDTO.getEmail(), credentialsDTO.getPassword(), new ArrayList<>());
            return authenticationManager.authenticate(token);

        }catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String username = ((UserDetailsSecurity) authResult.getPrincipal()).getUsername();
        String token = jwtUtil.generatorToken(username);
        response.addHeader("Authorization", "Bearer " + token);
    }
}
