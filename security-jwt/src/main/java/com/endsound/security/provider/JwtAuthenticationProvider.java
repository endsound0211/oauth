package com.endsound.security.provider;

import com.endsound.security.JwtAuthenticationToken;
import com.endsound.security.entity.JwtUserDetail;
import com.endsound.security.exception.UserDisableException;
import com.endsound.security.exception.UserNotFoundException;
import com.endsound.security.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;


@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailService userDetailService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken token = (JwtAuthenticationToken)authentication;
        String username = token.getPrincipal().toString();
        //md5
        String password = "";
        try {
            password = Base64.getEncoder().encodeToString(MessageDigest.getInstance("MD5").digest(token.getCredentials().toString().getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new UserNotFoundException();
        }

        //authenticate
        JwtUserDetail userDetail = Optional.ofNullable(userDetailService.loadUserByUsername(username))
                .orElseThrow(() -> new UserNotFoundException());

        if(!userDetail.isEnabled() || !userDetail.isAccountNonExpired() || !userDetail.isAccountNonLocked())
            throw new UserDisableException();

        if(!userDetail.getPassword().equals(password))
            throw new UserNotFoundException();



        JwtAuthenticationToken authenticatedToken = new JwtAuthenticationToken(username, password, userDetail.getAuthorities());
        authenticatedToken.setDetails(userDetail);
        return authenticatedToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}
