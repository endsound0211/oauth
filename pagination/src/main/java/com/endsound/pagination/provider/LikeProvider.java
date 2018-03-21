package com.endsound.pagination.provider;

import com.endsound.pagination.annotation.query.Like;
import com.endsound.pagination.bean.QueryParam;
import org.jooq.Condition;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class LikeProvider implements QueryConditionProvider {
    @Override
    public Condition genCondition(QueryParam queryParam) {
        return queryParam.getField().like("%" + queryParam.getData().toString() + "%");
    }

    @Override
    public Boolean support(Annotation annotation, Field field) {
        return annotation instanceof Like && ClassUtils.isAssignable(String.class,field.getType());
    }
}
