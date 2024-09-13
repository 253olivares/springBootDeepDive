package com.miguel.database_sql.services.impl;

import org.springframework.stereotype.Service;

import com.miguel.database_sql.domain.entities.BookEntity;
import com.miguel.database_sql.repositories.BookRepository;
import com.miguel.database_sql.services.BookService;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity book) {

        book.setIsbn(isbn);

        return bookRepository.save(book);
    }

}
