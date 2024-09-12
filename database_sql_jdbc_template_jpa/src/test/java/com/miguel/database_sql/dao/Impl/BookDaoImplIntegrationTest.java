package com.miguel.database_sql.dao.Impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.miguel.database_sql.TestDataUtil;
import com.miguel.database_sql.dao.AuthorDao;
import com.miguel.database_sql.dao.impl.BookDaoImpl;
import com.miguel.database_sql.domain.Author;
import com.miguel.database_sql.domain.Book;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        Author authorA = TestDataUtil.createTestAuthor();
        Author authorB = TestDataUtil.createTestAuthorB();

        authorDao.create(authorA);
        authorDao.create(authorB);

        Book bookA = TestDataUtil.createTestBook();
        Book bookB = TestDataUtil.createTestBookB();
        Book bookC = TestDataUtil.createTestBookC();

        underTest.create(bookA);
        underTest.create(bookB);
        underTest.create(bookC);
        List<Book> resultList = underTest.findAll();

        assertThat(resultList).hasSize(3).containsExactly(bookA, bookB, bookC);

    }

    @Test
    public void testThatBooksCanBeUpdated() {
        Book bookA = TestDataUtil.createTestBook();
        Author authorA = TestDataUtil.createTestAuthor();

        authorDao.create(authorA);
        underTest.create(bookA);

        bookA.setTitle("UPDATED");
        underTest.update(bookA.getIsbn(), bookA);

        Optional<Book> result = underTest.findOne(bookA.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);
    }

    @Test
    public void testThatBooksCanBeDeleted() {
        Author authorA = TestDataUtil.createTestAuthor();
        Book bookA = TestDataUtil.createTestBook();

        authorDao.create(authorA);
        bookA.setAuthorId(authorA.getId());
        underTest.create(bookA);

        underTest.delete(bookA.getIsbn());

        Optional<Book> result = underTest.findOne(bookA.getIsbn());

        assertThat(result).isEmpty();

    }
}
