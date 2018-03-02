package com.endsound.error.bean;

import java.sql.Timestamp;
import java.util.UUID;

public class InternalServerErrorBean {
    private UUID code;
    private Timestamp time;
    private String message;

    public UUID getCode() {
        return code;
    }

    public InternalServerErrorBean setCode(UUID code) {
        this.code = code;
        return this;
    }

    public Timestamp getTime() {
        return time;
    }

    public InternalServerErrorBean setTime(Timestamp time) {
        this.time = time;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public InternalServerErrorBean setMessage(String message) {
        this.message = message;
        return this;
    }
}
