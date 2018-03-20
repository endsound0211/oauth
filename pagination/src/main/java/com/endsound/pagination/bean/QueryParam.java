package com.endsound.pagination.bean;

import org.jooq.Field;
import org.jooq.Table;

public class QueryParam {
    private Integer group;
    private Table table;
    private Field field;
    private Object data;

    public Integer getGroup() {
        return group;
    }

    public QueryParam setGroup(Integer group) {
        this.group = group;
        return this;
    }

    public Table getTable() {
        return table;
    }

    public QueryParam setTable(Table table) {
        this.table = table;
        return this;
    }

    public Field getField() {
        return field;
    }

    public QueryParam setField(Field field) {
        this.field = field;
        return this;
    }

    public Object getData() {
        return data;
    }

    public QueryParam setData(Object data) {
        this.data = data;
        return this;
    }
}
