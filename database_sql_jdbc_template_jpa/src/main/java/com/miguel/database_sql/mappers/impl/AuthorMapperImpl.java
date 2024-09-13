package com.miguel.database_sql.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.miguel.database_sql.domain.dto.AuthorDto;
import com.miguel.database_sql.domain.entities.AuthorEntity;
import com.miguel.database_sql.mappers.Mapper;

@Component
public class AuthorMapperImpl implements Mapper<AuthorEntity, AuthorDto> {

    private ModelMapper modelMapper;

    public AuthorMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDto mapTo(AuthorEntity authorEntity) {
        // TODO Auto-generated method stub
        return modelMapper.map(authorEntity, AuthorDto.class);
    };

    @Override
    public AuthorEntity mapFrom(AuthorDto authorDto) {
        // TODO Auto-generated method stub
        return modelMapper.map(authorDto, AuthorEntity.class);
    };

}
