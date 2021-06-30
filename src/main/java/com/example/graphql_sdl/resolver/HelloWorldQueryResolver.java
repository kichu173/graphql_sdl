package com.example.graphql_sdl.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.graphql_sdl.dto.MessageDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class HelloWorldQueryResolver implements GraphQLQueryResolver {

    public String helloWorld() { // getHelloWorld, if boolean type isHelloWorld
        return "hello World! First graphql definition";
    }

    public String getGreetingMessage(String firstName, String lastName) {
        return String.format("Hello %s %s", firstName, lastName);
    }

    public MessageDto getMessage() {
        return MessageDto.builder()
                .id(UUID.randomUUID())
                .text("Graphql first step")
                .build();
    }

    public List<Integer> getRollDice() {
        return Arrays.asList(1,2,3,4);
    }
}
