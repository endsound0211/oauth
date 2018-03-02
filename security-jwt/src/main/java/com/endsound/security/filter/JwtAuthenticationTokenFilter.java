package com.endsound.security.filter;

import com.endsound.security.JwtAuthenticationToken;
import com.endsound.security.JwtUtil;
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
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Component
public class JwtAuthenticationTokenFilter extends GenericFilterBean {
    @Autowired
    private JwtUtil jwtUtil;
    @Value("${jwt.token.header.key:authentication_token}")
    private String TOKEN_HEADER_KEY;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        try {
            Optional.ofNullable(httpServletRequest.getHeader(TOKEN_HEADER_KEY))
                    .map(jwtToken -> jwtUtil.parseToken(jwtToken))
                    .ifPresent(userDetail -> {
                        //audience che
//                        if (!httpServletRequest.getHeader("origin").equals(userDetail.getAudience()))
//                            throw new TokenErrorException();
                        //set security context
                        JwtAuthenticationToken token = new JwtAuthenticationToken(
                                userDetail.getUsername(),
                                null,
                                userDetail.getAuthorities()
                        );
                        token.setDetails(userDetail);
                        SecurityContextHolder.getContext().setAuthentication(token);

                        //refresh token
                        Timestamp current = Timestamp.from(Instant.now());
                        if (current.after(userDetail.getRefresh())) {
                            httpServletResponse.setHeader(TOKEN_HEADER_KEY, jwtUtil.generateToken(userDetail));
                            httpServletResponse.setHeader("Access-Control-Expose-Headers", TOKEN_HEADER_KEY);
                        }
                    });
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException();
        } catch (SignatureException e) {
            throw new TokenErrorException();
        } catch (IllegalArgumentException e) {
            throw new TokenErrorException();
        }

        chain.doFilter(request, response);
    }

}
