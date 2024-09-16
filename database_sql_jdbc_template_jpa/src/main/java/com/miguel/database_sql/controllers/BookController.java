package com.miguel.database_sql.controllers;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

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
    public ResponseEntity<Page<BookDto>> getAllbooks(Pageable pageable) {
        Page<BookEntity> lists = bookService.findAll(pageable);
        return new ResponseEntity<>(lists.map(bookMapper::mapTo), HttpStatus.OK);
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

    @PatchMapping("/{isbn}")
    public ResponseEntity<BookDto> patchBookUpdate(
            @PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
        if (!bookService.isExists(isbn)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BookEntity bookEntity = bookMapper.mapFrom(bookDto);

        BookEntity updatedBook = bookService.partialUpdateBook(isbn, bookEntity);

        return new ResponseEntity<>(bookMapper.mapTo(updatedBook), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{isbn}")
    public ResponseEntity deleteBook(@PathVariable("isbn") String isbn) {
        if (!bookService.isExists(isbn)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            bookService.delete(isbn);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
