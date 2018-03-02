package com.endsound.security.filter;


import com.endsound.security.JwtAuthenticationToken;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final static Logger logger = LogManager.getLogger(JwtAuthenticationFilter.class);

    @Value("${jwt.username.key:username}")
    private String USERNAME_KEY;
    @Value("${jwt.password.key:password}")
    private String PASSWORD_KEY;

    public JwtAuthenticationFilter() {
        super(new AntPathRequestMatcher("/jwt/login", "POST"));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = Optional.ofNullable(request.getParameter(USERNAME_KEY)).orElse("");
        String password = Optional.ofNullable(request.getParameter(PASSWORD_KEY)).orElse("");
        logger.info(String.format("%s 登入驗證", username));
        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(username, password));
    }



}
