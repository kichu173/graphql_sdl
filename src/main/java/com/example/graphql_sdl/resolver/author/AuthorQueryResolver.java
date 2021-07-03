package com.example.graphql_sdl.resolver.author;

import com.example.graphql_sdl.dto.AuthorDto;
import com.example.graphql_sdl.services.AuthorService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorQueryResolver implements GraphQLQueryResolver {

    private final AuthorService authorService;
    @Autowired
    public AuthorQueryResolver(AuthorService authorService) {
        this.authorService = authorService;
    }

    public List<AuthorDto> getAuthors() {
        /*return Collections.singletonList(AuthorDto.builder()
                .id(UUID.randomUUID())
                .name("Peter")
                .email("Peter@abc.com")
                .build());*/
        return authorService.getAuthors();
    }
}
