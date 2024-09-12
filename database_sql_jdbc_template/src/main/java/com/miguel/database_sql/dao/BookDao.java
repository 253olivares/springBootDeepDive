package com.miguel.database_sql.dao;

import java.util.Optional;

import com.miguel.database_sql.domain.Book;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String isbn);
}
