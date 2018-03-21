package com.endsound.pagination.provider;

import com.endsound.pagination.annotation.query.Ne;
import com.endsound.pagination.bean.QueryParam;
import org.jooq.Condition;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class NeProvider implements QueryConditionProvider {
    @Override
    public Condition genCondition(QueryParam queryParam) {
        return queryParam.getField().ne(queryParam.getData());
    }

    @Override
    public Boolean support(Annotation annotation, Field field) {
        return annotation instanceof Ne;
    }
}
