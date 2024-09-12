package com.miguel.database_sql.dao.Impl;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.miguel.database_sql.TestDataUtil;
import com.miguel.database_sql.dao.impl.AuthorDaoImpl;
import com.miguel.database_sql.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
// This annotation will clear our database for each test we run below!
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImplIntegrationTest {

    private AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntegrationTest(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {

        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);
        Optional<Author> results = underTest.findOne(author.getId());

        assertThat(results).isPresent();

        assertThat(results.get()).isEqualTo(author);
        System.out.println(results.get());
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        Author authorA = TestDataUtil.createTestAuthor();
        Author authorB = TestDataUtil.createTestAuthorB();

        underTest.create(authorA);
        underTest.create(authorB);

        List<Author> resultsList = underTest.findAll();

        assertThat(resultsList).hasSize(2).containsExactly(authorA, authorB);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        Author authorA = TestDataUtil.createTestAuthor();

        underTest.create(authorA);

        authorA.setName("UPDATED");
        underTest.update(authorA.getId(), authorA);
        Optional<Author> result = underTest.findOne(authorA.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorA);

    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        Author authorA = TestDataUtil.createTestAuthor();
        underTest.create(authorA);

        underTest.delete(authorA.getId());

        Optional<Author> result = underTest.findOne(authorA.getId());

        assertThat(result).isEmpty();
    }
}
