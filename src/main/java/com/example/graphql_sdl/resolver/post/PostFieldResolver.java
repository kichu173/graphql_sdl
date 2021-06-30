package com.example.graphql_sdl.resolver.post;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.graphql_sdl.dto.AuthorDto;
import com.example.graphql_sdl.dto.MessageDto;
import com.example.graphql_sdl.dto.PostDto;
import com.example.graphql_sdl.services.AuthorService;
import com.example.graphql_sdl.services.MessageService;
import com.example.graphql_sdl.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class PostFieldResolver implements GraphQLResolver<PostDto> {
    private final AuthorService authorService;

    public PostFieldResolver(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    private MessageService messageService;

    public AuthorDto author(PostDto postDto) {
        /*return AuthorDto.builder()
                .id(UUID.randomUUID())
                .name("Peter")
                .email("peter@abc.com")
                .build();*/
        return authorService.getAuthorById(postDto.getAuthorId());
    }

    public List<MessageDto> getMessages(PostDto postDto, Integer first) {
        /*return Collections.singletonList(
                MessageDto.builder()
                        .id(UUID.randomUUID())
                        .authorId(UUID.randomUUID())
                        .build()
        );*/
        return messageService.getFirstFewMessagesByPostId(postDto.getId(),first);
    }
}
