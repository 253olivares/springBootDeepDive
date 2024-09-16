package com.miguel.database_sql.services;

import java.util.List;
import java.util.Optional;

import com.miguel.database_sql.domain.dto.AuthorDto;

public interface AuthorService {

    AuthorDto saveAuthor(AuthorDto author);

    List<AuthorDto> findAll();

    Optional<AuthorDto> findOne(Long id);

    boolean isExists(Long id);

    AuthorDto partialUpdateAuthor(Long id, AuthorDto author);

    void delete(Long id);

}
