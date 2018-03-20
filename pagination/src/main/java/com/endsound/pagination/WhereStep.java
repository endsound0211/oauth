package com.endsound.pagination;

import java.util.function.Function;

public interface WhereStep extends OrderByStep{

    public <T> OrderByStep query(Class<T> clazz);

    public <T> OrderByStep query(Class<T> clazz, Function<T, T> handler);
}
