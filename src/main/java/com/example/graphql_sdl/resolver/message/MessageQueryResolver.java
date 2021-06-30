package com.example.graphql_sdl.resolver.message;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.graphql_sdl.dto.MessageDto;
import com.example.graphql_sdl.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private MessageService messageService;

    public List<MessageDto> messages(Integer count, Integer offset) {
        return messageService.getMessages(count,offset);
    }
}
