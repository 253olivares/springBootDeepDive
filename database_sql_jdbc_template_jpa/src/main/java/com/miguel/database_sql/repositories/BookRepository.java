package com.miguel.database_sql.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.miguel.database_sql.domain.entities.BookEntity;

@Repository
public interface BookRepository
        extends CrudRepository<BookEntity, String>, PagingAndSortingRepository<BookEntity, String> {

    @Modifying
    @Transactional
    @Query("DELETE FROM BookEntity b WHERE b.authorEntity.id = ?1")
    void deleteBooksFromAuthorDelete(Long id);
}
