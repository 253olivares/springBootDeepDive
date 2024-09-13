package com.api_Test.book_api;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

import com.api_Test.book_api.domain.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class JacksonTests {
    @Test
    public void testThatObjectMapperCanCreateJasonFromJavaObject() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Book book = Book.builder()
                .isbn("YUmpiSbn")
                .title("Yump")
                .author("Written by yump")
                .yearPublished("2005").build();

        String result = objectMapper.writeValueAsString(book);

        assertThat(result).isEqualTo(":D");
    };
}
