package com.endsound.pagination.provider;

import com.endsound.pagination.annotation.query.Between;
import com.endsound.pagination.bean.BetweenParam;
import com.endsound.pagination.bean.QueryParam;
import org.jooq.Condition;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BetweenProvider implements QueryConditionProvider {
    @Override
    public Condition genCondition(QueryParam queryParam) {
        BetweenParam betweenParam = (BetweenParam) queryParam.getData();

        return queryParam.getField().between(betweenParam.getStart(), betweenParam.getEnd());
    }

    @Override
    public Boolean support(Annotation annotation, Field field) {
        return annotation instanceof Between && ClassUtils.isAssignable(BetweenParam.class,field.getType());
    }
}
