package com.endsound.security.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenExpiredException extends AuthenticationException {
    public TokenExpiredException(){
        super("認證已失效, 請重新登入");
    }
}
