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
import com.miguel.database_sql.domain.dto.BookDto;
import com.miguel.database_sql.domain.entities.AuthorEntity;
import com.miguel.database_sql.domain.entities.BookEntity;
import com.miguel.database_sql.services.BookService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {

        private BookService bookService;

        private MockMvc mockMvc;

        private ObjectMapper objectMapper;

        @Autowired
        public BookControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, BookService bookService) {
                this.mockMvc = mockMvc;
                this.objectMapper = objectMapper;
                this.bookService = bookService;
        }

        @Test
        public void testThatCreateBookReturnHttpStatus201Created() throws Exception {
                BookDto bookDto = TestDataUtil.createTestBookDto(null);

                String bookJson = objectMapper.writeValueAsString(bookDto);

                mockMvc.perform(
                                MockMvcRequestBuilders.put("/api/books/" + bookDto.getIsbn())
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(bookJson))
                                .andExpect(
                                                MockMvcResultMatchers.status().isCreated());
        }

        @Test
        public void testThatGetAllBooksReturnsHttpStatus200Get() throws Exception {
                mockMvc.perform(
                                MockMvcRequestBuilders.get("/api/books")
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(
                                                MockMvcResultMatchers.status().isOk());
        }

        @Test
        public void testThatGetAllBooksReturnExpectedValues() throws Exception {
                BookEntity testBookA = TestDataUtil.createTestBook(null);
                BookEntity testBookB = TestDataUtil.createTestBook(null);

                bookService.createUpdateBook(testBookA.getIsbn(), testBookA);
                bookService.createUpdateBook(testBookB.getIsbn(), testBookB);

                mockMvc.perform(
                                MockMvcRequestBuilders.get("/api/books")
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.content[0].isbn").isString())
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.content[0].title").isString());
        }

        @Test
        public void testThatGetBookReturnsHttpOk() throws Exception {
                BookEntity testBookEntityA = TestDataUtil.createTestBook(null);
                bookService.createUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);

                mockMvc.perform(
                                MockMvcRequestBuilders.get("/api/books/" + testBookEntityA.getIsbn())
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        public void testThatGetBookReturnsHttpNot_Found() throws Exception {
                mockMvc.perform(
                                MockMvcRequestBuilders.get("/api/books/1234").contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isNotFound());

        }

        @Test
        public void testThatGetBookByIsbnReturnExpectedBook() throws Exception {
                BookEntity testBookEntity = TestDataUtil.createTestBook(null);
                bookService.createUpdateBook(testBookEntity.getIsbn(), testBookEntity);

                mockMvc.perform(
                                MockMvcRequestBuilders.get("/api/books/" + testBookEntity.getIsbn())
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.isbn").isString());
        }

        @Test
        public void testThatUpdateBookReturnsHTTPStatus200Ok() throws Exception {
                BookEntity testBookEntity = TestDataUtil.createTestBook(null);

                String testBookEntityJSON2 = objectMapper.writeValueAsString(testBookEntity);

                mockMvc.perform(
                                MockMvcRequestBuilders.put("/api/books/" + testBookEntity.getIsbn())
                                                .contentType(MediaType.APPLICATION_JSON).content(testBookEntityJSON2))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.title")
                                                                .value(testBookEntity.getTitle()))
                                .andExpect(
                                                MockMvcResultMatchers.status().isCreated());

                mockMvc.perform(
                                MockMvcRequestBuilders.get("/api/books/" + testBookEntity.getIsbn())
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.title")
                                                                .value(testBookEntity.getTitle()))
                                .andExpect(
                                                MockMvcResultMatchers.status().isOk());

                testBookEntity.setTitle("Update");

                String testBookEntityJSON = objectMapper.writeValueAsString(testBookEntity);

                mockMvc.perform(
                                MockMvcRequestBuilders.put("/api/books/" + testBookEntity.getIsbn())
                                                .contentType(MediaType.APPLICATION_JSON).content(testBookEntityJSON))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.title").value("Update"))
                                .andExpect(
                                                MockMvcResultMatchers.status().isOk());
        }

        @Test
        public void testThatPatchBookReturnsHttpStatusOk() throws Exception {
                BookEntity testBook = TestDataUtil.createTestBook(null);
                BookEntity savedBook = bookService.createUpdateBook(testBook.getIsbn(), testBook);

                AuthorEntity authorEntity = AuthorEntity.builder().id(1L).name("Damn").age(50).build();
                savedBook.setAuthorEntity(authorEntity);
                savedBook.setTitle("Update");

                String testBookJson = objectMapper.writeValueAsString(savedBook);

                mockMvc.perform(
                                MockMvcRequestBuilders.patch("/api/books/" + savedBook.getIsbn())
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(testBookJson))
                                .andExpect(
                                                MockMvcResultMatchers.status().isOk());

        }

        @Test
        public void testThatPatchBookReturnsHttpStatusNotFound() throws Exception {
                BookEntity testBook = TestDataUtil.createTestBook(null);
                BookEntity savedBook = bookService.createUpdateBook(testBook.getIsbn(), testBook);

                AuthorEntity authorEntity = AuthorEntity.builder().id(1L).name("Damn").age(50).build();
                savedBook.setAuthorEntity(authorEntity);
                savedBook.setTitle("Update");

                String testBookJson = objectMapper.writeValueAsString(savedBook);

                mockMvc.perform(
                                MockMvcRequestBuilders.patch("/api/books/" + 20)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(testBookJson))
                                .andExpect(
                                                MockMvcResultMatchers.status().isNotFound());

        }

        @Test
        public void testThatPatchBookReturnsUpdatedBookValues() throws Exception {
                BookEntity testBook = TestDataUtil.createTestBook(null);
                BookEntity savedBook = bookService.createUpdateBook(testBook.getIsbn(), testBook);

                savedBook.setTitle("Update");

                String testBookJson = objectMapper.writeValueAsString(savedBook);

                mockMvc.perform(
                                MockMvcRequestBuilders.patch("/api/books/" + savedBook.getIsbn())
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(testBookJson))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.title").value("Update"));

        }

        @Test
        public void testThatDeleteBookReturnsHttpNoContent() throws Exception {
                BookEntity bookEntity = TestDataUtil.createTestBook(null);
                BookEntity savedBook = bookService.createUpdateBook(bookEntity.getIsbn(), bookEntity);

                mockMvc.perform(
                                MockMvcRequestBuilders.delete("/api/books/" + savedBook.getIsbn())
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(
                                                MockMvcResultMatchers.status().isNoContent());
        }

        @Test
        public void testThatDeleteBookReturnsHttpNotFound() throws Exception {

                mockMvc.perform(
                                MockMvcRequestBuilders.delete("/api/books/" + 50)
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(
                                                MockMvcResultMatchers.status().isNotFound());
        }
}
