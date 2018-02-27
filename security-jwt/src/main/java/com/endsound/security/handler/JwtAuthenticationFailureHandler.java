package com.endsound.security.handler;

import com.endsound.security.entryPoint.JwtAuthenticationEntryPoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@Component
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final static Logger logger = LogManager.getLogger(JwtAuthenticationFailureHandler.class);

    @Value("${jwt.username.key:username}")
    private String USERNAME_KEY;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info(String.format("%s 登入失敗:%s",
                Optional.ofNullable(request.getParameter(USERNAME_KEY)).orElse(""),
                exception.getMessage()));

        jwtAuthenticationEntryPoint.commence(request, response, exception);
    }
}
