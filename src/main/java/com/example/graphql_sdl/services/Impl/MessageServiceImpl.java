package com.example.graphql_sdl.services.Impl;

import com.example.graphql_sdl.dto.MessageDto;
import com.example.graphql_sdl.model.Message;
import com.example.graphql_sdl.repository.MessageRepository;
import com.example.graphql_sdl.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<MessageDto> getFirstFewMessagesByAuthorId(UUID authorId, Integer count) {
        List<Message> allByAuthor_id = messageRepository.findAllByAuthor_Id(authorId, PageRequest.of(0, count));
        return allByAuthor_id.stream()
                .map(message -> MessageDto.builder()
                        .id(message.getId())
                        .text(message.getText())
                        .authorId(message.getAuthor().getId())
                        .build())
                .collect(Collectors.toList());
    }
}
