package com.miguel.database_sql.services;

import java.util.List;
import java.util.Optional;

import com.miguel.database_sql.domain.entities.BookEntity;

public interface BookService {

    BookEntity createUpdateBook(String isbn, BookEntity book);

    List<BookEntity> getBooks();

    Optional<BookEntity> findOne(String isbn);

    boolean isExists(String isbn);

}
