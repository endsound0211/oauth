package com.endsound.security.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenErrorException extends AuthenticationException {
    public TokenErrorException(){
        super("Token錯誤, 請重新登入");
    }
}
