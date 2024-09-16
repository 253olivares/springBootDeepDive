package com.miguel.database_sql;

import com.miguel.database_sql.domain.dto.AuthorDto;
import com.miguel.database_sql.domain.dto.BookDto;
import com.miguel.database_sql.domain.entities.AuthorEntity;
import com.miguel.database_sql.domain.entities.BookEntity;

public final class TestDataUtil {
    private TestDataUtil() {

    }

    public static AuthorEntity createTestAuthor() {
        return AuthorEntity.builder()
                .id(1L)
                .name("ChickenNugget")
                .age(80)
                .build();
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder()
                .id(2L)
                .name("BullondCube")
                .age(60)
                .build();
    }

    public static BookDto createTestBookDto(final AuthorDto authorDto) {
        return BookDto.builder()
                .isbn("TestingIsbn")
                .title("Testing Book")
                .author(authorDto)
                .build();
    }

    public static BookEntity createTestBook(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("12d2131d")
                .title("Book Title")
                .authorEntity(author)
                .build();
    }

    public static BookEntity createTestBookB(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("213dsaf")
                .title("Book Title2")
                .authorEntity(author)
                .build();
    }

    public static BookEntity createTestBookC(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("213gsa")
                .title("Book Title3")
                .authorEntity(author)
                .build();
    }
}
