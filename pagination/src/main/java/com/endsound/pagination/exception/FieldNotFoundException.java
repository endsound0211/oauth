package com.endsound.pagination.exception;

public class FieldNotFoundException extends RuntimeException {
    public FieldNotFoundException(String fieldName){
        super(String.format("Field: %s Not Found in table", fieldName));
    }
}
