package com.endsound.security.exception;

import org.springframework.security.core.AuthenticationException;

public class UserDisableException extends AuthenticationException {

    public UserDisableException() {
        super("帳號已失效");
    }
}
