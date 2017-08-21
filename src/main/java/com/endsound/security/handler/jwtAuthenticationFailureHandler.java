package com.endsound.security.handler;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Component
public class jwtAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final static Logger logger = LogManager.getLogger(jwtAuthenticationFailureHandler.class);

    @Value("${jwt.username.key:username}")
    private String USERNAME_KEY;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info(String.format("%s 登入失敗:%s",
                Optional.ofNullable(request.getParameter(USERNAME_KEY)).orElse(""),
                exception.getMessage()));


        Map<String, Object> body = new HashMap<String, Object>();
        body.put("message", exception.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().print(new Gson().toJson(body));
        response.flushBuffer();
    }
}
