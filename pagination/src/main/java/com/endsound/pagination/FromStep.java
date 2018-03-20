package com.endsound.pagination;

import org.jooq.Table;

public interface FromStep extends WhereStep{
    public WhereStep from(Table table);

}
