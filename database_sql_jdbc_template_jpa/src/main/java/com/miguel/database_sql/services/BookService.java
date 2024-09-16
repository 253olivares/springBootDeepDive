package com.miguel.database_sql.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.miguel.database_sql.domain.dto.BookDto;
import com.miguel.database_sql.domain.entities.BookEntity;

public interface BookService {

    BookDto createUpdateBook(String isbn, BookDto book);

    List<BookDto> findAll();

    Page<BookDto> findAll(Pageable pageable);

    Optional<BookDto> findOne(String isbn);

    boolean isExists(String isbn);

    BookDto partialUpdateBook(String isbn, BookDto bookDto);

    void delete(String isbn);

}
