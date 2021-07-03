package com.example.graphql_sdl.resolver.author;

import com.example.graphql_sdl.dto.AuthorDto;
import com.example.graphql_sdl.dto.MessageDto;
import com.example.graphql_sdl.dto.PostDto;
import com.example.graphql_sdl.services.MessageService;
import com.example.graphql_sdl.services.PostService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorFieldResolver implements GraphQLResolver<AuthorDto> {
    private final PostService postService;
    @Autowired
    public AuthorFieldResolver(PostService postService) {
        this.postService = postService;
    }
    @Autowired
    private MessageService messageService;

    public List<PostDto> posts(AuthorDto authorDto) {
/*        return Collections.singletonList(
                PostDto.builder()
                        .id(UUID.randomUUID())
                        .title("post title")
                        .category("post category")
                        .description("post desc")
//                        .authorId(authorDto.getId())
                        .build()
        );*/
        return postService.getAllPostByAuthorId(authorDto.getId());
    }

    public Integer getPostCount(AuthorDto authorDto) {
        return postService.getPostCountByAuthorId(authorDto.getId());
    }

    public List<MessageDto> getMessages(AuthorDto authorDto, Integer first) {
        /*return Collections.singletonList(
                MessageDto.builder()
                        .id(UUID.randomUUID())
                        .text("random text")
                        .build()
        ) ;*/
        return messageService.getFirstFewMessagesByAuthorId(authorDto.getId(),first);
    }
}
