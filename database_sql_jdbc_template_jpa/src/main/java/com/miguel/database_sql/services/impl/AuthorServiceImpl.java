package com.miguel.database_sql.services.impl;

import java.util.List;
import java.util.Optional;

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
}
