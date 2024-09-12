package com.miguel.database_sql.dao.Impl;

import java.lang.classfile.ClassFile.Option;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.miguel.database_sql.TestDataUtil;
import com.miguel.database_sql.dao.impl.AuthorDaoImpl;
import com.miguel.database_sql.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorDaoImpIntegrationTest {

    private AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImpIntegrationTest(AuthorDaoImpl underTest) {
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

}
