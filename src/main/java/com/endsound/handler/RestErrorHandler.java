package com.endsound.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.sasl.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestErrorHandler {

//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException e){
//        Map errorContent = new HashMap<String, Object>();
//        errorContent.put("message", e.getMessage());
//        return new ResponseEntity<Object>(errorContent, HttpStatus.UNAUTHORIZED);
//    }
}
