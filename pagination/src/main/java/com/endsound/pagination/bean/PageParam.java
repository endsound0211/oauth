package com.endsound.pagination.bean;

import com.endsound.pagination.adapter.TimestampAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import java.util.Date;
import java.util.Optional;

public class PageParam {
    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(Date.class, new TimestampAdapter()).create();

    private Integer limit;
    private Integer offset;
    private String order;
    private String sort;
    private String query;


    public <T> Optional<T> getQueryObject(Class<T> clazz){
        if(StringUtils.isNotBlank(query))
            return Optional.of(GSON.fromJson(query, clazz));

        return Optional.empty();
    }

    public String getQuery() {
        return query;
    }

    public PageParam setQuery(String query) {
        this.query = query;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public PageParam setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public PageParam setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public String getOrder() {
        return order;
    }

    public PageParam setOrder(String order) {
        this.order = order;
        return this;
    }

    public String getSort() {
        return sort;
    }

    public PageParam setSort(String sort) {
        this.sort = sort;
        return this;
    }
}
