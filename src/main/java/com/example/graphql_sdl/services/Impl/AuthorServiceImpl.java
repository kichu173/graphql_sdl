package com.example.graphql_sdl.services.Impl;

import com.example.graphql_sdl.dto.AuthorDto;
import com.example.graphql_sdl.model.Author;
import com.example.graphql_sdl.repository.AuthorRepository;
import com.example.graphql_sdl.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository repository) {
        this.authorRepository = repository;
    }

    @Override
    public List<AuthorDto> getAuthors() {
        List<Author> all = authorRepository.findAll();
        return all.stream().map(author -> {
            return AuthorDto.builder()
                    .id(author.getId())
                    .email(author.getEmail())
                    .name(author.getName())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public AuthorDto getAuthorById(UUID authorId) {
        Author author = authorRepository.findById(authorId).get();
        return AuthorDto.builder()
                .id(author.getId())
                .email(author.getEmail())
                .name(author.getName())
                .build();
    }

    @Override
    public UUID createAuthor(AuthorDto authorDto) {
        Author newAuthor = Author.builder()
                .email(authorDto.getEmail())
                .name(authorDto.getName())
                .build();
        Author createdAuthor = authorRepository.saveAndFlush(newAuthor);
        return createdAuthor.getId();
    }
}
