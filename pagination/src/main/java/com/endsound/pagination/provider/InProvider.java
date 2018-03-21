package com.endsound.pagination.provider;

import com.endsound.pagination.annotation.query.In;
import com.endsound.pagination.bean.QueryParam;
import org.jooq.Condition;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;

public class InProvider implements QueryConditionProvider {

    @Override
    public Condition genCondition(QueryParam queryParam) {
        return queryParam.getField().in(queryParam.getData());
    }

    @Override
    public Boolean support(Annotation annotation, Field field) {
        return annotation instanceof In && ClassUtils.isAssignable(Collection.class,field.getType());
    }
}
