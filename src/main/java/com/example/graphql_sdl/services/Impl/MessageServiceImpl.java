package com.example.graphql_sdl.services.Impl;

import com.example.graphql_sdl.dto.MessageDto;
import com.example.graphql_sdl.exception.ResourceNotFound;
import com.example.graphql_sdl.mapper.MessageMapper;
import com.example.graphql_sdl.model.Author;
import com.example.graphql_sdl.model.Message;
import com.example.graphql_sdl.model.Post;
import com.example.graphql_sdl.repository.AuthorRepository;
import com.example.graphql_sdl.repository.MessageRepository;
import com.example.graphql_sdl.repository.PostRepository;
import com.example.graphql_sdl.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;
    private final MessageMapper messageMaper;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, PostRepository postRepository, AuthorRepository authorRepository, MessageMapper messageMaper) {
        this.messageRepository = messageRepository;
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
        this.messageMaper = messageMaper;
    }

    @Override
    public List<MessageDto> getFirstFewMessagesByAuthorId(UUID authorId, Integer count) {
        List<Message> allByAuthor_id = messageRepository.findAllByAuthor_Id(authorId, PageRequest.of(0, count));
        return allByAuthor_id.stream()
                .map(messageMaper::convertMessageToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> getFirstFewMessagesByPostId(UUID postId, Integer count) {
        List<Message> allByPost_id = messageRepository.findAllByPost_Id(postId, PageRequest.of(0, count));
        return allByPost_id.stream()
                .map(messageMaper::convertMessageToDto).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> getMessages(Integer count, Integer offset) {
        PageRequest of = PageRequest.of(offset, count); // offset ->How many page we want to skip,count -> size of the page
        Page<Message> all = messageRepository.findAll(of);
        return all.stream()
                .map(messageMaper::convertMessageToDto).collect(Collectors.toList());
    }

    @Override
    public UUID createMessage(MessageDto messageDto) {
        Optional<Author> authorOptional = authorRepository.findById(messageDto.getAuthorId());
        Optional<Post> postOptional = postRepository.findById(messageDto.getPostId());
        Author author = authorOptional.orElseThrow(() -> new ResourceNotFound("user is not exist!"));
        Post post = postOptional.orElseThrow(() -> new ResourceNotFound("Post is not exist!"));
        Message message = Message.builder()
                .text(messageDto.getText())
//                .author(authorOptional.get()) // uncomment it, use below replacement only in exception handling section
//                .post(postOptional.get())
                .author(author)
                .post(post)
                .build();

        Message createdComment = messageRepository.saveAndFlush(message);
        return createdComment.getId();
    }
}
