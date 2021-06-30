package com.example.graphql_sdl.resolver.message;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.graphql_sdl.dto.AuthorDto;
import com.example.graphql_sdl.dto.MessageDto;
import com.example.graphql_sdl.dto.PostDto;
import com.example.graphql_sdl.services.AuthorService;
import com.example.graphql_sdl.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageFieldResolver implements GraphQLResolver<MessageDto> {
    @Autowired
    private PostService postService;

    public PostDto getPost(MessageDto messageDto) {
        return postService.getPostById(messageDto.getPostId());
    }

    public AuthorDto getAuthor(MessageDto messageDto) {
        return postService.getAuthorById(messageDto.getAuthorId());
    }
}
