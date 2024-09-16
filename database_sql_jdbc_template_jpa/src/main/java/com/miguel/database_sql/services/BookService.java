package com.miguel.database_sql.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.miguel.database_sql.domain.entities.BookEntity;

public interface BookService {

    BookEntity createUpdateBook(String isbn, BookEntity book);

    List<BookEntity> findAll();

    Page<BookEntity> findAll(Pageable pageable);

    Optional<BookEntity> findOne(String isbn);

    boolean isExists(String isbn);

    BookEntity partialUpdateBook(String isbn, BookEntity bookEntity);

    void delete(String isbn);

}
