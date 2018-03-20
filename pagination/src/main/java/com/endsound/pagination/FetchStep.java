package com.endsound.pagination;

import com.endsound.pagination.bean.Page;


public interface FetchStep {
    public <T> Page<T> fetchInto(Class<T> clazz);
}
