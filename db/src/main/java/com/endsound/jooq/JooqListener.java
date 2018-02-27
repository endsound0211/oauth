package com.endsound.jooq;

import org.jooq.ExecuteContext;
import org.jooq.impl.DefaultExecuteListener;

public class JooqListener extends DefaultExecuteListener {
    @Override
    public void exception(ExecuteContext ctx){
        ctx.exception(new RuntimeException("database error", ctx.exception()));
    }
}
