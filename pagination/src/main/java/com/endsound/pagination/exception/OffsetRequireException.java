package com.endsound.pagination.exception;

public class OffsetRequireException extends RuntimeException {
    public OffsetRequireException() {
        super("Need Offset Param");
    }
}
