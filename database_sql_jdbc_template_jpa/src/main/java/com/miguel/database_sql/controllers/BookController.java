package com.miguel.database_sql.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miguel.database_sql.domain.dto.BookDto;
import com.miguel.database_sql.domain.entities.BookEntity;
import com.miguel.database_sql.mappers.Mapper;
import com.miguel.database_sql.services.BookService;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService;
    private Mapper<BookEntity, BookDto> bookMapper;

    public BookController(BookService bookService, Mapper<BookEntity, BookDto> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllbooks() {
        List<BookEntity> lists = bookService.getBooks();
        List<BookDto> results = lists.stream().map(bookMapper::mapTo).collect(Collectors.toList());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookDto> findBookById(@PathVariable("isbn") String isbn) {
        Optional<BookEntity> bookReturn = bookService.findOne(isbn);
        if (bookReturn.isPresent()) {
            BookDto bookDto = bookMapper.mapTo(bookReturn.get());
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<BookDto> createUpdateBook(
            @PathVariable("isbn") String isbn,
            @RequestBody BookDto bookDto) {

        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        boolean bookExists = bookService.isExists(isbn);
        BookEntity savedBookEntity = bookService.createUpdateBook(isbn, bookEntity);
        BookDto updatedBookEntity = bookMapper.mapTo(savedBookEntity);

        if (bookExists) {
            // update
            return new ResponseEntity<>(updatedBookEntity, HttpStatus.OK);
        } else {
            // create
            return new ResponseEntity<>(updatedBookEntity, HttpStatus.CREATED);
        }
    }

}
