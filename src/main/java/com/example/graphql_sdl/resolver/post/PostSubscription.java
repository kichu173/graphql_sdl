package com.example.graphql_sdl.resolver.post;

import com.example.graphql_sdl.dto.PostDto;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PostSubscription implements GraphQLSubscriptionResolver {
    @Autowired
    private PostPublisher postPublisher;// responsible for pushing newly created data to client

    public Publisher<PostDto> recentPost() {
        return postPublisher.getRecentPosts();
    }

    public Publisher<PostDto> recentPostByAuthor(UUID authorId) { // get recent Post created by particular author
        return postPublisher.getRecentPostByAuthor(authorId);
    }
}
