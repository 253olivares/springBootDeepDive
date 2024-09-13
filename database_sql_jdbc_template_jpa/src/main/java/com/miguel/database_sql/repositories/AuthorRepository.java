package com.miguel.database_sql.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.miguel.database_sql.domain.entities.AuthorEntity;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

    // Custom query created using the method name
    Iterable<AuthorEntity> ageLessThan(int age);

    // HQL this is used to write custom sql search params
    @Query("SELECT a FROM AuthorEntity a WHERE a.age > ?1")
    Iterable<AuthorEntity> findAuthorWithAgeGreaterThan(int age);

}
