package com.rng.theofficeapi.controllers;

import com.rng.theofficeapi.security.JWTUtil;
import com.rng.theofficeapi.security.UserDetailsSecurity;
import com.rng.theofficeapi.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/auth")
public class AuthResource {

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping(path = "/refresh_token")
    public ResponseEntity<?> refreshToken(HttpServletResponse response) {
        UserDetailsSecurity user = UserService.authenticated();
        String token = jwtUtil.generatorToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }
}
