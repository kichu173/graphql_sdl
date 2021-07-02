package com.example.graphql_sdl.resolver.message;

import com.example.graphql_sdl.dto.MessageDto;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@Component
public class MessagePublisher {

    private final Sinks.Many<MessageDto> processor;

    public MessagePublisher() {
        this.processor = Sinks.many().replay().all();
    }

    public Publisher<MessageDto> getRecentMessageFor(UUID postId) {
        return processor.asFlux().filter(messageDto -> postId.equals(messageDto.getPostId()));
    }

    public void publish(MessageDto messageDto) {
        processor.emitNext(messageDto, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
