package com.example.graphql_sdl.resolver.post;

import com.example.graphql_sdl.dto.PostDto;
import com.example.graphql_sdl.services.PostService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostQueryResolver implements GraphQLQueryResolver {

    private final PostService postService;
    @Autowired
    public PostQueryResolver(PostService postService) {
        this.postService = postService;
    }


    public List<PostDto> recentPosts(int count, int offset) {
        /*return Collections.singletonList(
                PostDto.builder()
                        .id(UUID.randomUUID())
                        .title("Post title")
                        .build()
        );*/
        return postService.getRecentPosts(count,offset);
    }
}
