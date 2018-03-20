package com.endsound.pagination;


import com.endsound.pagination.bean.Page;
import com.endsound.pagination.bean.PageParam;
import com.endsound.pagination.exception.FieldNotFoundException;
import com.endsound.pagination.exception.LimitRequireException;
import com.endsound.pagination.exception.OffsetRequireException;
import com.endsound.pagination.exception.SortFieldException;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.lambda.Unchecked;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class PDSLContext implements SelectStep{
    private Configuration configuration;
    private PageParam pageParam;
    private Field[] fields;
    private SortField[] sortFields;
    private Table table;
    private Condition condition;


    public PDSLContext(Configuration configuration, PageParam pageParam) {
        this.configuration = configuration;
        this.pageParam = pageParam;

        condition = DSL.trueCondition();
    }

    @Override
    public WhereStep selectFrom(Table table) {
        return select(table.fields())
                .from(table);
    }

    @Override
    public FromStep select(Field... fields) {
        this.fields = fields;
        return this;
    }

    @Override
    public WhereStep from(Table table) {
        this.table = table;
        return this;
    }

    @Override
    public <T> OrderByStep query(Class<T> clazz) {
        return query(clazz, null);
    }

    @Override
    public <T> OrderByStep query(Class<T> clazz, Function<T, T> handler) {
        if(Objects.isNull(handler))
            handler = (queryObject) -> queryObject;


        condition = pageParam.getQueryObject(clazz)
                .map(handler::apply)
                .map(Unchecked.function(queryObject -> PaginationUtil.genCondition(queryObject, table)))
                .orElse(DSL.trueCondition());

        return this;
    }

    @Override
    public FetchStep orderBy(SortField... sortFields) {
        this.sortFields = sortFields;
        return this;
    }

    @Override
    public <T> Page<T> fetchInto(Class<T> clazz) {
        Integer offset = Optional.ofNullable(pageParam.getOffset()).orElseThrow(() -> new OffsetRequireException());
        Integer limit = Optional.ofNullable(pageParam.getLimit()).orElseThrow(() -> new LimitRequireException());

        Field[] fields = getRealFields();

        SortField[] sortFields = getRealSortFields();

        return new Page<T>(
                DSL.using(configuration)
                    .select(fields)
                    .from(table)
                    .where(condition)
                    .orderBy(sortFields)
                    .offset(offset)
                    .limit(limit)
                    .fetchInto(clazz),
                DSL.using(configuration)
                    .selectCount()
                    .from(table)
                    .where(condition)
                    .fetchOne(0, Long.TYPE)
        );
    }

    private Field[] getRealFields(){
        return Arrays.asList(this.fields).stream()
                .map(field ->
                        Optional.ofNullable(table.field(field.getName()))
                                .orElseThrow(() -> new FieldNotFoundException(field.getName()))
                )
                .toArray(size -> new Field[size]);
    }

    private SortField[] getRealSortFields(){
        return Optional.ofNullable(this.sortFields)
                .map(sfs -> Arrays.asList(sfs).stream()
                        .map(sf -> {
                            Field field = Optional.ofNullable(table.field(sf.getName()))
                                    .orElseThrow(() -> new FieldNotFoundException(sf.getName()));

                            switch(sf.getOrder()){
                                case ASC:
                                    return field.asc();
                                case DESC:
                                    return field.desc();
                                case DEFAULT:
                                    return field.desc();
                            }

                            throw new SortFieldException();
                        }).toArray(size -> new SortField[size])
                ).orElseGet(() -> Optional.ofNullable(pageParam.getSort())
                        .map(sort -> {
                            Field field = Optional.ofNullable(table.field(sort))
                                    .orElseThrow(() -> new FieldNotFoundException(sort));
                            String order = Optional.ofNullable(pageParam.getOrder())
                                    .orElse("asc");

                            switch (order){
                                case "asc":
                                    return field.asc();
                                case "desc":
                                    return field.desc();
                                default:
                                    return field.asc();
                            }
                        })
                        .map(sf -> new SortField[]{sf})
                        .orElse(new SortField[]{table.field(0).asc()})
                );
    }

}
