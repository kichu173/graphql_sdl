package com.example.graphql_sdl.services.Impl;

import com.example.graphql_sdl.dto.MessageDto;
import com.example.graphql_sdl.dto.PostDto;
import com.example.graphql_sdl.model.Message;
import com.example.graphql_sdl.model.Post;
import com.example.graphql_sdl.repository.MessageRepository;
import com.example.graphql_sdl.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
                        .postId(message.getPost().getId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> getFirstFewMessagesByPostId(UUID postId, Integer count) {
        List<Message> allByPost_id = messageRepository.findAllByPost_Id(postId, PageRequest.of(0, count));
        return allByPost_id.stream()
                .map(message ->
                        MessageDto.builder()
                                .id(message.getId())
                                .authorId(message.getAuthor().getId())
                                .text(message.getText())
                                .postId(postId)
                                .build()).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> getMessages(Integer count, Integer offset) {
        PageRequest of = PageRequest.of(offset, count); // offset ->How many page we want to skip,count -> size of the page
        Page<Message> all = messageRepository.findAll(of);
        return all.stream()
                .map(message -> {
                    return MessageDto.builder()
                            .id(message.getId())
                            .text(message.getText())
                            .postId(message.getPost().getId())
                            .authorId(message.getAuthor().getId())
                            .build();
                }).collect(Collectors.toList());
    }
}
