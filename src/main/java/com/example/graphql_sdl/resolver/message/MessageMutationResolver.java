package com.example.graphql_sdl.resolver.message;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.graphql_sdl.dto.MessageDto;
import com.example.graphql_sdl.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
