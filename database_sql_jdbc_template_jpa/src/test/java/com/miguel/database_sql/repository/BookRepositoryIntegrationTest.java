package com.miguel.database_sql.repository;

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
import com.miguel.database_sql.domain.entities.AuthorEntity;
import com.miguel.database_sql.domain.entities.BookEntity;
import com.miguel.database_sql.repositories.BookRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTest {

    private BookRepository underTest;

    @Autowired
    public BookRepositoryIntegrationTest(BookRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {

        AuthorEntity author = TestDataUtil.createTestAuthor();
        BookEntity book = TestDataUtil.createTestBook(author);

        underTest.save(book);
        Optional<BookEntity> results = underTest.findById(book.getIsbn());

        assertThat(results).isPresent();
        assertThat(results.get()).isEqualTo(book);
        System.out.println(results.get());
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        AuthorEntity authorA = TestDataUtil.createTestAuthor();
        AuthorEntity authorB = TestDataUtil.createTestAuthorB();

        BookEntity bookA = TestDataUtil.createTestBook(authorA);
        BookEntity bookB = TestDataUtil.createTestBookB(authorB);
        BookEntity bookC = TestDataUtil.createTestBookC(authorA);

        underTest.save(bookA);
        underTest.save(bookB);
        underTest.save(bookC);
        List<BookEntity> resultList = (List<BookEntity>) underTest.findAll();

        assertThat(resultList).hasSize(3).containsExactly(bookA, bookB, bookC);

    }

    @Test
    public void testThatBooksCanBeUpdated() {
        AuthorEntity authorA = TestDataUtil.createTestAuthor();
        BookEntity bookA = TestDataUtil.createTestBook(authorA);

        underTest.save(bookA);

        bookA.setTitle("UPDATED");
        underTest.save(bookA);

        Optional<BookEntity> result = underTest.findById(bookA.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);
    }

    @Test
    public void testThatBooksCanBeDeleted() {
        AuthorEntity authorA = TestDataUtil.createTestAuthor();
        BookEntity bookA = TestDataUtil.createTestBook(authorA);

        underTest.save(bookA);

        underTest.deleteById(bookA.getIsbn());

        Optional<BookEntity> result = underTest.findById(bookA.getIsbn());

        assertThat(result).isEmpty();

    }
}
