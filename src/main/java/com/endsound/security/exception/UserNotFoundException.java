package com.endsound.security.exception;

import org.springframework.security.core.AuthenticationException;

public class UserNotFoundException extends AuthenticationException {

    public UserNotFoundException(){
        super("帳號密碼錯誤");
    }
}
