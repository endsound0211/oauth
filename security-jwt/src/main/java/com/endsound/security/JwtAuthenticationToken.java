package com.endsound.security;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {


    public JwtAuthenticationToken(String principal, String credentials){
        super(principal, credentials);
    }

    public JwtAuthenticationToken(String principal, String credentials, List<? extends GrantedAuthority> authorities){
        super(principal, credentials, authorities);
    }

}
