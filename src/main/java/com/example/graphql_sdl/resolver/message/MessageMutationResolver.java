package com.example.graphql_sdl.resolver.message;

import com.example.graphql_sdl.dto.MessageDto;
import com.example.graphql_sdl.services.MessageService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class MessageMutationResolver implements GraphQLMutationResolver {
    private final MessageService messageService;
    private final MessagePublisher messagePublisher;

    @Autowired
    public MessageMutationResolver(MessageService messageService, MessagePublisher messagePublisher) {
        this.messageService = messageService;
        this.messagePublisher = messagePublisher;
    }

    public UUID createMessage(MessageDto messageDto) {
//        return messageService.createMessage(messageDto); comment remove when you revise mutation
        UUID id = messageService.createMessage(messageDto);
        messageDto.setId(id);
        messagePublisher.publish(messageDto);
        return id;
    }

    // https://github.com/graphql-java/graphql-java-extended-validation
    public UUID createMessageForDirective(UUID id, String title, List<Integer> luckyNumbers, Integer value) {// for directive validation
        return UUID.randomUUID();
    }

}
