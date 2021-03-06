package com.example.graphql_sdl.resolver.message;

import com.example.graphql_sdl.dto.MessageDto;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessageSubscription implements GraphQLSubscriptionResolver {
    @Autowired
    private MessagePublisher messagePublisher;

    public Publisher<MessageDto> recentMessageByPost(UUID postId) {
        return  messagePublisher.getRecentMessageFor(postId);
    }
}
