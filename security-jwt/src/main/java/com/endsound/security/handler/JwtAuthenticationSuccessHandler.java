package com.endsound.security.handler;

import com.endsound.security.JwtUtil;
import com.endsound.security.entity.JwtUserDetail;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
    private final static Logger logger = LogManager.getLogger(JwtAuthenticationSuccessHandler.class);
    @Autowired
    private JwtUtil jwtUtil;
    @Value("${jwt.token.header.key:authentication_token}")
    private String TOKEN_HEADER_KEY;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info(String.format("%s 登入成功", authentication.getPrincipal()));
        JwtUserDetail userDetail = (JwtUserDetail) authentication.getDetails();

        //TODO set audience
        //set jwt info
        userDetail.setSubject(userDetail.getUsername())
                .setIssuer(request.getServerName())
                .setAudience(request.getHeader("origin"));

        String jwtToken = jwtUtil.generateToken(userDetail);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put(TOKEN_HEADER_KEY, jwtToken);

        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        IOUtils.copy(new StringReader(new Gson().toJson(data)), response.getOutputStream(), "UTF-8");
    }
}
