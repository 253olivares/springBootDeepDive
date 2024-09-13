package com.miguel.database_sql.services.impl;

import org.springframework.stereotype.Service;

import com.miguel.database_sql.domain.entities.AuthorEntity;
import com.miguel.database_sql.repositories.AuthorRepository;
import com.miguel.database_sql.services.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity author) {
        return authorRepository.save(author);
    }
}
