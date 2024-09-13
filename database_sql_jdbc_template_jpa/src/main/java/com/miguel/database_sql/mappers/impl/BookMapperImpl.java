package com.miguel.database_sql.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.miguel.database_sql.domain.dto.BookDto;
import com.miguel.database_sql.domain.entities.BookEntity;
import com.miguel.database_sql.mappers.Mapper;

@Component
public class BookMapperImpl implements Mapper<BookEntity, BookDto> {

    private ModelMapper modelMapper;

    public BookMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDto mapTo(BookEntity a) {
        return modelMapper.map(a, BookDto.class);
    }

    @Override
    public BookEntity mapFrom(BookDto b) {
        return modelMapper.map(b, BookEntity.class);
    }

}
