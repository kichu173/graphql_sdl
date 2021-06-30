package com.example.graphql_sdl.services;

import com.example.graphql_sdl.dto.AuthorDto;
import com.example.graphql_sdl.dto.PostDto;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<PostDto> getAllPostByAuthorId(UUID authorId);

    List<PostDto> getRecentPosts(int count, int offset);

    UUID createPost(PostDto postDto);

    Integer getPostCountByAuthorId(UUID id);

    PostDto getPostById(UUID postId);

    AuthorDto getAuthorById(UUID authorId);
}
