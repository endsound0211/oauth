package com.endsound.pagination.exception;

public class PageQueryPolicyNotSetException extends RuntimeException {
    public PageQueryPolicyNotSetException() {
        super("@PageQuery annotation must set to query class");
    }
}
