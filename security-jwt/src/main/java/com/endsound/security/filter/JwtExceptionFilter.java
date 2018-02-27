package com.endsound.security.filter;

import com.endsound.security.entryPoint.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtExceptionFilter extends GenericFilterBean {
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try{
            chain.doFilter(request, response);
        }catch (AuthenticationException exception){
            jwtAuthenticationEntryPoint.commence((HttpServletRequest) request, (HttpServletResponse) response, exception);
        }
    }
}
