package com.endsound.pagination;

import org.jooq.Field;
import org.jooq.Table;

public interface SelectStep extends FromStep{
    public WhereStep selectFrom(Table table);

    public FromStep select(Field ...fields);
}
