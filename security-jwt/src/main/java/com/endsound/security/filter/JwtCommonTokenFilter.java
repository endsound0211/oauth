package com.endsound.security.filter;

import com.endsound.security.JwtAuthenticationToken;
import com.endsound.security.JwtUtil;
import com.endsound.security.entity.JwtGrantedAuthority;
import com.endsound.security.exception.TokenErrorException;
import com.endsound.security.exception.TokenExpiredException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtCommonTokenFilter extends GenericFilterBean {
    @Autowired
    private JwtUtil jwtUtil;
    @Value("${jwt.common.token.header.key:common_token}")
    private String TOKEN_HEADER_KEY;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;

        try{
            Optional.ofNullable(httpServletRequest.getHeader(TOKEN_HEADER_KEY))
                    .map(jwtUtil::parseCommonToken)
                    .ifPresent(claims -> {
                        List<JwtGrantedAuthority> roles = ((List<String>)claims.get("roles")).parallelStream()
                                .map(role -> new JwtGrantedAuthority(role))
                                .collect(Collectors.toList());

                        JwtAuthenticationToken token = new JwtAuthenticationToken(null, null, roles);
                        SecurityContextHolder.getContext().setAuthentication(token);
                    });
        }catch (ExpiredJwtException e){
            throw new TokenExpiredException();
        }catch (SignatureException e){
            throw new TokenErrorException();
        }catch (IllegalArgumentException e){
            throw new TokenErrorException();
        }

        chain.doFilter(request, response);
    }
}
