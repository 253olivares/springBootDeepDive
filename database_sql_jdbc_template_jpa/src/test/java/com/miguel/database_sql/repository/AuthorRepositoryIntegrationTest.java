package com.miguel.database_sql.repository;

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
import com.miguel.database_sql.repositories.AuthorRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
// This annotation will clear our database for each test we run below!
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTest {

    private AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTest(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {

        AuthorEntity author = TestDataUtil.createTestAuthor();
        underTest.save(author);
        Optional<AuthorEntity> results = underTest.findById(author.getId());

        assertThat(results).isPresent();

        assertThat(results.get()).isEqualTo(author);
        System.out.println(results.get());
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        AuthorEntity authorA = TestDataUtil.createTestAuthor();
        AuthorEntity authorB = TestDataUtil.createTestAuthorB();

        underTest.save(authorA);
        underTest.save(authorB);

        List<AuthorEntity> resultsList = (List<AuthorEntity>) underTest.findAll();

        assertThat(resultsList).hasSize(2).containsExactly(authorA, authorB);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        AuthorEntity authorA = TestDataUtil.createTestAuthor();

        underTest.save(authorA);

        authorA.setName("UPDATED");
        underTest.save(authorA);
        Optional<AuthorEntity> result = underTest.findById(authorA.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorA);

    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        AuthorEntity authorA = TestDataUtil.createTestAuthor();
        underTest.save(authorA);

        underTest.deleteById(authorA.getId());

        Optional<AuthorEntity> result = underTest.findById(authorA.getId());

        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgesLessThan() {
        // Age 80
        AuthorEntity authorA = TestDataUtil.createTestAuthor();
        // Age 60
        AuthorEntity authorB = TestDataUtil.createTestAuthorB();

        underTest.save(authorA);
        underTest.save(authorB);

        // creating a custom query in JPA
        Iterable<AuthorEntity> results = underTest.ageLessThan(50);

        assertThat(results).containsExactly();

    }

    @Test
    public void testThatGetAuthorWithAgeLessThan() {
        // Age 80
        AuthorEntity authorA = TestDataUtil.createTestAuthor();
        // Age 60
        AuthorEntity authorB = TestDataUtil.createTestAuthorB();

        underTest.save(authorA);
        underTest.save(authorB);

        Iterable<AuthorEntity> results = underTest.findAuthorWithAgeGreaterThan(50);

        assertThat(results).containsExactly(authorA, authorB);
    }
}
