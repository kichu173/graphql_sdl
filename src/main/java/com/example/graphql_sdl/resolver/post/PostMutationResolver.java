package com.example.graphql_sdl.resolver.post;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.graphql_sdl.dto.PostDto;
import com.example.graphql_sdl.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PostMutationResolver implements GraphQLMutationResolver {

    private final PostService postService;
    @Autowired
    public PostMutationResolver(PostService postService) {
        this.postService = postService;
    }

    public UUID createPost(PostDto postDto) {
        return postService.createPost(postDto);
    }
}
