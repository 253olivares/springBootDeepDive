package com.miguel.database_sql.services;

import java.util.List;
import java.util.Optional;

import com.miguel.database_sql.domain.entities.AuthorEntity;

public interface AuthorService {

    AuthorEntity saveAuthor(AuthorEntity author);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(Long id);

    boolean isExists(Long id);

    AuthorEntity partialUpdateAuthor(Long id, AuthorEntity author);

    void delete(Long id);

}
