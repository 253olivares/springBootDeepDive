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

import com.miguel.database_sql.services.BookService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Page<BookDto>> getAllbooks(Pageable pageable) {
        Page<BookDto> lists = bookService.findAll(pageable);
        return new ResponseEntity<>(lists, HttpStatus.OK);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookDto> findBookById(@PathVariable("isbn") String isbn) {
        Optional<BookDto> bookReturn = bookService.findOne(isbn);
        if (bookReturn.isPresent()) {
            return new ResponseEntity<>(bookReturn.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<BookDto> createUpdateBook(
            @PathVariable("isbn") String isbn,
            @RequestBody BookDto bookDto) {

        boolean bookExists = bookService.isExists(isbn);
        BookDto savedBookDto = bookService.createUpdateBook(isbn, bookDto);

        if (bookExists) {
            // update
            return new ResponseEntity<>(savedBookDto, HttpStatus.OK);
        } else {
            // create
            return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
        }
    }

    @PatchMapping("/{isbn}")
    public ResponseEntity<BookDto> patchBookUpdate(
            @PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
        if (!bookService.isExists(isbn)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BookDto updatedBook = bookService.partialUpdateBook(isbn, bookDto);

        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
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
