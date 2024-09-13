package com.miguel.database_sql.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.miguel.database_sql.domain.dto.AuthorDto;

import com.miguel.database_sql.domain.entities.AuthorEntity;
import com.miguel.database_sql.mappers.Mapper;
import com.miguel.database_sql.services.AuthorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private AuthorService authorService;
    private Mapper<AuthorEntity, AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {

        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity savedAuthorEntity = authorService.createAuthor(authorEntity);

        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);
    }

}
