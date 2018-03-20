package com.endsound.pagination.exception;

public class LimitRequireException extends RuntimeException {
    public LimitRequireException() {
        super("Need Limit Params");
    }
}
