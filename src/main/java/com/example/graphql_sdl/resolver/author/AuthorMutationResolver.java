package com.example.graphql_sdl.resolver.author;

import com.example.graphql_sdl.dto.AuthorDto;
import com.example.graphql_sdl.services.AuthorService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthorMutationResolver implements GraphQLMutationResolver {
    private final AuthorService authorService;
    @Autowired
    public AuthorMutationResolver(AuthorService authorService) {
        this.authorService = authorService;
    }

    public UUID createAuthor(AuthorDto authorDto) {
//        return UUID.randomUUID();
        return authorService.createAuthor(authorDto);
    }
}
