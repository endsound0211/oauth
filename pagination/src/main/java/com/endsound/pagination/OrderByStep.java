package com.endsound.pagination;


import org.jooq.SortField;

public interface OrderByStep extends FetchStep {
    public FetchStep orderBy(SortField... sortFields);
}
