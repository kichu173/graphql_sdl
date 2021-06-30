package com.example.graphql_sdl.services;

import com.example.graphql_sdl.dto.MessageDto;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    List<MessageDto> getFirstFewMessagesByAuthorId(UUID authorId, Integer count);
}
