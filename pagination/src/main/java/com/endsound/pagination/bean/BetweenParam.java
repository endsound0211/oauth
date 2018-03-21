package com.endsound.pagination.bean;

public class BetweenParam<T> {
    private T start;
    private T end;

    public T getStart() {
        return start;
    }

    public BetweenParam setStart(T start) {
        this.start = start;
        return this;
    }

    public T getEnd() {
        return end;
    }

    public BetweenParam setEnd(T end) {
        this.end = end;
        return this;
    }
}
