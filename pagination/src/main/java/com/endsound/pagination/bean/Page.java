package com.endsound.pagination.bean;

import java.util.List;

public class Page<T> {
    private List<T> rows;
    private Long total;

    public Page(List<T> rows, Long total) {
        this.rows = rows;
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public Page setRows(List<T> rows) {
        this.rows = rows;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    public Page setTotal(Long total) {
        this.total = total;
        return this;
    }
}
