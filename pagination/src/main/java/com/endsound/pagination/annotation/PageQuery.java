package com.endsound.pagination.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PageQuery {
    PageQueryPolicy sameGroupPolicy() default PageQueryPolicy.AND;
    PageQueryPolicy diffGroupPolicy() default PageQueryPolicy.OR;
}
