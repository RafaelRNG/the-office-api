package com.rng.theofficeapi.security;

import com.rng.theofficeapi.entities.enums.Profiles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailsSecurity implements UserDetails {

    private Long id;
    private String email;
    private String password;

    private Collection<? extends GrantedAuthority> getAuthorities;

    public UserDetailsSecurity(Long id, String email, String password, Set<Profiles> profiles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.getAuthorities = profiles.stream().map(profile -> new SimpleGrantedAuthority(profile.getTitle())).collect(Collectors.toList());
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(Profiles profiles) {
        return this.getAuthorities().contains(new SimpleGrantedAuthority(profiles.getTitle()));
    }
}
