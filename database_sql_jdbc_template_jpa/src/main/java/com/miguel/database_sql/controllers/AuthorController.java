package com.miguel.database_sql.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.miguel.database_sql.domain.dto.AuthorDto;

import com.miguel.database_sql.services.AuthorService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {

        AuthorDto savedAuthorEntity = authorService.saveAuthor(authorDto);

        return new ResponseEntity<>(savedAuthorEntity, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> listAuthors() {
        List<AuthorDto> authors = authorService.findAll();
        return new ResponseEntity<>(authors,
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long id) {
        Optional<AuthorDto> authorReturn = authorService.findOne(id);
        if (authorReturn.isPresent()) {
            return new ResponseEntity<>(authorReturn.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
        if (!authorService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        authorDto.setId(id);

        AuthorDto savedAuthorEntity = authorService.saveAuthor(authorDto);

        return new ResponseEntity<>(savedAuthorEntity, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorDto> partialAuthorUpdate(@PathVariable("id") Long id,
            @RequestBody AuthorDto authorDto) {
        if (!authorService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AuthorDto updatedAuthor = authorService.partialUpdateAuthor(id, authorDto);

        return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") Long id) {
        if (!authorService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            authorService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
