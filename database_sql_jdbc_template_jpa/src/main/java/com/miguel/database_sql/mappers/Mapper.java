package com.miguel.database_sql.mappers;

public interface Mapper<A, B> {
    B mapTo(A a);

    A mapFrom(B b);
}
