package com.rng.theofficeapi.services.security;

import com.rng.theofficeapi.security.UserDetailsSecurity;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserDetailsSecurity authenticated() {
        try {
            return (UserDetailsSecurity) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}