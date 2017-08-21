package com.example;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/backend/api")
public class BackendExample {
    @PreAuthorize("hasRole('ROLE_BACKEND')")
    @GetMapping("")
    public Map<String, Object> Hello(){
        Map<String, Object> ob= new HashMap<String, Object>();
        ob.put("hello", "backend");

        return ob;
    }
}
