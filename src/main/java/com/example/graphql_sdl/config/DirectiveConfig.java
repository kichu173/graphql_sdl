package com.example.graphql_sdl.config;

import com.example.graphql_sdl.directive.UppercaseDirective;
import graphql.kickstart.tools.boot.SchemaDirective;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectiveConfig {
    @Bean
    public SchemaDirective upperCaseDirective() {
        return new SchemaDirective("uppercase", new UppercaseDirective());
    }
}
