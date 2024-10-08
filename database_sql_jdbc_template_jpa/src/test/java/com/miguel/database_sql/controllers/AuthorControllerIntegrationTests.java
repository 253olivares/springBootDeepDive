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
import com.miguel.database_sql.domain.dto.AuthorDto;
import com.miguel.database_sql.domain.entities.AuthorEntity;
import com.miguel.database_sql.mappers.Mapper;
import com.miguel.database_sql.services.AuthorService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTests {

        private AuthorService authorService;

        private MockMvc mockMvc;

        private ObjectMapper objectMapper;

        private Mapper<AuthorEntity, AuthorDto> authorMapper;

        @Autowired
        public AuthorControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper,
                        AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
                this.mockMvc = mockMvc;
                this.objectMapper = objectMapper;
                this.authorService = authorService;
                this.authorMapper = authorMapper;
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

        @Test
        public void testThatListAuthorsReturnsHttpStatus200() throws Exception {
                mockMvc.perform(
                                MockMvcRequestBuilders.get("/api/authors").contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        public void testThatListAuthorReturnsListOfAuthors() throws Exception {

                AuthorEntity testAuthorEntity = TestDataUtil.createTestAuthor();
                authorService.saveAuthor(authorMapper.mapTo(testAuthorEntity));

                mockMvc.perform(
                                MockMvcRequestBuilders.get("/api/authors").contentType(MediaType.APPLICATION_JSON))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$[0].name").isString())
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$[0].age").isNumber());
        }

        @Test
        public void testThatGetAuthorResturnsOk() throws Exception {

                AuthorEntity testAuthorEntity = TestDataUtil.createTestAuthor();
                authorService.saveAuthor(authorMapper.mapTo(testAuthorEntity));

                mockMvc.perform(
                                MockMvcRequestBuilders.get("/api/authors/" + testAuthorEntity.getId())
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk());

        }

        @Test
        public void testThatGetAuthorDoesNotReturnOk() throws Exception {
                AuthorEntity testAuthorEntity = TestDataUtil.createTestAuthor();
                mockMvc.perform(
                                MockMvcRequestBuilders.get("/api/authors/" + testAuthorEntity.getId())
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isNotFound());
        }

        @Test
        public void testThatGetAuthorReturnsProperAuthor() throws Exception {

                AuthorEntity testAuthorEntity = TestDataUtil.createTestAuthor();
                authorService.saveAuthor(authorMapper.mapTo(testAuthorEntity));

                mockMvc.perform(
                                MockMvcRequestBuilders.get("/api/authors/" + testAuthorEntity.getId())
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.id").isNumber())
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.name").isString())
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.age").isNumber());
        }

        @Test
        public void testThatUpdateAuthorReturnsHttpOk() throws Exception {
                AuthorEntity testAuthorEntity = TestDataUtil.createTestAuthor();

                authorService.saveAuthor(authorMapper.mapTo(testAuthorEntity));

                testAuthorEntity.setAge(90);

                String testAuthorJson = objectMapper.writeValueAsString(testAuthorEntity);

                mockMvc.perform(
                                MockMvcRequestBuilders.put("/api/authors/" + testAuthorEntity.getId())
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(testAuthorJson))
                                .andExpect(
                                                MockMvcResultMatchers.status().isOk());

        }

        @Test
        public void testThatUpdatesAuthorWhenNotFoundReturnsNotFound() throws Exception {
                AuthorEntity testAuthorEntity = TestDataUtil.createTestAuthor();

                authorService.saveAuthor(authorMapper.mapTo(testAuthorEntity));

                testAuthorEntity.setAge(90);

                String testAuthorJson = objectMapper.writeValueAsString(testAuthorEntity);

                mockMvc.perform(
                                MockMvcRequestBuilders.put("/api/authors/99")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(testAuthorJson))
                                .andExpect(
                                                MockMvcResultMatchers.status().isNotFound());
        }

        @Test
        public void testThatUpdateAuthorActuallyUpdatesTheValues() throws Exception {
                AuthorEntity testAuthorEntity = TestDataUtil.createTestAuthor();

                testAuthorEntity.setAge(50);
                testAuthorEntity.setName("Chicken");

                String testAuthorJson = objectMapper.writeValueAsString(testAuthorEntity);

                mockMvc.perform(
                                MockMvcRequestBuilders.post("/api/authors")
                                                .contentType(MediaType.APPLICATION_JSON).content(testAuthorJson))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.age").value(50))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.name").value("Chicken"));

                testAuthorEntity.setAge(90);
                testAuthorEntity.setName("Jacob");

                String testAuthorJson2 = objectMapper.writeValueAsString(testAuthorEntity);

                mockMvc.perform(
                                MockMvcRequestBuilders.put("/api/authors/" + testAuthorEntity.getId())
                                                .contentType(MediaType.APPLICATION_JSON).content(testAuthorJson2))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.age").value(90))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.name").value("Jacob"));

        }

        @Test
        public void testThatPatchAuthorReturnsHttpStatusOk() throws Exception {
                AuthorEntity testAuthor = TestDataUtil.createTestAuthor();
                AuthorEntity savedAuthor = authorMapper
                                .mapFrom(authorService.saveAuthor(authorMapper.mapTo(testAuthor)));

                AuthorEntity testAuthorUpdate = AuthorEntity.builder()
                                .id(null)
                                .name("Crancky")
                                .age(null)
                                .build();

                String testAuthorUpdateJson = objectMapper.writeValueAsString(testAuthorUpdate);

                mockMvc.perform(
                                MockMvcRequestBuilders.patch("/api/authors/" + savedAuthor.getId())
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(testAuthorUpdateJson))
                                .andExpect(
                                                MockMvcResultMatchers.status().isOk());

        }

        @Test
        public void testThatPatchAuthorReturnsHttpStatusNotFound() throws Exception {
                AuthorEntity testAuthorUpdate = AuthorEntity.builder()
                                .id(null)
                                .name("Crancky")
                                .age(null)
                                .build();
                String testAuthorUpdateJson = objectMapper.writeValueAsString(testAuthorUpdate);

                mockMvc.perform(
                                MockMvcRequestBuilders.patch("/api/authors/" + 53)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(testAuthorUpdateJson))
                                .andExpect(
                                                MockMvcResultMatchers.status().isNotFound());

        }

        @Test
        public void testThatPatchAuthorChangesValues() throws Exception {
                AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
                AuthorEntity savedAuthor = authorMapper
                                .mapFrom(authorService.saveAuthor(authorMapper.mapTo(authorEntity)));

                AuthorEntity authorEntityUpdate = AuthorEntity.builder()
                                .id(null)
                                .name("Update")
                                .age(null)
                                .build();

                String testAuthorUpdateJson = objectMapper.writeValueAsString(authorEntityUpdate);

                mockMvc.perform(
                                MockMvcRequestBuilders.patch("/api/authors/" + savedAuthor.getId())
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(testAuthorUpdateJson))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.id").value(authorEntity.getId()))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.name").value("Update"))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.age").value(authorEntity.getAge()));
        }

        @Test
        public void testThatDeleteAuthorReturnsHttpNoContent() throws Exception {

                AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
                AuthorEntity savedAuthor = authorMapper
                                .mapFrom(authorService.saveAuthor(authorMapper.mapTo(authorEntity)));

                mockMvc.perform(
                                MockMvcRequestBuilders.delete("/api/authors/" + savedAuthor.getId())
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(
                                                MockMvcResultMatchers.status().isNoContent());
        }

        @Test
        public void testThatDeleteAuthorReturnsHttpNotFound() throws Exception {

                mockMvc.perform(
                                MockMvcRequestBuilders.delete("/api/authors/" + 50)
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(
                                                MockMvcResultMatchers.status().isNotFound());
        }
}
