package com.miguel.database_sql.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.miguel.database_sql.domain.entities.BookEntity;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, String> {

}
