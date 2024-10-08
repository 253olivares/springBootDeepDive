package com.miguel.database_sql.dao.Impl;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.miguel.database_sql.TestDataUtil;
import com.miguel.database_sql.dao.impl.AuthorDaoImpl;
import com.miguel.database_sql.domain.Author;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSQL() {
        Author author = TestDataUtil.createTestAuthor();

        underTest.create(author);

        verify(jdbcTemplate).update(eq("INSERT INTO authors (id , name, age) VALUES (?, ?, ?)"),
                eq(1L), eq("ChickenNugget"), eq(80));
    }

    @Test
    public void testThatFindOneAuthorGeneratesTheCreateSQL() {
        underTest.findOne(1L);

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L));
    }

    @Test
    public void testThatFindManyAuthorsGenerateCorrectSQL() {
        underTest.findAll();

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any());

    }

    @Test
    public void testThatUpdateAuthorGenerateCorrectSQL() {
        Author author = TestDataUtil.createTestAuthor();
        underTest.update(3L, author);

        verify(jdbcTemplate).update(
                eq("UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?"),
                eq(1L),
                eq("ChickenNugget"),
                eq(80),
                eq(3L));

    }

    @Test
    public void testThatDeleteGeneratesTheCorrectSQL() {
        underTest.delete(1L);

        verify(jdbcTemplate).update(
                eq("DELETE FROM authors WHERE id = ?"), eq(1L));
    }
}
