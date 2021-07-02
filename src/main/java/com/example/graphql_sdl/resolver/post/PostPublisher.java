package com.example.graphql_sdl.resolver.post;

import com.example.graphql_sdl.dto.PostDto;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@Component
public class PostPublisher {

    private final Sinks.Many<PostDto> processor;

    public PostPublisher() {
        this.processor = Sinks.many().replay().all();
    }

    public Publisher<PostDto> getRecentPosts() {
        return processor.asFlux();
    }

    public void publish(PostDto postDto) {
        processor.emitNext(postDto, Sinks.EmitFailureHandler.FAIL_FAST);
    }

    public Publisher<PostDto> getRecentPostByAuthor(UUID authorId) {
        return processor.asFlux().filter(postDto -> authorId.equals(postDto.getAuthorId()));
    }
}
