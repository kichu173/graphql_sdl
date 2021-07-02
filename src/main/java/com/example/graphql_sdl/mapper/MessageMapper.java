package com.example.graphql_sdl.mapper;

import com.example.graphql_sdl.dto.MessageDto;
import com.example.graphql_sdl.model.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    public MessageDto convertMessageToDto(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .text(message.getText())
                .authorId(message.getAuthor().getId())
                .postId(message.getPost().getId())
                .build();
    }
}
