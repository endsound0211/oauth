package com.endsound.jooq;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.ExecuteContext;
import org.jooq.impl.DefaultExecuteListener;

public class JooqListener extends DefaultExecuteListener {
    private final static Logger logger = LogManager.getLogger(JooqListener.class);

    @Override
    public void executeStart(ExecuteContext ctx){
        logger.debug(ctx.sql());
    }

    @Override
    public void exception(ExecuteContext ctx){
        ctx.exception(new RuntimeException("database error", ctx.exception()));
    }
}
