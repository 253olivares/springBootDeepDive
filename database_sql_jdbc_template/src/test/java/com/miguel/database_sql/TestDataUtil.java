package com.miguel.database_sql;

import com.miguel.database_sql.domain.Author;
import com.miguel.database_sql.domain.Book;

public final class TestDataUtil {
    private TestDataUtil() {

    }

    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("ChickenNugget")
                .age(80)
                .build();
    }

    public static Book createTestBook() {
        return Book.builder()
                .isbn("12d2131d")
                .title("Book Title")
                .authorId(1L)
                .build();
    }
}
