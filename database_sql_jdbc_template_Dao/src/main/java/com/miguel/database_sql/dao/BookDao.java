package com.miguel.database_sql.dao;

import java.util.List;
import java.util.Optional;

import com.miguel.database_sql.domain.Book;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String isbn);

    List<Book> findAll();

    void update(String isbn, Book book);

    void delete(String isbn);
}
