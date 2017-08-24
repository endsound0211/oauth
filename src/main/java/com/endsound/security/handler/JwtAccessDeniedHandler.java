package com.endsound.security.handler;

import com.endsound.security.entity.JwtUserDetail;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler{
    private final static Logger logger = LogManager.getLogger(JwtAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("message", "無權限操作");

        JwtUserDetail userDetail = (JwtUserDetail) SecurityContextHolder.getContext().getAuthentication().getDetails();
        logger.error(String.format("403 - User: %s, IP: %s, Path: %s",
                userDetail.getName(),
                request.getRemoteAddr(),
                request.getServletPath()));

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().print(new Gson().toJson(body));
        response.flushBuffer();
    }
}
