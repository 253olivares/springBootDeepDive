package com.miguel.database_sql.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguel.database_sql.TestDataUtil;
import com.miguel.database_sql.domain.entities.AuthorEntity;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTests {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public AuthorControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testThatCreateAuthorSuccesfullyReturnsHTTP201Created() throws Exception {

        AuthorEntity testAuthor = TestDataUtil.createTestAuthor();
        testAuthor.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthor);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson))
                .andExpect(
                        MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testThatCreateAuthorSuccesfullyReturnsSavedAuthor() throws Exception {
        AuthorEntity testAuthor = TestDataUtil.createTestAuthor();
        testAuthor.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthor);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.name").isString())
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.age").isNumber());
    }

}
