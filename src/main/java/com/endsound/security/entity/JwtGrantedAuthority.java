package com.endsound.security.entity;

import org.springframework.security.core.GrantedAuthority;

public class JwtGrantedAuthority implements GrantedAuthority {

    private final String role;

    public JwtGrantedAuthority(String role){
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
