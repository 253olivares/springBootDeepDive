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

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("BullondCube")
                .age(60)
                .build();
    }

    public static Book createTestBook() {
        return Book.builder()
                .isbn("12d2131d")
                .title("Book Title")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("213dsaf")
                .title("Book Title2")
                .authorId(2L)
                .build();
    }

    public static Book createTestBookC() {
        return Book.builder()
                .isbn("213gsa")
                .title("Book Title3")
                .authorId(1L)
                .build();
    }
}
