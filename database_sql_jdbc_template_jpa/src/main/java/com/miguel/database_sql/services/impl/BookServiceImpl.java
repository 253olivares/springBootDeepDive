package com.miguel.database_sql.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.miguel.database_sql.domain.dto.BookDto;
import com.miguel.database_sql.domain.entities.BookEntity;
import com.miguel.database_sql.mappers.Mapper;
import com.miguel.database_sql.repositories.BookRepository;
import com.miguel.database_sql.services.BookService;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private Mapper<BookEntity, BookDto> bookMapper;

    public BookServiceImpl(BookRepository bookRepository, Mapper<BookEntity, BookDto> bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto createUpdateBook(String isbn, BookDto book) {

        BookEntity bookEntity = bookMapper.mapFrom(book);
        bookEntity.setIsbn(isbn);
        BookEntity results = bookRepository.save(bookEntity);
        return bookMapper.mapTo(results);
    }

    @Override
    public List<BookDto> findAll() {
        List<BookEntity> list = StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return list.stream().map(bookMapper::mapTo).collect(Collectors.toList());
    }

    @Override
    public Page<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).map(bookMapper::mapTo);
    }

    @Override
    public Optional<BookDto> findOne(String isbn) {
        return bookRepository.findById(isbn).map(bookMapper::mapTo);
    }

    @Override
    public boolean isExists(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public BookDto partialUpdateBook(String isbn, BookDto bookDto) {

        bookDto.setIsbn(isbn);
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity bookFound = bookRepository.findById(isbn).get();
        Optional.ofNullable(bookEntity.getTitle()).ifPresent(bookFound::setTitle);
        Optional.ofNullable(bookEntity.getAuthorEntity()).ifPresent(bookFound::setAuthorEntity);

        return bookMapper.mapTo(bookRepository.save(bookFound));
    }

    @Override
    public void delete(String isbn) {
        bookRepository.deleteById(isbn);
    }

}
