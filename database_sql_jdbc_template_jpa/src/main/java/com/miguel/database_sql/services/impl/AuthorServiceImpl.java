package com.miguel.database_sql.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.miguel.database_sql.domain.dto.AuthorDto;
import com.miguel.database_sql.domain.entities.AuthorEntity;
import com.miguel.database_sql.mappers.Mapper;
import com.miguel.database_sql.repositories.AuthorRepository;
import com.miguel.database_sql.repositories.BookRepository;
import com.miguel.database_sql.services.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private Mapper<AuthorEntity, AuthorDto> authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository,
            Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorDto saveAuthor(AuthorDto author) {
        return authorMapper.mapTo(authorRepository.save(authorMapper.mapFrom(author)));
    }

    @Override
    public List<AuthorDto> findAll() {
        List<AuthorEntity> list = StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return list.stream().map(authorMapper::mapTo).collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorDto> findOne(Long id) {
        return authorRepository.findById(id).map(authorMapper::mapTo);
    }

    @Override
    public boolean isExists(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public AuthorDto partialUpdateAuthor(Long id, AuthorDto author) {

        author.setId(id);

        AuthorEntity authorFound = authorRepository.findById(id).get();
        Optional.ofNullable(author.getAge()).ifPresent(authorFound::setAge);
        Optional.ofNullable(author.getName()).ifPresent(authorFound::setName);
        return authorMapper.mapTo(authorRepository.save(authorFound));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteBooksFromAuthorDelete(id);
        authorRepository.deleteById(id);
    }
}
