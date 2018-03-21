package com.endsound.pagination.provider;

import com.endsound.pagination.annotation.query.NotLike;
import com.endsound.pagination.bean.QueryParam;
import org.jooq.Condition;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class NotLikeProvider implements QueryConditionProvider {
    @Override
    public Condition genCondition(QueryParam queryParam) {
        return queryParam.getField().notLike("%" + queryParam.getData() + "%");
    }

    @Override
    public Boolean support(Annotation annotation, Field field) {
        return annotation instanceof NotLike && ClassUtils.isAssignable(String.class,field.getType());

    }
}
