package com.example.graphql_sdl.resolver.post;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.example.graphql_sdl.dto.PostDto;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PostSubscription implements GraphQLSubscriptionResolver {
    @Autowired
    private PostPublisher postPublisher;

    public Publisher<PostDto> recentPost() {
        return postPublisher.getRecentPosts();
    }

    public Publisher<PostDto> recentPostByAuthor(UUID authorId) { // get recent Post created by particular author
        return postPublisher.getRecentPostByAuthor(authorId);
    }
}
