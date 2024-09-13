package com.api_Test.book_api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.api_Test.book_api.domain.Book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class BookController {

    @GetMapping("/books")
    public Book retrieveBook() {
        return Book.builder()
                .isbn("isnasd")
                .title("Frogs")
                .author("Meatballs")
                .yearPublished("20024")
                .build();
    }

    @PostMapping("/books")
    public Book postMethodName(@RequestBody final Book book) {
        return book;
    }

}
