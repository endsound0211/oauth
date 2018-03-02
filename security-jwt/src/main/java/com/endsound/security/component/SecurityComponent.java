package com.endsound.security.component;

import com.endsound.security.entity.JwtUserDetail;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityComponent {
    public JwtUserDetail getUserDetail(){
        return (JwtUserDetail) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }
}
