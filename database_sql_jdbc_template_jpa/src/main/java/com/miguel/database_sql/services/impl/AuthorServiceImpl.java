package com.miguel.database_sql.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.miguel.database_sql.domain.entities.AuthorEntity;
import com.miguel.database_sql.repositories.AuthorRepository;
import com.miguel.database_sql.repositories.BookRepository;
import com.miguel.database_sql.services.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public AuthorEntity saveAuthor(AuthorEntity author) {
        return authorRepository.save(author);
    }

    @Override
    public List<AuthorEntity> findAll() {
        return (List<AuthorEntity>) authorRepository.findAll();
    }

    @Override
    public Optional<AuthorEntity> findOne(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public AuthorEntity partialUpdateAuthor(Long id, AuthorEntity author) {

        author.setId(id);

        AuthorEntity authorFound = authorRepository.findById(id).get();
        Optional.ofNullable(author.getAge()).ifPresent(authorFound::setAge);
        Optional.ofNullable(author.getName()).ifPresent(authorFound::setName);
        return authorRepository.save(authorFound);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteBooksFromAuthorDelete(id);
        authorRepository.deleteById(id);
    }
}
