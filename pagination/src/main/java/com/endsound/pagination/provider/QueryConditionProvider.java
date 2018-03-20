package com.endsound.pagination.provider;

import com.endsound.pagination.bean.QueryParam;
import org.jooq.Condition;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public interface QueryConditionProvider {

    public Condition genCondition(QueryParam queryParam);

    public Boolean support(Annotation annotation, Field field);
}
