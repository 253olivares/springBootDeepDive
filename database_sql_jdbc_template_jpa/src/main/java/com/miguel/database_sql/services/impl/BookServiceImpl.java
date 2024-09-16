package com.miguel.database_sql.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public BookEntity createUpdateBook(String isbn, BookEntity book) {

        book.setIsbn(isbn);

        return bookRepository.save(book);
    }

    @Override
    public List<BookEntity> findAll() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Page<BookEntity> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<BookEntity> findOne(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public boolean isExists(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public BookEntity partialUpdateBook(String isbn, BookEntity bookEntity) {

        bookEntity.setIsbn(isbn);

        BookEntity bookFound = bookRepository.findById(isbn).get();
        Optional.ofNullable(bookEntity.getTitle()).ifPresent(bookFound::setTitle);
        Optional.ofNullable(bookEntity.getAuthorEntity()).ifPresent(bookFound::setAuthorEntity);
        return bookRepository.save(bookFound);
    }

    @Override
    public void delete(String isbn) {
        bookRepository.deleteById(isbn);
    }

}
