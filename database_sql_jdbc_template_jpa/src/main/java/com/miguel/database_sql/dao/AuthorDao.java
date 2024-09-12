package com.miguel.database_sql.dao;

import java.util.List;
import java.util.Optional;

import com.miguel.database_sql.domain.Author;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(Long id);

    List<Author> findAll();

    void update(long id, Author author);

    void delete(long id);
}
