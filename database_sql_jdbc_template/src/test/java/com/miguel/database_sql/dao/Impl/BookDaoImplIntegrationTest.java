package com.miguel.database_sql.dao.Impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.miguel.database_sql.TestDataUtil;
import com.miguel.database_sql.dao.AuthorDao;
import com.miguel.database_sql.dao.impl.AuthorDaoImpl;
import com.miguel.database_sql.dao.impl.BookDaoImpl;
import com.miguel.database_sql.domain.Author;
import com.miguel.database_sql.domain.Book;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookDaoImplIntegrationTest {

    private AuthorDao authorDao;
    private BookDaoImpl underTest;

    @Autowired
    public BookDaoImplIntegrationTest(BookDaoImpl underTest, AuthorDao authorDao) {
        this.underTest = underTest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {

        Author author = TestDataUtil.createTestAuthor();
        authorDao.create(author);

        Book book = TestDataUtil.createTestBook();

        book.setAuthorId(author.getId());

        underTest.create(book);
        Optional<Book> results = underTest.findOne(book.getIsbn());

        assertThat(results).isPresent();

        assertThat(results.get()).isEqualTo(book);
        System.out.println(results.get());
    }
}
