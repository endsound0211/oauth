package com.endsound.error.bean;

public class ForbiddenErrorBean {
    private String message;

    public String getMessage() {
        return message;
    }

    public ForbiddenErrorBean setMessage(String message) {
        this.message = message;
        return this;
    }
}
