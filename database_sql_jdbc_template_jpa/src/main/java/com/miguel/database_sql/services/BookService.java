package com.miguel.database_sql.services;

import com.miguel.database_sql.domain.entities.BookEntity;

public interface BookService {

    BookEntity createBook(String isbn, BookEntity book);

}
